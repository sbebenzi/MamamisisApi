package mamamisis.example.com.Project.Receita


data class Receita(
        val id: Int,
        val quantidade: Int,
        val produtoInserir: Produto

)
data class Produto(
        val nome:String,
        val descricao:String,
        val valor:Double
)

data class ItemReceita(
        val produto: Int,
        val ingredient: Int,
        val quantidade: Int
)
