package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
data class Section(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @Column
    @Enumerated(EnumType.STRING)
    var category: Category?,

    @OneToMany(mappedBy = "section", fetch=FetchType.EAGER)
    var inboundOrder: List<InboundOrder>?,
) {

}
