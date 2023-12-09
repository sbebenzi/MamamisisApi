package mamamisis.example.com.Project.Comandas

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.stereotype.Repository

@Repository
class ComandaRepository @Autowired constructor(private val jdbcTemplate: JdbcTemplate) {
    fun getComandaMesa(numMesa: Int): List<Int> {
        val sql = """
            select a.id_comanda from comandas a
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
            select nvl(max(a.id_comanda),0) + 1 as id_comanda from comandas a
        """.trimIndent()

        return jdbcTemplate.query(sql){rs,_->rs.getInt("id_comanda")}.first()
    }

    fun criaComanda(proximaComanda: Int, numMesa: Int) {
        val sql = """
            insert into comandas (id_comanda,mesa,status_comanda)
            values(?,?,1)
        """.trimIndent()

        jdbcTemplate.update(sql,proximaComanda,numMesa)
    }

    fun getComandaId(proximaComanda: Int): DadosComanda {
        val sql = """
            select id_comanda,mesa,status_comanda from comandas
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
             select count(id_comanda)as qtd from comandas
             where id_comanda = ?
        """.trimIndent()

        val qtdComanda = jdbcTemplate.query(sql,id){rs,_->rs.getInt("qtd")}.first()

        return qtdComanda != 0
    }

    fun alteraComanda(numMesa: Int, comanda: Int) {
        val sql = """
            update comandas set mesa = ?
            where id_comanda = ? 
        """.trimIndent()

        jdbcTemplate.update(sql,numMesa,comanda)
    }
    fun getValorComanda(comanda:Int):Double{
        val sql = """
            select sum(b.valor)as valorTotal from itens a 
            left join produtos b on (a.produto = b.id_produto)
            where a.comanda = ? 
        """.trimIndent()

        return jdbcTemplate.query(sql,comanda){rs,_->rs.getDouble("valorTotal")}.first()
    }

    fun getValorComandasMesa(comanda: Int): Double {
        val sql = """
            select sum(b.valor)as valorTotal from itens a 
            left join produtos b on (a.produto = b.id_produto)
            where a.comanda = ? 
        """.trimIndent()

        return jdbcTemplate.query(sql,comanda){rs,_->rs.getDouble("valorTotal")}.first()
    }

    fun getItemId(): Int {
        val sql = """
            select nvl(max(a.id_item),0) + 1 as id_item from itens a
        """.trimIndent()
        return jdbcTemplate.query(sql){rs,_->rs.getInt("id_item")}.first()
    }

    fun adicionaItemComanda(payload: ItemComanda, idItem: Int) {
        val sql = """
            insert into itens (id_item,comanda,produto,status_item) 
            values(?,?,?,1)
        """.trimIndent()

        jdbcTemplate.update(sql,idItem,payload.comanda,payload.produto)

    }
}