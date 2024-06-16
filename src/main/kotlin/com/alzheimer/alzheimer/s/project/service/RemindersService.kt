package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.Card
import com.alzheimer.alzheimer.s.project.model.Interactions
import com.alzheimer.alzheimer.s.project.model.Reminders
import com.alzheimer.alzheimer.s.project.repository.InteractionsRepository
import com.alzheimer.alzheimer.s.project.repository.RemindersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class RemindersService {

    @Autowired
    lateinit var remindersRepository: RemindersRepository

    @Autowired
    lateinit var interactionsRepository: InteractionsRepository

    fun list (): List<Reminders> {
        return remindersRepository.findAll()
    }



    fun listCard(card: Card): List<Reminders> {
        val reminders = remindersRepository.findAll()

        val response = reminders.filter {
            it.date == card.dateTime
                    && it.startTime!! <= card.hour!!.withSecond(0).withNano(0)
                    && it.endTime!! >= card.hour!!.withSecond(0).withNano(0)
        }

        response.forEach { reminder ->
            val interaction = Interactions().apply {
                title = reminder.title        // Título del recordatorio
                dateTime = card.dateTime  // Fecha de la card
                hour = card.hour!!.withSecond(0).withNano(0)    // Hora de la card
                cardId = card.id
            }
            interactionsRepository.save(interaction)
        }

            return response
    }



    fun save(reminders: Reminders): Reminders{
        try{
            return remindersRepository.save(reminders)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }

    fun update(reminders: Reminders): Reminders{
        try {
            remindersRepository.findById(reminders.id)
                ?: throw Exception("ID no existe")

            return remindersRepository.save(reminders)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(reminders:Reminders): Reminders{
        try{
            val response = remindersRepository.findById(reminders.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title=reminders.title
            }
            return remindersRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Reminders?{
        return remindersRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = remindersRepository.findById(id)
                ?: throw Exception("ID no existe")
            remindersRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }


}
