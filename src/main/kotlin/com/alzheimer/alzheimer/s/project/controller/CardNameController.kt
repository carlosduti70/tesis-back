package com.alzheimer.alzheimer.s.project.controller


import com.alzheimer.alzheimer.s.project.model.CardName
import com.alzheimer.alzheimer.s.project.service.CardNameService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/cardName")
class CardNameController {
    @Autowired
    lateinit var cardNameService: CardNameService

    @GetMapping
    fun list (): List<CardName> {
        return cardNameService.list()
    }

    @PostMapping
    fun save (@RequestBody users: CardName):ResponseEntity<CardName>{
        return ResponseEntity(cardNameService.save(users), HttpStatus.OK)
    }

    @PutMapping
    fun update (@RequestBody users: CardName):ResponseEntity<CardName>{
        return ResponseEntity(cardNameService.update(users), HttpStatus.OK)
    }

    @PatchMapping
    fun updateName (@RequestBody users: CardName):ResponseEntity<CardName>{
        return ResponseEntity(cardNameService.updateName(users), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun listById (@PathVariable("id") id: Long): ResponseEntity<*>{
        return ResponseEntity(cardNameService.listById (id), HttpStatus.OK)

    }

    @DeleteMapping("/delete/{id}")
    fun delete (@PathVariable("id") id: Long):Boolean?{
        return cardNameService.delete(id)
    }
}