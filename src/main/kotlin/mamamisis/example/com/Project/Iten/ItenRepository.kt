package mamamisis.example.com.Project.Iten

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.query
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class ItenRepository @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {
    fun getItem(id: Int): Iten? {
        val sql = """
            select id_item,comanda,produto,status_item from itens
            where id_item = ?
        """.trimIndent()
        return jdbcTemplate.query(sql,id){rs,_->
            Iten(
        rs.getInt("id_item"),
        rs.getInt("comanda"),
        rs.getInt("produto"),
        rs.getInt("status_item")
            )
        }.first()

    }

    fun getItens(): List<Iten> {
        val sql = """
            select id_item,comanda,produto,status_item from itens
            where id_item = ?
        """.trimIndent()
        return jdbcTemplate.query(sql){rs,_->
            Iten(
                    rs.getInt("id_item"),
                    rs.getInt("id_comanda"),
                    rs.getInt("id_produto"),
                    rs.getInt("status_item")
            )
        }
    }

    fun deleteItem(id: Int) {
        val sql = """
            DELETE FROM Itens WHERE id_item = ?
        """.trimIndent()

        jdbcTemplate.update(sql,id)
    }

    fun aleteraStatus(id: Int) {
        val sql = """
            UPDATE Itens SET status_item = 0 WHERE id_item = ?;
        """.trimIndent()
        jdbcTemplate.update(sql,id)
    }
}

