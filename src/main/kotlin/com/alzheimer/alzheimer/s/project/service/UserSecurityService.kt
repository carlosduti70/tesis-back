package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.dto.RegisterRequest
import com.alzheimer.alzheimer.s.project.dto.TokenDTO
import com.alzheimer.alzheimer.s.project.model.UserEntity
import com.alzheimer.alzheimer.s.project.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.server.ResponseStatusException

@Service
class UserSecurityService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null
    @Override
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val userEntity = userRepository.findByUsername(username)
            ?: throw
            UsernameNotFoundException(
                "User $username not found."
            )

        val roles: Array<String?> = userEntity.roles?.map {
                role -> role.role }!!.toTypedArray()

        return User.builder()
            .username(userEntity.username)
            .password(userEntity.password)
            .roles(*roles)
            .accountLocked(userEntity.locked!!)
            .disabled(userEntity.disabled!!)
            .build()
    }

    fun save(userEntity: UserEntity): UserEntity{
        try {
            return userRepository.save(userEntity)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun register(request: RegisterRequest): TokenDTO? {
        val userEntity = UserEntity().apply {
            username = request.email
            password = passwordEncoder.encode(request.password)
            email = request.email
            locked = false
            disabled = false
            name = request.name
            lastName = request.lastName
            roles = request.roles
            }
        )
        }

}

//fun register(request: RegisterRequest): AuthenticationResponse? {
//    val userAuth= UserAuth().apply {
//        firstname= request.firstname
//        lastname=request.lastname
//        email=request.email
//        password1= passwordEncoder?.encode(request.password)
//        role= request.role
//
//    }
//
//    repository.save(userAuth)
//    val jwtToken = jwtService.generateToken(userAuth,userAuth.role)
//    return AuthenticationResponse().apply {
//        token=jwtToken
//    }
//
//}