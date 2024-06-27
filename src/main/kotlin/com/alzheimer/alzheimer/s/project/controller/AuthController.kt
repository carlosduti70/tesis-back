package com.alzheimer.alzheimer.s.project.controller

import com.alzheimer.alzheimer.s.project.config.JwtUtil
import com.alzheimer.alzheimer.s.project.dto.LoginDTO
import com.alzheimer.alzheimer.s.project.dto.TokenDTO
import com.alzheimer.alzheimer.s.project.model.Patient
import com.alzheimer.alzheimer.s.project.model.UserEntity
import com.alzheimer.alzheimer.s.project.service.UserSecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@CrossOrigin(methods = [RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.DELETE])
@RequestMapping("/auth")
class AuthController {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    @Autowired
    private val jwtUtil: JwtUtil? = null
    @Autowired
    lateinit var userSecurityService: UserSecurityService

    @PostMapping("/login")
    fun login(@RequestBody loginDto: LoginDTO): ResponseEntity<*>? {
        val login = UsernamePasswordAuthenticationToken(loginDto.username, loginDto.password)
        val authentication: Authentication = authenticationManager!!.authenticate(login)
        val response = TokenDTO().apply { jwt= jwtUtil!!.create(loginDto.username)}
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/logout")
    fun logout() {
        // Invalidar la sesión actual y eliminar el token JWT
        SecurityContextHolder.clearContext()
        // Puedes agregar otras lógicas de limpieza de sesión si es necesario
    }

    @PostMapping("/createuser")
    fun save (@RequestBody userEntity: UserEntity):ResponseEntity<UserEntity>{
        return ResponseEntity(userSecurityService.save(userEntity), HttpStatus.OK)
    }
}