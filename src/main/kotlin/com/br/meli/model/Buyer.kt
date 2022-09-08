package com.br.meli.model

import javax.persistence.*

@Entity
data class Buyer(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var buyerId: Int
)
