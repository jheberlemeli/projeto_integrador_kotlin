package com.br.meli.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import net.bytebuddy.implementation.attribute.AnnotationAppender.Default.apply
import javax.persistence.*

@Entity
data class InboundOrder constructor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int?,

    @ManyToOne
    @JoinColumn(name = "sectionId")
    @JsonIgnoreProperties("inboundOrder")
    var section: Section?,

    @OneToMany(mappedBy = "inboundOrder", cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    var batchStockList: List<BatchStock>?,
){

}
