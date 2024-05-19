package com.alzheimer.alzheimer.s.project.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Entity
@Table(name = "interactions")
class Interactions {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    var id: Long? = null

    var message: String? = null

    var response: String? = null

    @Column(name = "date_time_interaction")
    var dateTimeInteractions: Timestamp? = null

    var request: Boolean? = null

    @Column(name = "patient_id")
    var patientId: Long? = null
}