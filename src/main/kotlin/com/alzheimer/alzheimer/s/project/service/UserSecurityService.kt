package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.dto.RegisterRequest
import com.alzheimer.alzheimer.s.project.dto.TokenDTO
import com.alzheimer.alzheimer.s.project.model.UserEntity
import com.alzheimer.alzheimer.s.project.repository.PatientRepository
import com.alzheimer.alzheimer.s.project.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Service
class UserSecurityService: UserDetailsService {
    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var patientRepository: PatientRepository
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

    fun getLastPatientId(): Long? {
        return patientRepository.findLastPatientId()
    }

    fun register(registerRequest: RegisterRequest): UserEntity{
        val newUser= UserEntity()
        val passwordEncoder = BCryptPasswordEncoder()
        newUser.apply {
            username = registerRequest.username
            password = passwordEncoder.encode(registerRequest.password)
            disabled = false
            locked = false
            name= registerRequest.name
            lastName= registerRequest.lastName
            patientId= getLastPatientId()
        }
        val response = userRepository.save(newUser)

        return response
    }
}