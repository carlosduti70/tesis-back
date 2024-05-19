package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.Card
import com.alzheimer.alzheimer.s.project.repository.CardRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.time.LocalTime

@Service
class CardService {
    @Autowired
    lateinit var cardRepository: CardRepository

    fun list (): List<Card> {
        return cardRepository.findAll()
    }

    fun save(card: Card): Card{
        try{
            card.dateTime = LocalDate.now()
            card.hour = LocalTime.now().withSecond(0).withNano(0)
            card.cardNameId= 1

            return cardRepository.save(card)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(card: Card): Card{
        try {
            cardRepository.findById(card.id)
                ?: throw Exception("ID no existe")

            return cardRepository.save(card)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(card:Card): Card{
        try{
            val response = cardRepository.findById(card.id)
                ?: throw Exception("ID no existe")
            response.apply {
                dateTime=card.dateTime
            }
            return cardRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Card?{
        return cardRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = cardRepository.findById(id)
                ?: throw Exception("ID no existe")
            cardRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}
