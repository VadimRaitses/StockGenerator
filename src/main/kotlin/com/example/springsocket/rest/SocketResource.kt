package com.example.springsocket.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView


@Controller
class SocketResource {


    @RequestMapping("/")
    fun blog(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index.html"
        return modelAndView
    }
}
