package com.ashago.ashago.service

import com.ashago.ashago.entity.User
import com.ashago.ashago.repository.UserRepository
import com.ashago.ashago.resp.CommonResp
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
                        .withIgnorePaths("userName")
                        .withIgnorePaths("activated"))
        if (userRepository.exists(userExample)) {
            return CommonResp("E001", "User exist")
        }
        user.activated = false
        if (user.subscribed == null) {
            user.subscribed = false
        }
        userRepository.saveAndFlush(user)
        if (user.id == null) {
            return CommonResp("E002", "sign up failed")
        }

        //TODO:发送邮件


        return CommonResp.success()
                .appendData("userId", user.userId)
                .appendData("userIdType", user.userIdType)
                .appendData("userName", user.userName)
    }

    fun login(user: User): CommonResp {
        val userExample: Example<User> = Example.of(user,
                ExampleMatcher.matching()
                        .withIgnorePaths("subscribed")
                        .withIgnorePaths("userName")
                        .withIgnorePaths("activated")
        )
        val userFinding: Optional<User> = userRepository.findOne(userExample)
        return if (userFinding.isPresent) {
            val t = UUID.randomUUID().toString()
            CommonResp.success()
                    .appendData("t", t)
                    .appendData("userId", userFinding.get().userId)
                    .appendData("sessionId", userFinding.get().userId
                            .replace("-","").replace("a","e").reversed())
        } else {
            CommonResp("E003", "email or pass failed")
        }
    }

}