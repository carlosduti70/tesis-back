package com.alzheimer.alzheimer.s.project.model

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "cardname")
class CardName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null
    @Column(name = "card_uid")
    var cardUID: String? = null
}