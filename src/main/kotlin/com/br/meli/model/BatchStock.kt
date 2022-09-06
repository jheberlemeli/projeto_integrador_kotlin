package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.time.LocalDate
import javax.persistence.*

@Entity
class BatchStock(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @ManyToOne
    @JsonIgnoreProperties("batchStockId")
    var sellerAd: SellerAd,

    var currentQuantity: Int,

    var dueDate: LocalDate,

    @ManyToOne
    @JsonIgnoreProperties("batchStockList")
    @JoinColumn(name = "inbound_order_id")
    var inboundOrder: InboundOrder?,
)
