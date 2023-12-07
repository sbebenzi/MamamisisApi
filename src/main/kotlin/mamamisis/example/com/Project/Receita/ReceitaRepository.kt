package mamamisis.example.com.Project.Receita

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class ReceitaRepository @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {

    fun getReceitas(): List<Receita> {
        val sql = """
            select a.id, a.quantidade, b.nome_produto,b.descricao,b.valor from receitas a
            left join produto b on (a.produto = b.id_produto)
        """.trimIndent()

        return jdbcTemplate.query(sql){rs,_->
            Receita(
                    rs.getInt("id"),
                    rs.getInt("quantidade"),
                    Produto(
                            rs.getString("nome_produto"),
                            rs.getString("descricao"),
                            rs.getDouble("valor")
                    )
            )
        }

    }

    fun getReceitasId(id: Int): List<Receita> {
        val sql = """
            select a.id, a.quantidade, b.nome_produto,b.descricao,b.valor from receitas a
            left join produto b on (a.produto = b.id_produto)
            where a.produto = ?
        """.trimIndent()

        return jdbcTemplate.query(sql,id){rs,_->
            Receita(
                    rs.getInt("id"),
                    rs.getInt("quantidade"),
                    Produto(
                            rs.getString("nome_produto"),
                            rs.getString("descricao"),
                            rs.getDouble("valor")
                    )
            )
        }
    }

    fun getNextId(): Int {

        val sql = """
            select nvl(max(id),0)+1 as id from receitas
        """.trimIndent()

        return jdbcTemplate.query(sql){rs,_->
            rs.getInt("id")
        }.firstOrNull()?: 0

    }

    fun insereItemReceita(payload: ItemReceita, id: Int) {

        val sql = """
            insert into receitas id,produto, ingrediente, quantidade
             values(?,?,?,?)
        """.trimIndent()

        jdbcTemplate.update(sql,id, payload.produto, payload.ingredient, payload.quantidade)
    }

    fun getItemReceita(id: Int): ItemReceita? {
        val sql ="""
            select id,produto, ingrediente, quantidade from receitas where id = ?
        """.trimIndent()

        return jdbcTemplate.query(sql,id){rs,_->
            ItemReceita(
                    rs.getInt("produto"),
                    rs.getInt("ingrediente"),
                    rs.getInt("quantidade")
            )

        }.firstOrNull()
    }

    fun deleteReceitaId(id: Int) {
        val sql = """
             delete from receitas where id = ?
        """.trimIndent()
        jdbcTemplate.update(sql)
    }
}