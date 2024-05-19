package com.alzheimer.alzheimer.s.project.controller

import com.alzheimer.alzheimer.s.project.model.Alarm
import com.alzheimer.alzheimer.s.project.model.Card
import com.alzheimer.alzheimer.s.project.service.AlarmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/alarm")
class AlarmController {
    @Autowired
    lateinit var alarmService: AlarmService

    @GetMapping
    fun list (): List<Alarm> {
        return alarmService.list()
    }

    @GetMapping("/currentalarm")
    fun getCurrentAlarm(): ResponseEntity<Alarm> {
        val alarm = alarmService.getAlarmByCurrentDateTime()

        return if (alarm != null) {
            ResponseEntity.ok(alarm)
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }


    @PostMapping
    fun save(@RequestBody alarm: Alarm): ResponseEntity<Alarm> {
        return ResponseEntity.ok(alarmService.save(alarm))
    }

    @PutMapping
    fun update(@RequestBody alarm: Alarm): ResponseEntity<Alarm> {
        return ResponseEntity.ok(alarmService.update(alarm))
    }

    @GetMapping("/{id}")
    fun listById(@PathVariable("id") id: Long): ResponseEntity<Alarm> {
        return ResponseEntity.ok(alarmService.listById(id).orElse(null))
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<String> {
        val success = alarmService.delete(id)
        return if (success) {
            ResponseEntity.ok("Deleted successfully")
        } else {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Deletion failed")
        }
    }
}
