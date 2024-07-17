package com.alzheimer.alzheimer.s.project.service

import com.alzheimer.alzheimer.s.project.model.Patient
import com.alzheimer.alzheimer.s.project.model.UserPatientView
import com.alzheimer.alzheimer.s.project.repository.PatientRepository
import com.alzheimer.alzheimer.s.project.repository.UserPatientViewRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PatientService {
    @Autowired
    lateinit var patientRepository: PatientRepository

    @Autowired
    lateinit var userPatientViewRepository: UserPatientViewRepository

    fun list (): List<Patient> {
        return patientRepository.findAll()
    }

    fun listUserPatient (username:String): UserPatientView{
        return userPatientViewRepository.findByUsername(username)
    }

    fun save(patient: Patient): Patient{
        try{
            return patientRepository.save(patient)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun update(patient: Patient): Patient{
        try {
            patientRepository.findById(patient.id)
                ?: throw Exception("ID no existe")

            return patientRepository.save(patient)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateName(patient:Patient): Patient{
        try{
            val response = patientRepository.findById(patient.id)
                ?: throw Exception("ID no existe")
            response.apply {
                address=patient.address
            }
            return patientRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun listById (id:Long?):Patient?{
        return patientRepository.findById(id)
    }

    fun delete (id: Long?):Boolean?{
        try{
            val response = patientRepository.findById(id)
                ?: throw Exception("ID no existe")
            patientRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

}
