package com.br.meli.repository

import com.br.meli.model.BatchStock
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.repository.CrudRepository

@Repository
interface BatchStockRepo: JpaRepository<BatchStock, Int> {

    @Query(nativeQuery = true, value = "SELECT sum(bs.current_quantity) from batchStock s\n" +
            "join seller_ad sa where s.seller_ad_id = :product  ")
    fun getQuantityProduct(product: Int?): Int

}
