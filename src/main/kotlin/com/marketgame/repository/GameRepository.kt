package com.marketgame.repository

import com.marketgame.enums.GameStatus
import com.marketgame.model.CustomerModel
import com.marketgame.model.GameModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository

interface GameRepository : JpaRepository<GameModel, Int> {

     fun findByStatus(status: GameStatus, pageable: Pageable): Page<GameModel>
     fun findByCustomer(customer: CustomerModel): List<GameModel>

//     fun findAll(pageable:Pageable): Page<GameModel>

}