package com.dx.component;

import com.dx.model.Task.TaskModel;
import com.dx.model.common.TaskEnum;
import com.dx.service.contract.ContractServiceImpl;
import com.dx.service.site.SiteServiceImpl;
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
    @Autowired
    private SiteServiceImpl siteServiceImpl ;
    @Autowired
    private EmailUtil emailUtil ;


    @Scheduled(cron ="0 30 8 ? * MON")
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
        try {
            JSONObject jsonObject;
            for (TaskModel task:taskList) {
                jsonObject = JSONObject.fromObject(task.getContent());
                if(emailUtil.sendEmail(jsonObject)){
                    task.setStatus(1);
                    taskServeceImpl.updateTask(task);
                }else {
                    logger.info("{}此条任务执行失败",task.getId());
                }
            }
        } catch (Exception e) {
            logger.info("邮件发送异常："+e.getMessage());
        }

    }
    @Scheduled(cron ="0 0 1 * * ?")
    public void updateContractStart() {
        logger.info("检查合同是否到期！");
        contractServiceImpl.updateContractStatus();
    }
    @Scheduled(cron ="0 0 2 ? * MON")
    public void sendContractTaskToEmail() {
        logger.info("扫描需处理的合同信息！");
        contractServiceImpl.sendContractTaskToEmail();
    }
  @Scheduled(cron ="0 0 1 * * ?")
    public void updageRevisedDade() {
        logger.info("站点信息规整！");
        siteServiceImpl.updageRevisedDade();
    }



}
