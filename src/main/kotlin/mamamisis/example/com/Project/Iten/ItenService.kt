package mamamisis.example.com.Project.Iten

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ItenService {
    @Autowired
    lateinit var repository: ItenRepository
    fun getItem(id: Int): ResponseEntity<*> {
        return ResponseEntity.ok().body(repository.getItem(id)?:"Não foi encontrado nenhum item com esse id:${id}")
    }

    fun getItens(): ResponseEntity<*> {
        return ResponseEntity.ok().body(repository.getItens().isEmpty())
    }

    fun deleteIten(id: Int): ResponseEntity<*> {
        val itemDeleteado = repository.getItem(id) ?: return ResponseEntity.badRequest().body("Não foi encontrado nenhum item com esse id:${id}")
        repository.deleteItem(id)
        return ResponseEntity.ok().body(itemDeleteado)

    }

    fun alteraStatus(id: Int): ResponseEntity<*> {
        repository.getItem(id) ?: return ResponseEntity.badRequest().body("Não foi encontrado nenhum item com esse id:${id}")
        repository.aleteraStatus(id)
        return ResponseEntity.ok().body(repository.getItem(id))
    }
}
