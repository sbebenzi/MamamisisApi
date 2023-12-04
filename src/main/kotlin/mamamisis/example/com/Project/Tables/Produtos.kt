package mamamisis.example.com.Project.Tables

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id_produto:Int? = null
        get() = field
        set(value) {
            field = value
        }

    var nome_produto:String? = null
        get() = field
        set(value) {
            field = value
        }

    var descricao:String? = null
        get() = field
        set(value) {
            field = value
        }
    var valor:Double? = null
        get() = field
        set(value) {
            field = value
        }
}