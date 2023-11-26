package com.marketgame.controller

import com.marketgame.controller.request.PostGameRequest
import com.marketgame.controller.request.PutGameRequest
import com.marketgame.controller.response.GameResponse
import com.marketgame.extension.toGameModel
import com.marketgame.extension.toResponse
import com.marketgame.service.CustomerService
import com.marketgame.service.GameService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@CrossOrigin("*")
@RequestMapping("game")
class GameController (
    val gameService: GameService,
    val customerService: CustomerService

) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request: PostGameRequest) {
        val customer = customerService.findById(request.customerId)
        gameService.create(request.toGameModel(customer))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 100) pageable: Pageable): Page<GameResponse>{

        return gameService.findAll(pageable).map { it.toResponse() }
    }

    @GetMapping("/active")
    fun findActives(@PageableDefault(page = 0, size = 100) pageable: Pageable): Page<GameResponse> =
        gameService.findActives(pageable).map { it.toResponse() }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): GameResponse{
        return gameService.findById(id).toResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Int){
        gameService.delete(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Int, @RequestBody game: PutGameRequest){
        val gameSaved = gameService.findById(id)
        gameService.update(game.toGameModel(gameSaved))
    }



}

