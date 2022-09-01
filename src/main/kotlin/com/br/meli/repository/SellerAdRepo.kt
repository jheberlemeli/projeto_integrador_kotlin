package com.br.meli.repository

import com.br.meli.model.SellerAd
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface SellerAdRepo: CrudRepository<SellerAd, Int> {

}