package com.alzheimer.alzheimer.s.project.repository

import com.alzheimer.alzheimer.s.project.model.UserPatientView
import org.springframework.data.jpa.repository.JpaRepository

interface UserPatientViewRepository: JpaRepository<UserPatientView, Long?> {

    fun findByUsername(username: String?): UserPatientView
}