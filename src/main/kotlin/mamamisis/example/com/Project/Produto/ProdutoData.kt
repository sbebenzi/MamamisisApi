package mamamisis.example.com.Project.Produto

data class Produto(
        val id:Int,
        val nome:String,
        val descricao:String,
        val valor:Double
)

data class ProdutoInserir(
        val nome:String,
        val descricao:String,
        val valor:Double,
        val ingredientes:List<Ingredientes>
)

data class Ingredientes(
        val descricao:String
)

data class ProdutoAlterar(
        val id:Int,
        val nome:String,
        val descricao:String?,
        val valor:Double?
)