package com.meal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengm on 2018/1/26.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("")
    public  String index(){
        return "hello wechat-meal";
    }
}