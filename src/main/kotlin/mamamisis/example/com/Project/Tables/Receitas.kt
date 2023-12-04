package mamamisis.example.com.Project.Tables

import jakarta.persistence.*

@Entity
class Receitas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null
        get() = field
        set(value) {
            field = value
        }

    @ManyToOne
    @JoinColumn(name = "id_produto")
    var produto: Int? = null
        get() = field
        set(value) {
            field = value
        }

    var sequencia:Int? = null
        get() = field
        set(value) {
            field = value
        }

    @ManyToOne
    @JoinColumn(name = "id_ingrediente")
    var ingrediente: Int? = null
        get() = field
        set(value) {
            field = value
        }
    var quantidade: Int? = null
        get() = field
        set(value) {
            field = value
        }
}