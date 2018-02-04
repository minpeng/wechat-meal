package com.meal.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * Created by pengmin on 2018/2/3.
 */
public class WeixinController {
    private final Logger logger=LoggerFactory.getLogger(WeixinController.class);

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        logger.info("进入auth方法。。。");
        logger.info("code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd898fcb01713c658&secret=29d8a650db31472aa87800e3b0d739f2&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        logger.info("response={}", response);
    }
}
