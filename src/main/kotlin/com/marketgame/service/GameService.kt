package com.marketgame.service

import com.marketgame.enums.Errors
import com.marketgame.enums.GameStatus
import com.marketgame.exception.NotFoundException
import com.marketgame.model.CustomerModel
import com.marketgame.model.GameModel
import com.marketgame.repository.GameRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GameService (
    val gameRepository: GameRepository
){

    fun create(game: GameModel) {
        gameRepository.save(game)
    }

    fun findAll(pageable: Pageable): Page<GameModel> {
        return gameRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<GameModel> {
        return gameRepository.findByStatus(GameStatus.ATIVO, pageable)
    }

    fun findById(id: Int): GameModel {
        return gameRepository.findById(id).orElseThrow{NotFoundException(Errors.MG101.message.format(id), Errors.MG101.code)}
    }

    fun delete(id: Int) {
        val game = findById(id)

        game.status = GameStatus.CANCELADO

        update(game)
    }

    fun update(game: GameModel) {
        gameRepository.save(game)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val games = gameRepository.findByCustomer(customer)
        for(game in games){
            game.status = GameStatus.DELETADO
        }
        gameRepository.saveAll(games)

    }

    fun findAllByIds(gameIds: Set<Int>): List<GameModel> {
        return gameRepository.findAllById(gameIds).toList()
    }

    fun purchase(games: MutableList<GameModel>) {
        games.map {
            it.status = GameStatus.VENDIDO
        }
        gameRepository.saveAll(games)

    }


}
