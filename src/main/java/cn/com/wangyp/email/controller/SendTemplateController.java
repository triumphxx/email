package cn.com.wangyp.email.controller;

import cn.com.wangyp.email.send.SendTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:wangyupeng
 * @Date:2019-12-10
 * @Time:22:36
 * @desc:
 **/
@RestController
@RequestMapping("/send")
public class SendTemplateController {

    @Autowired
    SendTemplateService sendTemplateService;

    @RequestMapping(value = "/template/email", method = RequestMethod.POST)
    public String sendTemplateApi() {

        return sendTemplateService.sendTemplate();
    }
}
