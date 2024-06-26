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
@Table(name = "alarm")
class Alarm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null

    var title: String? = null

    var time: LocalTime? = null
    var repeat: Boolean? = true
    var date: LocalDate? = null
    @Column(name = "patient_id")
    var patientId: Long? = null
}