package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
class BatchStock(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int,

    @ManyToOne
    @JsonIgnoreProperties("batchStockId")
    var sellerAd: SellerAd,

    @ManyToOne
    @JsonIgnoreProperties("batchStockList")
    @JoinColumn(name = "inbound_order_id")
    var inboundOrder: InboundOrder?,
) {

}
