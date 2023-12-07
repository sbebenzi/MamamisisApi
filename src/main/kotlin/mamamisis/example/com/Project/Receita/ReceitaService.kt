package mamamisis.example.com.Project.Receita

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ReceitaService {
    @Autowired
    lateinit var repository: ReceitaRepository
    fun getReceitas(): ResponseEntity<*> {
        return ResponseEntity.ok().body(repository.getReceitas())

    }

    fun getReceitasId(id: Int): ResponseEntity<*> {
        return ResponseEntity.ok().body(repository.getReceitasId(id))
    }

    fun insereItemReceita(payload: ItemReceita): ResponseEntity<*> {
        val id = repository.getNextId()

        repository.insereItemReceita(payload, id)

        return ResponseEntity.ok().body(repository.getItemReceita(id))

    }

    fun deleteReceitaId(id: Int): ResponseEntity<*> {
        val itemDeletado = repository.getItemReceita(id)
        repository.deleteReceitaId(id)
        if(itemDeletado == null){
            return ResponseEntity.badRequest().body("Não exisye Receita de Id:${id}")
        }
         return ResponseEntity.ok().body(itemDeletado)
    }
}