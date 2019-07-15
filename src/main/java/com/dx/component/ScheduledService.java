package com.dx.component;

import com.dx.service.contract.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledService {
    private Logger logger = LoggerFactory.getLogger(ScheduledService.class);
    @Autowired
    private ContractService contractService;


  //@Scheduled(cron ="0 0/2 * * * ?")
    public void scheduled() throws Exception {
       logger.info("开始定时任务！");
     /*  List<ReportInfo> reportInfos=findUpdateService.querybyStart();
       boolean flag = findUpdateService.findFlag(reportInfos);
        int msgFlag = dealReportMapper.queryStatus0();
        if(msgFlag > 0){
            evaluationThreadSerivce.sendAdminMsg("-2",new ReportInfo());
        }

       logger.info("输出的flag"+flag);*/
   }

}
