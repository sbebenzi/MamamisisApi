package mamamisis.example.com.Project.Produto

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProdutoService {

    @Autowired
    lateinit var repository: ProdutoRepository
    fun getProdutos(): ResponseEntity<List<Produto>> {
        try {
            val listProduto = repository.getProdutos()

            if (listProduto.isEmpty()) {
                return ResponseEntity.notFound().build()
            }

            return ResponseEntity.ok(listProduto)

        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    fun gravaProduto(produto: ProdutoInserir): ResponseEntity<*> {
        val id = repository.getNextId()
        val erro = validaProdutoInserir(produto)

        if (erro.isNotEmpty()) {
            return ResponseEntity.badRequest().body(erro)
        }

        return try {
            repository.gravaProduto(produto, id)
            val produtoInserido = repository.getProdutoInserido(id)
            produtoInserido?.let {
                ResponseEntity.ok(it)
            } ?: ResponseEntity.badRequest().body("Erro ao obter o produto inserido")
        } catch (ex: DataAccessException) {
            ex.printStackTrace()
            ResponseEntity.badRequest().body("Erro ao gravar o produto")
        }
    }

    private fun validaProdutoInserir(produto: ProdutoInserir): String {
        return when {
            repository.existeProduto(produto.nome) -> "Já existe um produto cadastrado com esse nome!"
            produto.nome.trim().isEmpty() -> "O nome do produto não pode ser vazio"
            produto.descricao.trim().isEmpty() -> "A descrição do produto não pode ser vazia!"
            produto.valor == 0.00 -> "O produto não pode ter valor igual a zero!"
            else -> ""
        }
    }

    fun getProdutoId(id: Int): ResponseEntity<*> {
        try {
            val produto = repository.getProdutoId(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o produto com id:${id}")


            return ResponseEntity.ok(produto)

        } catch (ex: Exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("")
        }
    }

    fun alteraProduto(payload: ProdutoAlterar): ResponseEntity<*> {
        repository.getProdutoId(payload.id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o produto com id:${payload.id} para alterar")
        repository.alteraProduto(payload)
        val produtoAlterado =  repository.getProdutoId(payload.id)
        return ResponseEntity.ok(produtoAlterado)

    }

    fun deleteProdutoById(id: Int): ResponseEntity<*> {
        //TODO ADICIONAR VALIDACAO PARA EXCLUIR PRODUTO -> NAO PODE ESTAR EM UMA COMANDA(ITEM). -> TEM QUE APAGAR A RECEITA JUNTO.
        val produto = repository.getProdutoId(id) ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possivel encontrar o produto com id:${id} para excluir")
        if(repository.verificaProdutoComanda(id)){
            return ResponseEntity.badRequest().body("Não é possivel deletar o produto pois o mesmo faz está em uma comanda.")
        }
        repository.deleteProdutoById(id)
        repository.deleteReceita(id)
        return ResponseEntity.ok(produto)
    }

}