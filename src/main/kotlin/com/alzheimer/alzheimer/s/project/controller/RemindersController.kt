package com.alzheimer.alzheimer.s.project.controller


import com.alzheimer.alzheimer.s.project.model.Card
//import com.alzheimer.alzheimer.s.project.model.Caregivers
import com.alzheimer.alzheimer.s.project.model.Reminders
import com.alzheimer.alzheimer.s.project.repository.CardRepository
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


    @PostMapping("/card-reminders")
    fun getReminders(@RequestBody card: Card): ResponseEntity<*> {
        val reminder = remindersService.listCard(card)

        return ResponseEntity.ok(reminder)
    }



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