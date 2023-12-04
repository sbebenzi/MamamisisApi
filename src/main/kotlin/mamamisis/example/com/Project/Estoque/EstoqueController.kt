package mamamisis.example.com.Project.Estoque

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.bind.annotation.GetMapping
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
}