package mamamisis.example.com.Project.Iten

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/iten")
class ItenController {
    @Autowired
    lateinit var service: ItenService
    @GetMapping("/get-item/{id}")
    fun getItem(@PathVariable id:Int):ResponseEntity<*>{
        return service.getItem(id)
    }

    @GetMapping("/get-item")
    fun getItens():ResponseEntity<*>{
        return service.getItens()
    }

    @DeleteMapping("/delete-item/{id}")
    fun deleteItem(@PathVariable id:Int):ResponseEntity<*>{
        return service.deleteIten(id)
    }

    @PostMapping("/altera-status-pedido/{id}")
    fun alteraStatu(@PathVariable id:Int):ResponseEntity<*>{
        return service.alteraStatus(id)
    }
}