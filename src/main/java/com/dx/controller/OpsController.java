package com.dx.controller;

import com.dx.component.ScheduledJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ops")
public class OpsController {

    @Autowired
    private ScheduledJob scheduledJob;
    //合同跑批
    @RequestMapping("/updateContractStart")
    public String updateContractStart() {
        scheduledJob.updateContractStart();
        return "true";
    }
    //邮件跑批
    @RequestMapping("/sendEmail")
    public String sendEmail() {
        scheduledJob.sendEmail();
        return "true";
    }
}
