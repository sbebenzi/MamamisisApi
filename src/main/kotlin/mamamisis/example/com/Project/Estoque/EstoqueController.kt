package mamamisis.example.com.Project.Estoque

import jakarta.validation.Payload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mapping.model.IdPropertyIdentifierAccessor
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/estoque")
class EstoqueController () {
    @Autowired
    lateinit var estoqueService : EstoqueService
    @GetMapping("/get-estoque")
    fun getEstoque(): ResponseEntity<List<ItenEstoque>> {
        return estoqueService.getEstoque()
    }

    @PostMapping("/insere-ingrediente")
    fun insereIngrdiene(@RequestBody payload: Ingrdiente):ResponseEntity<*>{
        return estoqueService.insereIngrdiente(payload)
    }

    @PutMapping("/altera-quantidade-estoque")
    fun alteraQuantidadeEstoque(@RequestBody payload:IngredienteQuantidade):ResponseEntity<*>{
        return estoqueService.alteraQuantidadeEstoque(payload)
    }

}