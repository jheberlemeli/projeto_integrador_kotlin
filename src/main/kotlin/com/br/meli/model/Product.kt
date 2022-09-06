package com.br.meli.model

import javax.persistence.*


@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public var id: Int,
    public var name: String,

    @Enumerated(EnumType.STRING)
    public var category: Category,

    @OneToMany(mappedBy = "product")
    public var sellerAd: List<SellerAd>
)
