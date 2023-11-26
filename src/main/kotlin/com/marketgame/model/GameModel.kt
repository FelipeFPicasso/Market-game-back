package com.marketgame.model

import com.marketgame.enums.Errors
import com.marketgame.enums.GameStatus
import com.marketgame.exception.BadRequestException
import jakarta.persistence.*
import org.springframework.web.client.HttpClientErrorException.BadRequest
import java.math.BigDecimal

@Entity(name = "game")
data class GameModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,



    @ManyToOne
    @JoinColumn(name="customer_id")
    var customer: CustomerModel? = null

){
    @Column
    @Enumerated(EnumType.STRING)
    var status: GameStatus? = null
        set(value){
            if(field == GameStatus.CANCELADO || field == GameStatus.DELETADO)
                throw BadRequestException(Errors.MG102.message.format(field), Errors.MG102.code)

            field = value
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: GameStatus?): this(id, name, price, customer){
        this.status = status
                }
}

