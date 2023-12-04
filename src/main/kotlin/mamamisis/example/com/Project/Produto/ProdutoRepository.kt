package mamamisis.example.com.Project.Produto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class ProdutoRepository @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {
    fun getProdutos(): List<Produto> {
        val sql = """
            select a.id_produto,a.nome_produto,a.descricao,a.valor from produtos a
        """.trimIndent()

        return jdbcTemplate.query(sql){rs,_->
            Produto(
                    rs.getInt("id_produto"),
                    rs.getString("nome_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
            )
        }
    }

    fun gravaProduto(produto: ProdutoInserir, id: Int) {
        val sql = "INSERT INTO PRODUTOS (DESCRICAO,NOME_PRODUTO, VALOR,id_produto) VALUES (?,?,?, ?)"

        try {
            jdbcTemplate.update(sql, produto.descricao,produto.nome ,produto.valor,id)
        } catch (ex: DataAccessException) {
            // Trate exceções ou registre conforme necessário
            ex.printStackTrace()
        }
    }

    fun getProdutoInserido(id: Int):Produto? {
        val sql = """
            select a.id_produto,a.descricao,a.valor,a.nome_produto 
            from produtos a
            where a.id_produto = ?
            group by a.id_produto
        """.trimIndent()

        return jdbcTemplate.query(sql,id){rs,_->
            Produto(
                    rs.getInt("id_produto"),
                    rs.getString("nome_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
            )
        }.firstOrNull()
    }

    fun existeProduto(nome: String): Boolean {
        val sql = """
        select count(*) as qtd from produtos 
        where nome_produto = ?
    """.trimIndent()

        val qtd = jdbcTemplate.query(sql,nome){rs,_->rs.getInt("qtd")}.firstOrNull() ?: 0

        return qtd > 0
    }

    fun getNextId(): Int {
        val sql = """
            select nvl(max(id_produto),0) + 1 as id from produtos
        """.trimIndent()

        return jdbcTemplate.query(sql){rs,_->rs.getInt("id")}.first()
    }

    fun getProdutoId(id: Int): Produto? {
        val sql = """
            select a.id_produto,a.descricao,a.valor,a.nome_produto
            from produtos a
            where a.id_produto = ?
            group by a.id_produto
        """.trimIndent()

        return jdbcTemplate.query(sql,id){rs,_->
            Produto(
                    rs.getInt("id_produto"),
                    rs.getString("nome_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
            )
        }.firstOrNull()
    }

    fun alteraProduto(payload: ProdutoAlterar) {
        val sql = """
            update produtos
                   set descricao = ?,
                   valor = ?,
                   nome_produto = ?
            where id_produto = ?
        """.trimIndent()

        jdbcTemplate.update(sql,payload.descricao,payload.valor,payload.nome,payload.id)

    }

    fun deleteProdutoById(id: Int) {
        val sql = """
            delete from produtos 
            where id_produto = ?
        """.trimIndent()

        jdbcTemplate.update(sql,id)
    }

    fun verificaExisteIngrediente(ingrediente: Ingredientes):Int? {
        val sql = """
            select a.id_ingrediente from estoque a 
            where descricao = ?
        """.trimIndent()

        return jdbcTemplate.query(sql,ingrediente.descricao){rs,_->rs.getInt("id_ingrediente")}.firstOrNull()
    }

    fun adicionaIngrdiente(ingrediente: Ingredientes) {
        val sql = """
            insert into estoque a (a.descricao,a.quantidade,a.esgotado)
            values (?,0,0)
        """.trimIndent()

        jdbcTemplate.update(sql,ingrediente.descricao)
    }


}