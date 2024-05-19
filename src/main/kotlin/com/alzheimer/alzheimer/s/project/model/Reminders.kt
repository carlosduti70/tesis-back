package com.alzheimer.alzheimer.s.project.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Time
import java.time.LocalDate
import java.time.LocalTime
import java.util.Date

@Entity
@Table(name = "reminders")
class Reminders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    var title: String? = null
    var description: String? = null
    var date: LocalDate? = null

    @Column(name = "start_time")
    var startTime: LocalTime? = null
    @Column(name = "end_time")
    var endTime: LocalTime? = null
    var status: String? = null
    var repeat: Boolean? = null

    @Column(name = "caregivers_id")
    var caregiversId: Long? = null
    @Column(name = "patient_id")
    var patientId: Long? = null
    @Column(name = "card_id")
    var cardId: Long? = null
}
