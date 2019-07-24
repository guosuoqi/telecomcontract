package com.dx.controller.site;

import com.dx.controller.contract.ContractController;
import com.dx.model.contract.Contract;
import com.dx.model.site.EquipmentBBU;
import com.dx.model.site.EquipmentRRUAAU;
import com.dx.model.site.SitManager;
import com.dx.service.site.SiteService;
import com.dx.util.ExceptionPrintUtil;
import com.dx.util.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@RequestMapping("site")
public class SiteController {
    @Autowired
    private SiteService siteService;
    private Logger logger = LoggerFactory.getLogger(SiteController.class);

    //查询3/4/5GBBU页面
    @RequestMapping(value = "queryBBU", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryBBU(Integer page, Integer rows, EquipmentBBU equipmentBBU) {
        PageResult pageResult = siteService.queryBBU(page, rows, equipmentBBU);
        return pageResult;
    }

    //3/4/5GBBU页面新增
    @RequestMapping("add3GBBU")
    @ResponseBody
    public HashMap<String, String> add3GBBU(EquipmentBBU equipmentBBU) {

        HashMap<String, String> result= new HashMap<String, String>();
        try {
            if ((equipmentBBU != null )) {
                if (!siteService.add3GBBU(equipmentBBU)) {
                    result.put("code", "1");
                    result.put("msg", "BBU新增失败！");
                    logger.info(this.getClass() + "，BBU新增失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "BBU新增失败！");
                logger.info(this.getClass() + "，BBU新增失败！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "BBU新增失败！");
            logger.info(this.getClass() + "，BBU新增失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }

    //3/4/5GBBU页面批量删除
    @RequestMapping("delAll")
    @ResponseBody
    public  HashMap<String, String>  delAll(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   siteService.delAll(ids);;
                if (count == 0) {
                    result = new HashMap<String, String>();
                    result.put("code", "1");
                    result.put("msg", "删除失败！");
                    logger.info(this.getClass() + "，删除失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "删除失败！");
                logger.info(this.getClass() + "，删除失败！");
            }

        } catch (Exception e) {
            result = new HashMap<String, String>();
            result.put("code", "3");
            result.put("msg", "删除失败！");
            logger.info(this.getClass() + "，删除失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
        return result;
    }
    //3/4/5GBBU页面修改
    @RequestMapping("update3GBBU")
    @ResponseBody
    public void update3GBBU(EquipmentBBU equipmentBBU){
        siteService.update3GBBU(equipmentBBU);
    }

    //查询3/4/5GRRU页面
    @RequestMapping(value = "queryRRU", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryRRU(Integer page, Integer rows, EquipmentRRUAAU equipmentRRUAAU) {
        PageResult pageResult = siteService.queryRRU(page, rows, equipmentRRUAAU);
        return pageResult;
    }


    //3/4/5GRRU页面新增
    @RequestMapping("add3GRRU")
    @ResponseBody
    public HashMap<String, String> add3GRRU(EquipmentRRUAAU equipmentRRUAAU) {

        HashMap<String, String> result= new HashMap<String, String>();;
        try {
            if ((equipmentRRUAAU != null )) {
                if (!siteService.add3GRRU(equipmentRRUAAU)) {
                    result.put("code", "1");
                    result.put("msg", "RRU新增失败！");
                    logger.info(this.getClass() + "，RRU新增失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "RRU新增失败！");
                logger.info(this.getClass() + "，RRU新增失败！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "RRU新增失败！");
            logger.info(this.getClass() + "，RRU新增失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }

    //3/4/5GRRU页面批量删除
    @RequestMapping("delAllRRU")
    @ResponseBody
    public  HashMap<String, String> delAllRRU(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   siteService.delAllRRU(ids);;
                if (count == 0) {
                    result = new HashMap<String, String>();
                    result.put("code", "1");
                    result.put("msg", "删除失败！");
                    logger.info(this.getClass() + "，删除失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "删除失败！");
                logger.info(this.getClass() + "，删除失败！");
            }

        } catch (Exception e) {
            result = new HashMap<String, String>();
            result.put("code", "3");
            result.put("msg", "删除失败！");
            logger.info(this.getClass() + "，删除失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
        return result;
    }
    //3/4/5GRRU页面修改
    @RequestMapping("updateRRU")
    @ResponseBody
    public void updateRRU(EquipmentRRUAAU equipmentBBRRUAAU){
        siteService.updateRRU(equipmentBBRRUAAU);
    }


    //查询3/4/5GBBU页面
    @RequestMapping(value = "queryStieManager", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryStieManager(Integer page, Integer rows, SitManager sitManager) {
        PageResult pageResult = siteService.queryStieManager(page, rows, sitManager);
        return pageResult;
    }

}
