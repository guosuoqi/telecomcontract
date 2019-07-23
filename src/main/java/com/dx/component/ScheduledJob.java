package com.dx.component;

import com.dx.model.Task.TaskModel;
import com.dx.model.common.TaskEnum;
import com.dx.service.contract.ContractServiceImpl;
import com.dx.service.task.TaskServeceImpl;
import com.dx.util.EmailUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ScheduledJob {
    private Logger logger = LoggerFactory.getLogger(ScheduledJob.class);
    @Autowired
    private ContractServiceImpl contractServiceImpl ;
    @Autowired
    private TaskServeceImpl taskServeceImpl ;


    @Scheduled(cron ="0 0 7 * * ?")
    public void sendEmail() {
        logger.info("开始定时发送邮件！");
        TaskModel model = new TaskModel();
        model.setType(TaskEnum.SEND_EMAIL.getKey());
        model.setStatus(0);
        List<TaskModel> taskList = taskServeceImpl.queryTask(model);
        if(taskList==null || taskList.isEmpty()){
            logger.info("没有待发送邮件任务！");
            return;
        }
        for (TaskModel task:taskList) {
            String content = task.getContent();
            if(EmailUtil.sendEmail(JSONObject.fromObject(content))){
                task.setStatus(1);
                taskServeceImpl.updateTask(task);
            }else {
                logger.info("{}此条任务执行失败",task.getId());
            }
        }

    }
    @Scheduled(cron ="0 0 2 * * ?")
    public void updateContractStart() {
        logger.info("检查合同是否到期！");
        contractServiceImpl.updateContractStatus();
        contractServiceImpl.sendContractTaskToEmail();
    }



}
