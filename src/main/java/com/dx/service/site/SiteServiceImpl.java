package com.dx.service.site;

import com.alibaba.fastjson.JSON;
import com.dx.mapper.site.SiteMapper;
import com.dx.model.common.PowerEnum;
import com.dx.model.common.SiteEnum;
import com.dx.model.contract.Contract;
import com.dx.model.site.*;
import com.dx.util.DateUtils;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SiteServiceImpl implements SiteService{

    @Autowired
    private SiteMapper siteMapper;

    @Override
    public PageResult queryBBU(Integer page, Integer rows, EquipmentBBU equipmentBBU) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("equipmentBBU",equipmentBBU);
        int  count=siteMapper.queryBBUCount(params);
        pageResult.setTotal(count);
        PageUtil<EquipmentBBU> pageUtil = new PageUtil<EquipmentBBU>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<EquipmentBBU> list = siteMapper.queryBBU(params);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public boolean add3GBBU(EquipmentBBU equipmentBBU) {
        List<EquipmentBBU> bbuList=new ArrayList<>();
        bbuList.add(equipmentBBU);
        SitManager sit = new SitManager();
        sit.setDxCode(equipmentBBU.getDxCode());
        if(SiteEnum.T_BBU.getKey()==equipmentBBU.getNetworkType()){
            sit.setThreeBbuCount(1);
        }else if(SiteEnum.F_BBU.getKey()==equipmentBBU.getNetworkType()){
            sit.setFourBbuCount(1);
        }else if(SiteEnum.FIVE_BBU.getKey()==equipmentBBU.getNetworkType()){
            sit.setFiveBbuCount(1);
        }else{
            return false;
        }
        siteMapper.updateSite(sit);
        return siteMapper.add3GBBU(bbuList);
    }

    @Override
    public int delAll(String ids) {
        return siteMapper.delAll(ids);
    }

    @Override
    public EquipmentBBU query3GBBUById(Integer id) {
        return siteMapper.query3GBBUById(id);
    }

    @Override
    public void update3GBBU(EquipmentBBU equipmentBBU) {
        siteMapper.update3GBBU(equipmentBBU);
    }

    @Override
    public PageResult queryRRU(Integer page, Integer rows, EquipmentRRUAAU equipmentRRUAAU) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("equipmentRRUAAU",equipmentRRUAAU);
        int  count=siteMapper.queryRRUCount(params);
        pageResult.setTotal(count);
        PageUtil<EquipmentRRUAAU> pageUtil = new PageUtil<EquipmentRRUAAU>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<EquipmentRRUAAU> list = siteMapper.queryRRU(params);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public boolean add3GRRU(EquipmentRRUAAU equipmentRRUAAU) {
        List<EquipmentRRUAAU> rruList=new ArrayList<>();
        rruList.add(equipmentRRUAAU);
        return siteMapper.add3GRRU(rruList);
    }

    @Override
    public int delAllRRU(String ids) {
        return siteMapper.delAllRRU(ids);
    }

    @Override
    public void updateRRU(EquipmentRRUAAU equipmentRRUAAU) {
        siteMapper.updateRRU(equipmentRRUAAU);
    }

    @Override
    public EquipmentRRUAAU query3GRRUById(Integer id) {
        return siteMapper.query3GRRUById(id);
    }

    @Override
    public PageResult queryStieManager(Integer page, Integer rows, SitManager sitManager) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("sitManager",sitManager);
        int  count=siteMapper.queryStieManagerCount(params);
        pageResult.setTotal(count);
        PageUtil<SitManager> pageUtil = new PageUtil<SitManager>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<SitManager> list = siteMapper.queryStieManager(params);
        pageResult.setRows(list);
        return pageResult;
    }

    public List<EquipmentBBU> queryBBUByIdsAndType(String ids) {
        return siteMapper.queryBBUByIdsAndType(ids);
    }

    public List<EquipmentRRUAAU> queryRRByIdsAndType(String ids) {
        return siteMapper.queryRRByIdsAndType(ids);
    }

    public boolean addRRUList(Sheet sheet) {
        List<EquipmentRRUAAU> RRUList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        Integer tp=null;
        EquipmentRRUAAU rru;
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            try {
                Row row = (Row) sheet.getRow(i);
                if(row ==null){
                    break;//整行为空，跳出
                }
                //获取每个单元格
                /**
                 * "电信编码,rru编码,rru名称,网管员id,网管员,类型编码"
                 */
                //电信编码
                String dxCode = getCellVal(row.getCell(0));//第一个单元格
                //bbu编码
                String rruCode = getCellVal(row.getCell(1));
                //bbu名称
                String rruName = getCellVal(row.getCell(2));
                //耗电量
                String power = getCellVal(row.getCell(3));
                //网管员id
                String userId = getCellVal(row.getCell(4));
                if(userId==null || userId.equals("")){
                    userId="1";
                }
                //网管员
                String userName = getCellVal(row.getCell(5));
                //类型编码
                String type = getCellVal(row.getCell(6));
                if(tp==null){
                    try{
                        tp=Integer.valueOf(type);
                    }catch (Exception e){
                    }
                }
                if(dxCode==null ||rruCode==null){
                    continue;
                }
                rru=new EquipmentRRUAAU();
                if(power==null ||power.isEmpty()){
                    Double bbu1 = getPower(type, "rru");
                    rru.setPower(bbu1);
                }else {
                    rru.setPower(Double.valueOf(power));
                }
                rru.setDxCode(dxCode);
                rru.setRruCode(rruCode);
                rru.setRruName(rruName);
                rru.setNetCareId(Integer.valueOf(userId));
                rru.setNetCareName(userName);
                rru.setNetworkType(Integer.valueOf(type));
                RRUList.add(rru);
                dxCodes.add(dxCode);
            } catch (HandlerException e){
                continue;
            }
        }

        if(siteMapper.add3GRRU(RRUList)){
           List<SitManager> sitManager=siteMapper.queryRruInfo(dxCodes,tp);
           siteMapper.updateRruCountAndPower(sitManager);
        }
        return true;
    }
    @Transactional
    public boolean addBBUList(Sheet sheet) {
        List<EquipmentBBU> BBUList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        Integer tp=null;
        EquipmentBBU bbu;
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                break;//整行为空，跳出
            }
            //错误原因
            String reason = "";
            //获取每个单元格
            /**
             * "电信编码,bbu编码,bbu名称,网管员id,网管员,类型编码"
             */
            //电信编码
            String dxCode = getCellVal(row.getCell(0));//第一个单元格
            //bbu编码
            String rruCode = getCellVal(row.getCell(1));
            //bbu名称
            String rruName = getCellVal(row.getCell(2));
            //bbu耗电量
            String power = getCellVal(row.getCell(3));
            //网管员id
            String userId = getCellVal(row.getCell(4));
            if(userId==null || userId.equals("")){
                userId="1";
            }
            //网管员
            String userName = getCellVal(row.getCell(5));
            //类型编码
            String type = getCellVal(row.getCell(6));
            if(tp==null){
                try{
                    tp=Integer.valueOf(type);
                }catch (Exception e){
                }
            }
            if(dxCode==null ||rruCode==null){
                continue;
            }
            bbu=new EquipmentBBU();
            if(power==null ||power.isEmpty()){
                Double bbu1 = getPower(type, "bbu");
                bbu.setPower(bbu1);
            }else {
                bbu.setPower(Double.valueOf(power));
            }
            bbu.setDxCode(dxCode);
            bbu.setBbuCode(rruCode);
            bbu.setBbuName(rruName);
            bbu.setNetCareId(Integer.valueOf(userId));
            bbu.setNetCareName(userName);
            bbu.setNetworkType(Integer.valueOf(type));
            BBUList.add(bbu);
            dxCodes.add(dxCode);
        }
        System.out.println("================================================="+JSON.toJSONString(BBUList));
        if(siteMapper.add3GBBU(BBUList)){
            List<SitManager> sitManager=siteMapper.queryBBUInfo(dxCodes,tp);
            System.out.println(JSON.toJSONString(sitManager));
            siteMapper.updateRruCountAndPower(sitManager);
        }
        return true;
    }

    /**
     *默认耗电量
     */
    private Double getPower(String type,String bbu) {
        Integer ty=Integer.valueOf(type);
        if("bbu".equals(bbu)){
            if(ty.equals(SiteEnum.T_BBU.getKey())){
                return PowerEnum.T_BBU_POWER.getKey();
            }else if (ty.equals(SiteEnum.F_BBU.getKey())){
                return PowerEnum.T_BBU_POWER.getKey();
            }else if(ty.equals(SiteEnum.FIVE_BBU.getKey())){
                return PowerEnum.FIVE_BBU_POWER.getKey();
            }
        }else if("rru".equals(bbu)){
            if(ty.equals(SiteEnum.T_RRU.getKey())){
                return PowerEnum.T_RRU_POWER.getKey();
            }else if (ty.equals(SiteEnum.F_RRU.getKey())){
                return PowerEnum.T_RRU_POWER.getKey();
            }else if(ty.equals(SiteEnum.FIVE_AAU.getKey())){
                return PowerEnum.FIVE_AAU_POWER.getKey();
            }
        }
        return null;
    }

    /**
     * 获取单元格内容
     * @param cell 指定单元格
     * @return
     */
    private String getCellVal(Cell cell){
        if(cell != null){
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue().trim();
        }else {  return "";}
    }

    public List<EquipmentOLT> queryOLTByIds(String ids) {
      return  siteMapper.queryOLTByIds(ids);
    }
    public List<EquipmentIPRAN> queryIPRANByIds(String ids) {
      return  siteMapper.queryIPRANByIds(ids);
    }

    public boolean addOLTList(Sheet sheet) {
        List<EquipmentOLT> OLTList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        EquipmentOLT olt;
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                continue;//整行为空，跳出
            }
            //错误原因
            String reason = "";
            //获取每个单元格
            /**
             * "电信编码,bbu编码,bbu名称,网管员id,网管员,类型编码"
             */
            //电信编码
            String dxCode = getCellVal(row.getCell(0));//第一个单元格
            //bbu编码
            String oltCode = getCellVal(row.getCell(1));
            //bbu名称
            String oltName = getCellVal(row.getCell(2));
            //bbu耗电量
            String power = getCellVal(row.getCell(3));
            //网管员id
            String userId = getCellVal(row.getCell(4));
            if(userId==null || userId.equals("")){
                userId="1";
            }
            //网管员
            String userName = getCellVal(row.getCell(5));
            if(dxCode==null ||oltCode==null){
                continue;
            }
            olt=new EquipmentOLT();
            if(power==null ||power.isEmpty()){
                Double olt1 = getPower("0", "olt");
                olt.setPower(olt1);
            }else {
                olt.setPower(Double.valueOf(power));
            }
            olt.setDxCode(dxCode);
            olt.setOltCode(oltCode);
            olt.setOltName(oltName);
            olt.setNetCareId(Integer.valueOf(userId));
            olt.setNetCareName(userName);
            OLTList.add(olt);
            dxCodes.add(dxCode);
        }
        if(siteMapper.addOLT(OLTList)){
            List<SitManager> sitManager=siteMapper.queryOLTInfo(dxCodes);
            siteMapper.updateRruCountAndPower(sitManager);
        }
        return true;
    }
}
