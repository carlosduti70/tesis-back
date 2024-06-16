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
    val id: Long? = 0
    @Column(name = "card_uid")
    var cardUid: String = ""
    @Column(name = "card_id")
    var cardId: Long? = null
}