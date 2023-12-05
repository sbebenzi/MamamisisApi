package mamamisis.example.com.Project.Comandas

import jakarta.validation.Payload
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/comandas")
class ComandaController {
    @Autowired
    lateinit var service : ComandaService
    @GetMapping("/get-comanda-mesa/{numMesa}")
    fun getComandasMesa(@PathVariable numMesa:Int):ResponseEntity<*>{
        return service.getComandaMesa(numMesa)
    }

    @GetMapping("get-comanda/{id}")
    fun getComandaId(@PathVariable id:Int):ResponseEntity<*>{
        return service.getComandaId(id)
    }

    @PostMapping("/inicia-comanda/{numMesa}")
    fun iniciaComanda(@PathVariable numMesa: Int):ResponseEntity<*>{
        return service.iniciaComanda(numMesa)
    }

    @PutMapping("/altera-mesa-comanda/{numMesa}/{comanda}")
    fun alteraMesaComanda(@PathVariable numMesa: Int,@PathVariable comanda:Int):ResponseEntity<*>{
        return service.alteraComanda(numMesa,comanda)
    }

    @PutMapping("/fecha-comandas/{numMesa}")
    fun fechaComandaNumMesa(@PathVariable numMesa: Int):ResponseEntity<*>{
        return service.fechaComanda(numMesa)
    }
}