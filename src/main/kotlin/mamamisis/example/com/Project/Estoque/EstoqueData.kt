package mamamisis.example.com.Project.Estoque

data class ItenEstoque(
        val idProduto:Int,
        val descricao:String,
        val quantidade:Int,
        val esgotado:Int
)
data class Ingrdiente(
        val descricao:String,
        val quantidade:Int,
)

data class IngredienteQuantidade(
        val idIngrediente:Int,
        val novaQuantidade:Int
)