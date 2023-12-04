package mamamisis.example.com.Project.Estoque


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
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
}
