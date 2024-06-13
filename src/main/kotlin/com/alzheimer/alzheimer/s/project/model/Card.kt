package com.alzheimer.alzheimer.s.project.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "card")
class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    var id: Long? = null

    @Column(name = "date_time")
    var dateTime: LocalDate? = null
    var hour: LocalTime?= null

    @Column(name = "cardname_id")
    var cardNameId: Long? = null
    @Column(name = "patient_id")
    var patientId: Long? = null
}