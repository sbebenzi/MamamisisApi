package mamamisis.example.com.Project.Comandas

import mamamisis.example.com.Project.Tables.Produtos

data class Comanda(
        val numComanda:Int,
        var itens:List<Itens>
)

data class Itens(
        val nomeProduto: String,
        val descricaoProduto:String,
        val valorProduto:Double
)

data class DadosComanda (
      val  id_comanda:Int,
      val  mesa:Int,
      val  status_comanda:Int
)

data class ComandasFechadas(
        val numMesa:Int,
        val listaComandas:List<ComandaFechada>,
        val total:Double
)

data class ComandaFechada(
        val numComanda:Int,
        var itens:List<Itens> ,
        val valorComanda:Double
)

data class ItemComanda(
        val comanda:Int,
        val produto:Int
)