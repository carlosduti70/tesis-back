package com.alzheimer.alzheimer.s.project.controller


import com.alzheimer.alzheimer.s.project.model.Patient
import com.alzheimer.alzheimer.s.project.model.Reminders
import com.alzheimer.alzheimer.s.project.service.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/patient")
class PatientController {
    @Autowired
    lateinit var patientService: PatientService

    @GetMapping
    fun list (): List<Patient> {
        return patientService.list()
    }

    @GetMapping("/get-home/{username}")
    fun listUserPatient (@PathVariable("username") username: String): ResponseEntity<*> {
        val response= patientService.listUserPatient(username)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    fun save (@RequestBody patient: Patient):ResponseEntity<Patient>{
        return ResponseEntity(patientService.save(patient), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody patient: Patient):ResponseEntity<Patient>{
        return ResponseEntity(patientService.update(patient), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody patienteInfo: Patient):ResponseEntity<Patient>{
        return ResponseEntity(patientService.updateName(patienteInfo), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(patientService.listById (id), HttpStatus.OK)

    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return patientService.delete(id)
    }
}