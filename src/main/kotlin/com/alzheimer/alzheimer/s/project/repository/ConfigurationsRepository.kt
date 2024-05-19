package com.alzheimer.alzheimer.s.project.repository

import com.alzheimer.alzheimer.s.project.model.Configurations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConfigurationsRepository:JpaRepository<Configurations, Long?> {
fun findById (id: Long?): Configurations?
}