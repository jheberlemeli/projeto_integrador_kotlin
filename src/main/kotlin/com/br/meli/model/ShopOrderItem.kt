package com.br.meli.model

import java.time.LocalDate
import javax.persistence.*

@Entity
data class ShopOrderItem(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int?,
    var date: LocalDate?,
    var price: Double?,
    var quantity: Int?,
    @ManyToOne(cascade = [CascadeType.PERSIST])
    @JoinColumn(name = "sellerAd_id")
    var sellerAd: SellerAd?,

    ) {

}
