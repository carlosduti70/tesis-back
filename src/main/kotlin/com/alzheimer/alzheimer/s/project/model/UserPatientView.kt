package com.alzheimer.alzheimer.s.project.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "user_patient_info")
class UserPatientView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    var userId: Long? = null

    @Column(name = "username")
    val username: String? = null

    @Column(name = "user_name")
    val name: String? = null

    @Column(name = "user_last_name")
    val userLastName: String? = null

    @Column(name = "locked")
    val locked: Boolean? = null

    @Column(name = "disabled")
    val disabled: Boolean? = null

    @Column(name = "patient_id")
    val patientId: Long? = null

    @Column(name = "patient_name")
    val patientName: String? = null

    @Column(name = "patient_last_name")
    val patientLastName: String? = null

    @Column(name = "age")
    val age: LocalDate? = null

    @Column(name = "date_diagnosis")
    val dateDiagnosis: LocalDate? = null

    @Column(name = "address")
    val address: String? = null

    @Column(name = "stage")
    val stage: String? = null
}