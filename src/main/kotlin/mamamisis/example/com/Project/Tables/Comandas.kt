package mamamisis.example.com.Project.Tables

import jakarta.annotation.Generated
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Comandas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id_comanda: Int? = null
        get() = field
        set(value) {
            field = value
        }

    var status_comanda: Int? = null
        get() = field
        set(value) {
            field = value
        }
}
