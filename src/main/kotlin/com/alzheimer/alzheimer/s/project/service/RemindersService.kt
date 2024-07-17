package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.Card
import com.alzheimer.alzheimer.s.project.model.Interactions
import com.alzheimer.alzheimer.s.project.model.Reminders
import com.alzheimer.alzheimer.s.project.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.time.LocalTime
import kotlinx.coroutines.*

@Service
class RemindersService {

    @Autowired
    lateinit var remindersRepository: RemindersRepository

    @Autowired
    lateinit var interactionsRepository: InteractionsRepository

    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var cardRepository: CardRepository

    fun list (): List<Reminders> {
        return remindersRepository.findAll()
    }



    fun listCard(card: Card): List<Reminders> {
        try {
            card.dateTime = LocalDate.now()
            card.hour = LocalTime.now().withSecond(0).withNano(0)
            val cardsave = cardRepository.save(card)

            val reminders = remindersRepository.findAll()

            val response = reminders.filter {
                it.date == cardsave.dateTime
                        && it.startTime!! <= cardsave.hour!!.withSecond(0).withNano(0)
                        && it.endTime!! >= cardsave.hour!!.withSecond(0).withNano(0) &&
                        it.isSend == false
            }.sortedBy { it.id }

            if (response.isNotEmpty()) {
                val firstReminder = response.first()

                // Marca el primer recordatorio como enviado
                firstReminder.isSend = true
                remindersRepository.save(firstReminder)

                // Crea y guarda la interacción
                val interaction = Interactions().apply {
                    patientId = firstReminder.patientId
                    title = firstReminder.title
                    dateTime = card.dateTime
                    hour = card.hour!!.withSecond(0).withNano(0)
                }
                interactionsRepository.save(interaction)

                return listOf(firstReminder)
            } else {
                return emptyList()
            }
    }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }



    fun save(reminders: Reminders): Reminders{
        try{
            patientRepository.findById(reminders.patientId)
                ?: throw Exception("Id del paciente no encontrado")
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
