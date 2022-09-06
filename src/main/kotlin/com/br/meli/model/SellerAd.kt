package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.micronaut.core.annotation.Introspected
import javax.persistence.*

@Entity
data class SellerAd(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public var id: Int?,
    public var price: Double?,

    @OneToMany(mappedBy = "sellerAd", fetch = FetchType.EAGER)
    public var batchStockId: List<BatchStock>?,

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @JsonIgnoreProperties("sellerAdList")
    public var seller: Seller?,

    @ManyToOne
    public var product: Product?,
)
