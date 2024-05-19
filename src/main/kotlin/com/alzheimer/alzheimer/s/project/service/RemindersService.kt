package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.Card
import com.alzheimer.alzheimer.s.project.model.Reminders
import com.alzheimer.alzheimer.s.project.repository.CardRepository
import com.alzheimer.alzheimer.s.project.repository.RemindersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class RemindersService {
    @Autowired
    private lateinit var reminders: RemindersRepository

    @Autowired
    lateinit var remindersRepository: RemindersRepository

    @Autowired
    lateinit var cardRepository: CardRepository

    fun list (): List<Reminders> {
        return remindersRepository.findAll()
    }

//    fun listCard(card: Card): Reminders? {
//        val dateTime = card.dateTime
//        val hour = card.hour
//
//        // Validar que la fecha y hora sean válidas
//        if (dateTime!!.isBefore(LocalDate.now()) || hour!!.isBefore(LocalTime.now())) {
//            throw IllegalArgumentException("La fecha y la hora deben ser futuras.")
//        }
//
//        // Obtener todos los recordatorios
//        val remindersList = remindersRepository.findAll()
//
//        // Obtener el recordatorio que coincide con la fecha y la hora de la tarjeta
//        val reminder = remindersList.firstOrNull {
//            it.date == dateTime &&
//                    it.startTime!! <= hour &&
//                    hour >= it.endTime
//        }
//
//        return reminder
//    }

    fun listCard(card: Card): Reminders? {
        val reminder = remindersRepository.findAll()

        return reminder.firstOrNull{
            it.date == card.dateTime && it.startTime!! <= card.hour && it.endTime!! >= card.hour
        }
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
