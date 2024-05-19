package com.alzheimer.alzheimer.s.project.controller


import com.alzheimer.alzheimer.s.project.model.Card
import com.alzheimer.alzheimer.s.project.model.Caregivers
import com.alzheimer.alzheimer.s.project.model.Reminders
import com.alzheimer.alzheimer.s.project.service.RemindersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeParseException
import java.util.Timer
import java.util.concurrent.TimeUnit
import kotlin.concurrent.schedule

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/reminders")
class RemindersController {
    @Autowired
    lateinit var remindersService: RemindersService

    @GetMapping
    fun list (): List<Reminders> {
        return remindersService.list()
    }

//    @GetMapping("/cardreminders")
//    fun getReminders(
//        @RequestParam dateTime: String,
//        @RequestParam hour: String
//    ): ResponseEntity<Reminders> {
//        try {
//            val card = Card(dateTime = dateTime, hour = hour)
//            val reminder = remindersService.listCard(card)
//
//            return if (reminder != null) {
//                // Enviar el recordatorio durante 30 segundos
//                val timer = Timer()
//                timer.schedule(object : TimerTask() {
//                    override fun run() {
//                        // Aquí puedes detener el envío del recordatorio o hacer cualquier otra acción necesaria
//                        println("Deteniendo el envío del recordatorio después de 30 segundos")
//                        timer.cancel() // Detiene el temporizador después de 30 segundos
//                    }
//                }, TimeUnit.SECONDS.toMillis(30))
//
//                ResponseEntity.ok(reminder)
//            } else {
//                ResponseEntity.notFound().build()
//            }
//        } catch (e: IllegalArgumentException) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
//        }
//    }


//    @PostMapping("/cardreminders")
//    fun getReminders(
//        @RequestBody card: Card
//    ): ResponseEntity<Reminders> {
//        val reminder = remindersService.listCard(card)
//
//        return if (reminder != null){
//            ResponseEntity.ok(reminder)
//        } else{
//            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
//        }
//    }


//    @GetMapping("/cardreminders")
//    fun getReminders(@RequestBody card: Card): ResponseEntity<Reminders> {
//        val reminder = remindersService.listCard(card)
//
//        return if (reminder != null) {
//            ResponseEntity.ok(reminder)
//        } else {
//            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
//        }
//    }



    @PostMapping
    fun save (@RequestBody reminders: Reminders):ResponseEntity<Reminders>{
        return ResponseEntity(remindersService.save(reminders), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody reminders: Reminders):ResponseEntity<Reminders>{
        return ResponseEntity(remindersService.update(reminders), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody reminders: Reminders):ResponseEntity<Reminders>{
        return ResponseEntity(remindersService.updateName(reminders), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(remindersService.listById (id), HttpStatus.OK)

    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return remindersService.delete(id)
    }
}