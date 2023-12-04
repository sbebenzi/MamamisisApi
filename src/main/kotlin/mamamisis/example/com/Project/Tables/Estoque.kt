package mamamisis.example.com.Project.Tables

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
// Tabela que guarda os ingrdientes e as quantidades.
@Entity
@Table(name = "estoque")
class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id_ingrediente:Int? = null
        get() = field
        set(value) {
            field = value
        }

    var descricao_ingrdiente:String? = null
        get() = field
        set(value) {
            field = value
        }

    var quantidade:Int? = null
        get() = field
        set(value) {
            field = value
        }

    var esgotado:Int? = null
        get() = field
        set(value) {
            field = value
        }
}