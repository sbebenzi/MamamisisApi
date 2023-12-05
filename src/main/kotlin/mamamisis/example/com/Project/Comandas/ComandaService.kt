package mamamisis.example.com.Project.Comandas

import mamamisis.example.com.Project.Produto.ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ComandaService {
    @Autowired
    lateinit var repository: ComandaRepository
    fun getComandaMesa(numMesa: Int): ResponseEntity<*> {
        val comandasMesa = repository.getComandaMesa(numMesa)
        val listComanda = mutableListOf<Comanda>()
        if(comandasMesa.isEmpty()){
            return ResponseEntity.badRequest().body("Não foram encontradas comandas para essa mesa.")
        }
        comandasMesa.forEach {
            val itensComanda = repository.getItensComandas(it)
            listComanda.add(Comanda(it,itensComanda))
        }
        return ResponseEntity.ok(listComanda)
    }

    fun iniciaComanda(numMesa: Int): ResponseEntity<*> {
        val proximaComanda = repository.getIdComanda()
        repository.criaComanda(proximaComanda,numMesa)
        val comandaCriada = repository.getComandaId(proximaComanda)

        return ResponseEntity.ok(comandaCriada)
    }

    fun getComandaId(id: Int): ResponseEntity<*> {
        if(!repository.verificaExisteComanda(id)){
            return ResponseEntity.badRequest().body("Não foi encontrada a comanda de número:${id}.")
        }
        val itensComanda = repository.getItensComandas(id)
        val comanda = Comanda(id,itensComanda)

        return ResponseEntity.ok(comanda)
    }

    fun alteraComanda(numMesa: Int, comanda: Int): ResponseEntity<*> {
        if(!repository.verificaExisteComanda(comanda)){
            return ResponseEntity.badRequest().body("Não foi encontrada a comanda de número:${comanda}.")
        }
        repository.alteraComanda(numMesa,comanda)
        val comandaAlterada = getComandaId(comanda)

        return ResponseEntity.ok(comandaAlterada)
    }

    fun fechaComanda(numMesa: Int): ResponseEntity<*> {
        val comandasMesa = repository.getComandaMesa(numMesa)
        val listComanda = mutableListOf<ComandaFechada>()
        var valorComanda = 0.00

        if(comandasMesa.isEmpty()){
            return ResponseEntity.badRequest().body("Não foram encontradas comandas para essa mesa.")
        }

        comandasMesa.forEach {
            val itensComanda = repository.getItensComandas(it)
            listComanda.add(ComandaFechada(it,itensComanda,repository.getValorComanda(it)))
        }

        val valorComandasMesa = repository.getValorComandasMesa(numMesa)

        return ResponseEntity.ok(ComandasFechadas(numMesa,listComanda,valorComandasMesa))

    }

}