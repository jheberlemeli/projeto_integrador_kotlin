package com.br.meli.repository

import com.br.meli.model.Category
import com.br.meli.model.Section
import io.micronaut.context.annotation.Parameter
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.math.BigInteger

@Repository
interface SectionRepo: JpaRepository<Section, Int> {

    @Query(nativeQuery = true, value = "SELECT sec.section_id from section as sec where sec.category = category")
    fun getSectionsIdsByCategory(@Parameter("category") category: Category?): List<BigInteger>

}
