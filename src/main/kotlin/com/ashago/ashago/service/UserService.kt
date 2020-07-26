package com.ashago.ashago.service

import com.ashago.ashago.entity.User
import com.ashago.ashago.repository.UserRepository
import com.ashago.ashago.resp.CommonResp
import com.ashago.ashago.resp.LoginResp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) {
    fun signUp(user: User): CommonResp {
        val userExample: Example<User> = Example.of(user,
                ExampleMatcher.matching()
                        .withIgnorePaths("token")
                        .withIgnorePaths("subscribed")
                        .withIgnorePaths("nickName"))
        if (userRepository.exists(userExample)) {
            return CommonResp("E001", "User exist")
        }
        userRepository.saveAndFlush(user)
        if (user.id == null) {
            return CommonResp("E002", "sign up failed")
        }
        return LoginResp(code = "0", msg = "success", userId = user.id, nickName = user.nickName)
    }

    fun login(user: User): CommonResp {
        val userExample: Example<User> = Example.of(user,
                ExampleMatcher.matching()
                        .withIgnorePaths("subscribed")
                        .withIgnorePaths("nickName"))
        val userFinding: Optional<User> = userRepository.findOne(userExample)
        if (userFinding.isPresent) {
            return LoginResp(code = "0", msg = "success", userId = userFinding.get().id, nickName = userFinding.get().nickName)
        } else {
            return CommonResp("E003", "email or pass failed")
        }
    }

}