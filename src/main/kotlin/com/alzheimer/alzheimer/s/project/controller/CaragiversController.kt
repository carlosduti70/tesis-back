package com.alzheimer.alzheimer.s.project.controller


import com.alzheimer.alzheimer.s.project.model.Caregivers
import com.alzheimer.alzheimer.s.project.service.CaragiversService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/caragivers")
class CaragiversController {
    @Autowired
    lateinit var caragiversService: CaragiversService

    @GetMapping
    fun list (): List<Caregivers> {
        return caragiversService.list()
    }

    @PostMapping
    fun save (@RequestBody users: Caregivers):ResponseEntity<Caregivers>{
        return ResponseEntity(caragiversService.save(users), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody users: Caregivers):ResponseEntity<Caregivers>{
        return ResponseEntity(caragiversService.update(users), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody users: Caregivers):ResponseEntity<Caregivers>{
        return ResponseEntity(caragiversService.updateName(users), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(caragiversService.listById (id), HttpStatus.OK)

    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return caragiversService.delete(id)
    }
}