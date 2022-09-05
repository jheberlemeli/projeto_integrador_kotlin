package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import io.micronaut.core.annotation.Introspected
import javax.persistence.*

@Entity
data class SellerAd(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,
    var price: Double?,

    @OneToMany(mappedBy = "sellerAd", fetch = FetchType.EAGER)
    var batchStockId: List<BatchStock>?,

    @ManyToOne
    @JoinColumn(name = "sellerId")
    @JsonIgnoreProperties("sellerAdList")
    var seller: Seller?,

    @ManyToOne
    var product: Product?,
) {

}
