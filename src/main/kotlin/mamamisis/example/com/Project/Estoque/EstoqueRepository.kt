import mamamisis.example.com.Project.Estoque.ItenEstoque
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class EstoqueRepository(@Autowired
                        private val jdbcTemplate: JdbcTemplate) {
    fun getEstoque(): List<ItenEstoque> {
        val sqlQuery = "select id_ingrediente, descricao_ingrdiente, quantidade, esgotado from estoque"
        val itensEstoque = jdbcTemplate.query(sqlQuery) { rs, _ ->
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
