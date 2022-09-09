package com.br.meli.repository

import com.br.meli.model.BatchStock
import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.repository.CrudRepository

@Repository
interface BatchStockRepo: JpaRepository<BatchStock, Int> {

    @Query(nativeQuery = true, value = "select sum(bs.current_quantity) from batch_stock bs\n" +
            "join seller_ad sa where bs.seller_ad_id = sa.id;  ")
    fun getQuantityProduct(product: Int?): Int

}
