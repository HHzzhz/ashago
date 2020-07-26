package com.ashago.ashago.controller

import com.ashago.ashago.entity.User
import com.ashago.ashago.resp.CommonResp
import com.ashago.ashago.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Email

@RestController
@CrossOrigin
class UserController
@Autowired constructor(
        private val userService: UserService
) {

    @RequestMapping("/users", method = [RequestMethod.POST])
    fun signUp(@RequestBody user: User): CommonResp {
        return userService.signUp(user);
    }

    @RequestMapping("/users/{email}:login", method = [RequestMethod.POST])
    fun login(@RequestBody user: User, @PathVariable("email") email: String): CommonResp {
        if (email != user.email) {
            return CommonResp("E002", "email not verified")
        }

        return userService.login(user)
    }

}