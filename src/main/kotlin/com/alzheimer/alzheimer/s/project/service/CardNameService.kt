package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.CardName
import com.alzheimer.alzheimer.s.project.repository.CardNameRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CardNameService {
    @Autowired
    lateinit var cardNameRepository: CardNameRepository

    fun list (): List<CardName> {
        return cardNameRepository.findAll()
    }

    fun save(cardName: CardName): CardName{
        try{
            return cardNameRepository.save(cardName)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(cardName: CardName): CardName{
        try {
            cardNameRepository.findById(cardName.id)
                ?: throw Exception("ID no existe")

            return cardNameRepository.save(cardName)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(cardName:CardName): CardName{
        try{
            val response = cardNameRepository.findById(cardName.id)
                ?: throw Exception("ID no existe")
            response.apply {
                cardUID=cardName.cardUID
            }
            return cardNameRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):CardName?{
        return cardNameRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = cardNameRepository.findById(id)
                ?: throw Exception("ID no existe")
            cardNameRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}
