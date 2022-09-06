package com.br.meli.model

import javax.persistence.*

@Entity
data class ShopOrder(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Enumerated(EnumType.STRING)
    var status: Status?,

    @OneToMany(cascade = [CascadeType.PERSIST], fetch=FetchType.EAGER)
    @JoinColumn(name = "shopOrder_id")
    @Column
    var shopOrderItem: List<ShopOrderItem>?,

    @ManyToOne(cascade= arrayOf(CascadeType.PERSIST))
    @JoinColumn(name="buyer_id")
    var buyer: Buyer?,

    var totalPrice: Double?

    )