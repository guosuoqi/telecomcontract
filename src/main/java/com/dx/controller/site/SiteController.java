package com.dx.controller.site;

import com.dx.controller.contract.ContractController;
import com.dx.model.contract.Contract;
import com.dx.model.site.*;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/site")
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

    //查询3/4/5GRRU页面
    @RequestMapping(value = "queryRRU", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryRRU(Integer page, Integer rows, EquipmentRRUAAU equipmentRRUAAU) {
        PageResult pageResult = siteService.queryRRU(page, rows, equipmentRRUAAU);
        return pageResult;
    }
  //查询3/4/5GRRU页面
    @RequestMapping(value = "/queryBoFen", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryBoFen(Integer page, Integer rows, EquipmentBoFen equipmentBoFen) {
        PageResult pageResult = siteService.queryBofen(page, rows, equipmentBoFen);
        return pageResult;
    }


    //3/4/5GRRU页面新增
    @RequestMapping("add3GRRU")
    @ResponseBody
    public HashMap<String, String> add3GRRU(EquipmentRRUAAU equipmentRRUAAU) {
        HashMap<String, String> result= new HashMap<String, String>();;
        try {
            if ((equipmentRRUAAU != null )) {
                if (equipmentRRUAAU.getId()!=null){
                    siteService.updateRRU(equipmentRRUAAU);
                    result.put("code", "3");
                    result.put("msg", "修改成功！");
                    logger.info(this.getClass() + "，修改成功！");
                    return result;
                }
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


    //查询3/4/5GBBU页面
    @RequestMapping(value = "queryStieManager", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryStieManager(Integer page, Integer rows, SitManager sitManager) {
        PageResult pageResult = siteService.queryStieManager(page, rows, sitManager);
        return pageResult;
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

    //波分页面批量删除
    @RequestMapping("delAllBofen")
    @ResponseBody
    public  HashMap<String, String> delAllBofen(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   siteService.delAllBofen(ids);;
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

    //查询OLT页面
    @RequestMapping(value = "queryOlt", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryOlt(Integer page, Integer rows, EquipmentOLT equipmentOLT) {
        PageResult pageResult = siteService.queryOlt(page, rows, equipmentOLT);
        return pageResult;
    }
    //Olt页面新增
    @RequestMapping("addOlt")
    @ResponseBody
    public HashMap<String, String> addOlt(EquipmentOLT equipmentOLT) {
        HashMap<String, String> result= new HashMap<String, String>();
        try {
            if ((equipmentOLT != null )) {
                if (equipmentOLT.getId()!=null){
                    siteService.updateOlt(equipmentOLT);
                    result.put("code", "3");
                    result.put("msg", "修改成功！");
                    logger.info(this.getClass() + "，修改成功！");
                    return result;
                }
                if (!siteService.addOlt(equipmentOLT)) {
                    result.put("code", "1");
                    result.put("msg", "Olt新增失败！");
                    logger.info(this.getClass() + "，Olt新增失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "Olt新增失败！");
                logger.info(this.getClass() + "，Olt新增失败！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "Olt新增失败！");
            logger.info(this.getClass() + "，Olt新增失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }
    //sitManager页面新增
    @RequestMapping("insertStation")
    @ResponseBody
    public HashMap<String, String> insertStation(SitManager sitManager) {
        HashMap<String, String> result= new HashMap<String, String>();
        if (sitManager != null ) {
            List<SitManager> sitManagers = new ArrayList<>();
            sitManagers.add(sitManager);
            if (siteService.insertStation(sitManagers)) {
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }
            result = new HashMap<String, String>();
            result.put("code", "1");
            result.put("msg", "失敗！");
            return result;
        }else{
            result = new HashMap<String, String>();
            result.put("code", "1");
            result.put("msg", "操作失败！");
            return result;
        }
    }

    //3/4/5GRRU页面批量删除
    @RequestMapping("delAllOlt")
    @ResponseBody
    public  HashMap<String, String> delAllOlt(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   siteService.delAllOlt(ids);;
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
    //站点页面批量删除
    @RequestMapping("delAllSit")
    @ResponseBody
    public  HashMap<String, String> delAllSit(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   siteService.delAllSit(ids);;
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


    //查询IPRAN页面
    @RequestMapping(value = "queryIPRAN", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryIPRAN(Integer page, Integer rows, EquipmentIPRAN equipmentIPRAN) {
        PageResult pageResult = siteService.queryIPRAN(page, rows, equipmentIPRAN);
        return pageResult;
    }
    //Olt页面新增
    @RequestMapping("addIPRAN")
    @ResponseBody
    public HashMap<String, String> addIPRAN(EquipmentIPRAN equipmentIPRAN) {
        HashMap<String, String> result= new HashMap<String, String>();
        try {
            if ((equipmentIPRAN != null )) {
                if (equipmentIPRAN.getId()!=null){
                    siteService.updateIPRAN(equipmentIPRAN);
                    result.put("code", "3");
                    result.put("msg", "修改成功！");
                    logger.info(this.getClass() + "，修改成功！");
                    return result;
                }
                if (!siteService.addIPRAN(equipmentIPRAN)) {
                    result.put("code", "1");
                    result.put("msg", "IPRAN新增失败！");
                    logger.info(this.getClass() + "，IPRAN新增失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "IPRAN新增失败！");
                logger.info(this.getClass() + "，IPRAN新增失败！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "IPRAN新增失败！");
            logger.info(this.getClass() + "，Olt新增失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }

    //3/4/5GRRU页面批量删除
    @RequestMapping("delAllIPRAN")
    @ResponseBody
    public  HashMap<String, String> delAllIPRAN(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   siteService.delAllIPRAN(ids);;
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


    //波分页面新增
    @RequestMapping("/addBoFen")
    @ResponseBody
    public HashMap<String, String> addBoFen(EquipmentBoFen equipmentBoFen) {
        HashMap<String, String> result= new HashMap<String, String>();
        try {
            if ((equipmentBoFen != null )) {
                if (equipmentBoFen.getId()!=null){
                    siteService.updateBoFen(equipmentBoFen);
                    result.put("code", "3");
                    result.put("msg", "修改成功！");
                    logger.info(this.getClass() + "，修改成功！");
                    return result;
                }
                if (!siteService.addBoFen(equipmentBoFen)) {
                    result.put("code", "1");
                    result.put("msg", "波分新增失败！");
                    logger.info(this.getClass() + "，波分新增失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "波分新增失败！");
                logger.info(this.getClass() + "，波分新增失败！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "波分新增失败！");
            logger.info(this.getClass() + "，波分新增失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }
}
