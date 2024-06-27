package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.Alarm
import com.alzheimer.alzheimer.s.project.repository.AlarmRepository
import com.alzheimer.alzheimer.s.project.repository.PatientRepository
//import com.alzheimer.alzheimer.s.project.repository.CaragiversRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.time.LocalTime
import java.util.Optional

@Service
class AlarmService {
    @Autowired
    lateinit var alarmRepository: AlarmRepository

    @Autowired
    lateinit var patientRepository: PatientRepository


    fun list (): List<Alarm> {
        return alarmRepository.findAll()
    }

    fun getAlarmByCurrentDateTime(): Alarm? {
        val alarms = alarmRepository.findAll()

        return alarms.firstOrNull {
            it.date == LocalDate.now() && it.repeat== true && it.time == LocalTime.now().withSecond(0).withNano(0)
        }
    }


    fun listById(id: Long): Optional<Alarm> {
        return alarmRepository.findById(id)
    }

    fun save(alarm: Alarm): Alarm {
        try {
            patientRepository.findById(alarm.patientId)
                ?: throw Exception("Id del paciente no encontrado")
            return alarmRepository.save(alarm)
        }
        catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
        }
    }


    fun update(alarm: Alarm): Alarm {
        try {
            val existingAlarm = alarm.id?.let { alarmRepository.findById(it) }
            if (existingAlarm != null) {
                if (existingAlarm.isPresent) {
                    return alarmRepository.save(alarm)
                } else {
                    throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")
                }
            }
            // Agrega el return para manejar el caso cuando existingAlarm es null
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
        }
    }



    fun delete(id: Long?): Boolean {
        try {
            val response = alarmRepository.findById(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "ID no existe")
            alarmRepository.deleteById(id!!)
            return true
        }
        catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.message)
        }
    }


}
