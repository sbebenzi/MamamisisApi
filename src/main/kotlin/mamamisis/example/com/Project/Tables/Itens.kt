package mamamisis.example.com.Project.Tables

import jakarta.persistence.*

@Entity
class Itens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id_item :Int? = null
        get() = field
        set(value) {
            field = value
        }
    @ManyToOne
    @JoinColumn(name = "id_comanda")
    var comanda: Comandas? = null
        get() = field
        set(value) {
            field = value
        }

    @ManyToOne
    @JoinColumn(name = "id_produto")
    var produto:Produtos? = null
        get() = field
        set(value) {
            field = value
        }
    var status_item:Int? = null
        get() = field
        set(value) {
            field = value
        }
}