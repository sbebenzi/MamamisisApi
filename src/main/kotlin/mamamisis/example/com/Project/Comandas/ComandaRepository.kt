package mamamisis.example.com.Project.Comandas

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class ComandaRepository @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {
    fun getComandaMesa(numMesa: Int): List<Int> {
        val sql = """
            select a.id_comanda from comandas 
            where a.mesa = ?
        """.trimIndent()

        return jdbcTemplate.query(sql,numMesa){rs,_-> rs.getInt("id_comanda")}

    }

    fun getItensComandas(comandasMesa: Int):List<Itens>{
        val sql = """
            select b.nome_produto,b.descricao,b.valor from itens a 
            left join produtos b on (a.produto = b.id_produto)
            where a.comanda = ? 
        """.trimIndent()

        return jdbcTemplate.query(sql,comandasMesa){rs,_->
            Itens(
                    rs.getString("nome_produto"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
            )
        }
    }

    fun getIdComanda(): Int {
        val sql = """
            select nvl(max(a.id_comanda),0) + 1 as id_comanda from comanda a
        """.trimIndent()

        return jdbcTemplate.query(sql){rs,_->rs.getInt("id_comanda")}.first()
    }

    fun criaComanda(proximaComanda: Int, numMesa: Int) {
        val sql = """
            insert into comanda (id_comanda,mesa,status_comanda)
            values(?,?,1)
        """.trimIndent()

        jdbcTemplate.update(sql,proximaComanda,numMesa)
    }

    fun getComandaId(proximaComanda: Int): DadosComanda {
        val sql = """
            select id_comanda,mesa,status_comanda from comanda
            where id_comanda = ?
        """.trimIndent()

        return jdbcTemplate.query(sql,proximaComanda){rs,_->
            DadosComanda(
                    rs.getInt("id_comanda"),
                    rs.getInt("mesa"),
                    rs.getInt("status_comanda")
            )
        }.first()
    }

    fun verificaExisteComanda(id: Int): Boolean {
        val sql = """
             select count(id_comanda)as qtd from comanda
             where id_comanda = ?
        """.trimIndent()

        val qtdComanda = jdbcTemplate.query(sql){rs,_->rs.getInt("qtd")}.first()

        return qtdComanda != 0
    }
}