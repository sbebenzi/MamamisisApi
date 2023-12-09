package mamamisis.example.com.Project.Estoque


import com.fasterxml.jackson.module.kotlin.jsonMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class EstoqueRepository @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {

    fun getEstoque(): List<ItenEstoque> {
        val sql = """
            select id_ingrediente, descricao_ingrdiente, quantidade, esgotado from estoque
        """.trimIndent()
        val itensEstoque = jdbcTemplate.query(sql) { rs, _ ->
            ItenEstoque(
                    rs.getInt("id_ingrediente"),
                    rs.getString("descricao_ingrdiente"),
                    rs.getInt("quantidade"),
                    rs.getInt("esgotado")
            )
        }

        return itensEstoque
    }

    fun getNextId(): Int {
        val sql = """
            select nvl(max(id_ingrediente),0) + 1 as nextId from estoque 
        """.trimIndent()

        return jdbcTemplate.query(sql) { rs, _ -> rs.getInt("nextId") }.first()
    }

    fun insereIngrediente(payload: Ingrdiente, id: Int) {
        val sql = """
            insert into estoque (id_ingrediente,descricao_ingrdiente,quantidade,esgotado)
            values(?,?,?,0)
        """.trimIndent()

        jdbcTemplate.update(sql, id, payload.descricao, payload.quantidade)
    }

    fun getIngrediente(id: Int): List<ItenEstoque> {
        val sql = """
            select id_ingrediente, descricao_ingrdiente, quantidade, esgotado from estoque
            where id_ingrediente = ?
        """.trimIndent()
        val itensEstoque = jdbcTemplate.query(sql, id) { rs, _ ->
            ItenEstoque(
                    rs.getInt("id_ingrediente"),
                    rs.getString("descricao_ingrdiente"),
                    rs.getInt("quantidade"),
                    rs.getInt("esgotado")
            )
        }
        return itensEstoque
    }

    fun naoExisteEstoque(payload: IngredienteQuantidade):Boolean {
        val sql = """
            select count(*) as qtd from estoque where id_ingrediente = ?
        """.trimIndent()
        val qtd = jdbcTemplate.query(sql,payload.idIngrediente){rs,_->rs.getInt("qtd")}.firstOrNull() ?: 0

        return qtd == 0
    }

    fun alteraQuantidadeEstoque(payload: IngredienteQuantidade) {
        val sql = """
            update estoque set quantidade = ?
            where id_ingrediente = ?
        """.trimIndent()
        jdbcTemplate.update(sql,payload.novaQuantidade,payload.idIngrediente)
    }
}