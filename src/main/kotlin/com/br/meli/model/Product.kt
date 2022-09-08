package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*


@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public var id: Int,
    public var name: String,

    @Enumerated(EnumType.STRING)
    public var category: Category,

    @OneToMany(mappedBy = "product", fetch=FetchType.EAGER)
    public var sellerAd: List<SellerAd>
)
{
    override fun toString(): String {
        return "Product(id=$id, name='$name', category=$category)"
    }
}
