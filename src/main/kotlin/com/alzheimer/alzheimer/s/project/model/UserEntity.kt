package com.alzheimer.alzheimer.s.project.model
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "users")
class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null


    var password: String? = null

    @Column(length = 50)
    var username: String? = null

    @Column(nullable = false)
    var locked: Boolean? = null
    var disabled: Boolean? = null

    var name : String? = null
    @Column(name = "last_name")
    var lastName: String? = null

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    var roles: List<RoleEntity>? = null

    @Column(name = "patient_id")
    var patientId: Long? = null

}