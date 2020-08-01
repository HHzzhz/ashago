package com.ashago.ashago.controller

import com.ashago.ashago.entity.User
import com.ashago.ashago.resp.CommonResp
import com.ashago.ashago.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse

@RestController
@CrossOrigin
class UserController
@Autowired constructor(
        private val userService: UserService
) {

    @RequestMapping("/user/register", method = [RequestMethod.POST])
    fun signUp(@RequestBody user: User): CommonResp {
        return userService.signUp(user);
    }

    @RequestMapping("/user/login", method = [RequestMethod.POST])
    fun login(@RequestBody user: User, response: HttpServletResponse): CommonResp {
        val resp: CommonResp = userService.login(user)
        val cookie = Cookie("sessionId", resp.getData("sessionId").toString())
        response.addCookie(cookie)
        return resp
    }

}