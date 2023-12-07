package mamamisis.example.com.Project.Receita

import jakarta.validation.Payload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/receita")
class ReceitasController {

    @Autowired
    lateinit var service: ReceitaService

    @GetMapping("/get-receitas")
    fun getReceitas(): ResponseEntity<*>{
        return service.getReceitas()

    }
    @GetMapping("/get-receita-produto/{id}")
    fun getReceitasId(@PathVariable id:Int): ResponseEntity<*>{
        return service.getReceitasId(id)

    }

    @PostMapping("/insere-item-receita")
    fun insereItemReceita(@RequestBody payload: ItemReceita): ResponseEntity<*>{
        return service.insereItemReceita(payload)
    }

    @DeleteMapping("/deleta-receita/{id}")
    fun deletaReceitaId(@PathVariable id:Int):ResponseEntity<*>{
        return service.deleteReceitaId(id)
    }
}