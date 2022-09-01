package com.br.meli.model

import javax.persistence.*


@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int,
    var name: String,

    @Enumerated(EnumType.STRING)
    var category: Category
) {

}
