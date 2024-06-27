package com.alzheimer.alzheimer.s.project.dto

import com.alzheimer.alzheimer.s.project.model.Role
import com.alzheimer.alzheimer.s.project.model.RoleEntity

class RegisterRequest {
    var username: String? = null
    var password: String? = null
    var email: String? = null
    var name: String? = null
    var lastName: String? = null
    var roles: List<RoleEntity>? = null
}