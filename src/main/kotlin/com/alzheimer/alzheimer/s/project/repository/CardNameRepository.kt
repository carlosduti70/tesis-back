package com.alzheimer.alzheimer.s.project.repository

import com.alzheimer.alzheimer.s.project.model.CardName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardNameRepository :JpaRepository<CardName, Long?> {
    fun findById (id: Long?): CardName?
    fun findByCardUid(cardUid: String): CardName?

}