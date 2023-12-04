package mamamisis.example.com.Project.Produto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/produto")
class ProdutoController {
    @Autowired
    lateinit var service : ProdutoService

    @GetMapping("/get-produtos")
    fun getProdutos():ResponseEntity<List<Produto>>{
        return service.getProdutos()
    }
    @GetMapping("/get-produtos/{id}")
    fun getProdutoId(@PathVariable("id") id: Int):ResponseEntity<*>{
        return service.getProdutoId(id)
    }

    @PostMapping("/grava-produto")
    fun gravaProdudo(@RequestBody payload: ProdutoInserir):ResponseEntity<*>{
        return service.gravaProduto(payload)
    }

    @PutMapping("/altera-produto")
    fun alteraProduto(@RequestBody payload: ProdutoAlterar):ResponseEntity<*>{
        return service.alteraProduto(payload)
    }

    @DeleteMapping("delete-produto/{id}")
    fun deleteProdutoById(@PathVariable id: Int):ResponseEntity<*>{
        return service.deleteProdutoById(id)
    }

    //TODO ADICIONAR VALIDACAO PARA EXCLUIR PRODUTO -> NAO PODE ESTAR EM UMA COMANDA(ITEM). -> TEM QUE APAGAR A RECEITA JUNTO.
}