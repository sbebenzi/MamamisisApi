package mamamisis.example.com.Project.Comandas

import mamamisis.example.com.Project.Iten.ItenRepository
import mamamisis.example.com.Project.Produto.ProdutoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ComandaService {
    @Autowired
    lateinit var repository: ComandaRepository
    @Autowired
    lateinit var repositoryProduto: ProdutoRepository
    @Autowired
    lateinit var repositoryITem: ItenRepository
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
        var valorComandaMesa = 0.00

        if(comandasMesa.isEmpty()){
            return ResponseEntity.badRequest().body("Não foram encontradas comandas para essa mesa.")
        }

        comandasMesa.forEach {
            val itensComanda = repository.getItensComandas(it)
            listComanda.add(ComandaFechada(it,itensComanda,repository.getValorComanda(it)))
        }

        comandasMesa.forEach{ valorComandaMesa  +=  repository.getValorComandasMesa(it)}

        return ResponseEntity.ok(ComandasFechadas(numMesa,listComanda,valorComandaMesa))

    }

    fun addItemComanda(payload: ItemComanda):ResponseEntity<*> {
        val idItem = repository.getItemId()
        if(verificaItemComanda(payload) != ""){
            return ResponseEntity.badRequest().body(verificaItemComanda(payload))
        }
        repository.adicionaItemComanda(payload,idItem)

        return ResponseEntity.ok().body(repositoryITem.getItem(idItem))
    }

    private fun verificaItemComanda(payload: ItemComanda):String? {
        return when {
            !repository.verificaExisteComanda(payload.comanda) -> "Não foi possivel localizar comanda de numero ${payload.comanda}"
            repositoryProduto.getProdutoId(payload.produto) == null -> "Não foi possivel achar um produto de id: ${payload.produto}"
            else -> ""
        }
    }

}