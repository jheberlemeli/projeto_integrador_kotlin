package com.br.meli.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
class Product(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int,
    var name: String,
    var category: Category
) {

}
