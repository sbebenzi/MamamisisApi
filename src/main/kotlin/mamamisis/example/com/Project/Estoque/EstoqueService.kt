import mamamisis.example.com.Project.Estoque.ItenEstoque
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EstoqueService () {
    private val repository = EstoqueRepository()
    fun getEstoque(): ResponseEntity<List<ItenEstoque>> {
        try {
            val itensEstoque = repository.getEstoque()

            if (itensEstoque.isEmpty()) {
                return ResponseEntity.noContent().build()
            }

            return ResponseEntity.ok(itensEstoque)
        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}
