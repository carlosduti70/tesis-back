package com.alzheimer.alzheimer.s.project.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime

@Entity
@Table(name = "interactions")
class Interactions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    var id: Long? = null

    var title: String? = null

    @Column(name = "date_time")
    var dateTime: LocalDate? = null

    var hour: LocalTime?= null

    @Column(name = "card_id")
    var cardId: Long? = null
}