package com.br.meli.model

import javax.persistence.*
import javax.validation.constraints.Email

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,
    var name: String,
    var email: String,
    var password: String,
    var cpf: String,
) {

}
