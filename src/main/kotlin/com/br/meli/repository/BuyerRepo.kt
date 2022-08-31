package com.br.meli.repository

import com.br.meli.model.Buyer
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface BuyerRepo: CrudRepository<Buyer, Int> {

}
