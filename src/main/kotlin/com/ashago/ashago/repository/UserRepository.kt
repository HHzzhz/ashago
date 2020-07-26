package com.ashago.ashago.repository

import com.ashago.ashago.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
}