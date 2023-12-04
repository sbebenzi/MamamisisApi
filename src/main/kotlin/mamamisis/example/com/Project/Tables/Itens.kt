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
    @JoinColumn(name = "id_comanda")
    var comanda: Int? = null
        get() = field
        set(value) {
            field = value
        }

    @JoinColumn(name = "id_produto")
    var produto:Int? = null
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