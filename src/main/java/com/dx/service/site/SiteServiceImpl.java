package com.dx.service.site;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dx.mapper.site.SiteMapper;
import com.dx.model.common.PowerEnum;
import com.dx.model.common.SiteEnum;
import com.dx.model.contract.Contract;
import com.dx.model.site.*;
import com.dx.util.DateUtils;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import com.dx.util.StringUtil;
import org.apache.commons.lang.StringUtils;
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
    @Transactional
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
        sit.setPower(equipmentBBU.getPower());
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
        SitManager sit = new SitManager();
        sit.setDxCode(equipmentRRUAAU.getDxCode());
        if(SiteEnum.T_RRU.getKey()==equipmentRRUAAU.getNetworkType()){
            sit.setThreeRruCount(1);
        }else if(SiteEnum.F_RRU.getKey()==equipmentRRUAAU.getNetworkType()){
            sit.setFourRruCount(1);
        }else if(SiteEnum.FIVE_AAU.getKey()==equipmentRRUAAU.getNetworkType()){
            sit.setFiveAauCount(1);
        }else{
            return false;
        }
        sit.setPower(equipmentRRUAAU.getPower());
        siteMapper.updateSite(sit);
        return siteMapper.add3GRRU(rruList);
    }

    @Override
    public boolean addOlt(EquipmentOLT equipmentOLT) {
        List<EquipmentOLT> oltList=new ArrayList<>();
        oltList.add(equipmentOLT);
        SitManager sit = new SitManager();
        sit.setDxCode(equipmentOLT.getDxCode());
        sit.setPower(equipmentOLT.getPower());
        sit.setOltCount(1);
        siteMapper.updateSite(sit);
        return siteMapper.addOlt(oltList);
    }

    @Override
    public EquipmentOLT queryOltById(Integer id) {
        return siteMapper.queryOltById(id);
    }

    @Override
    public void updateOlt(EquipmentOLT equipmentOLT) {
        siteMapper.updateOlt(equipmentOLT);
    }

    @Override
    public int delAllOlt(String ids) {
        return siteMapper.delAllOlt(ids);
    }

    @Override
    public int delAllIPRAN(String ids) {
        return siteMapper.delAllIPRAN(ids);
    }

    @Override
    public void updateIPRAN(EquipmentIPRAN equipmentIPRAN) {
        siteMapper.updateIPRAN(equipmentIPRAN);
    }

    @Override
    public boolean addIPRAN(EquipmentIPRAN equipmentIPRAN) {
        List<EquipmentIPRAN> ipranList=new ArrayList<>();
        ipranList.add(equipmentIPRAN);
        SitManager sit = new SitManager();
        sit.setDxCode(equipmentIPRAN.getDxCode());
        sit.setPower(equipmentIPRAN.getPower());
        sit.setIpranCount(1);
        siteMapper.updateSite(sit);
        return siteMapper.addIPRAN(ipranList);
    }

    @Override
    public PageResult queryIPRAN(Integer page, Integer rows, EquipmentIPRAN equipmentIPRAN) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("equipmentIPRAN",equipmentIPRAN);
        int  count=siteMapper.queryIPRANCount(params);
        pageResult.setTotal(count);
        PageUtil<EquipmentIPRAN> pageUtil = new PageUtil<EquipmentIPRAN>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<EquipmentIPRAN> list = siteMapper.queryIPRAN(params);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public EquipmentIPRAN queryIPRANById(Integer id) {
        return siteMapper.queryIPRANById(id);
    }
    @Override
    public boolean insertStation(List<SitManager> sitManagerList) {
       return siteMapper.insertStation(sitManagerList);
    }


    @Override
    public int delAllRRU(String ids) {
        return siteMapper.delAllRRU(ids);
    }
 @Override
    public int delAllSit(String ids) {
        return siteMapper.delAllSit(ids);
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

    @Override
    public PageResult queryOlt(Integer page, Integer rows, EquipmentOLT equipmentOLT) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("equipmentOLT",equipmentOLT);
        int  count=siteMapper.queryRRUCount(params);
        pageResult.setTotal(count);
        PageUtil<EquipmentOLT> pageUtil = new PageUtil<EquipmentOLT>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<EquipmentOLT> list = siteMapper.queryOlt(params);
        pageResult.setRows(list);
        return pageResult;
    }


    public List<EquipmentBBU> queryBBUByIdsAndType(String ids) {
        return siteMapper.queryBBUByIdsAndType(ids);
    }

    public List<EquipmentRRUAAU> queryRRByIdsAndType(String ids) {
        return siteMapper.queryRRByIdsAndType(ids);
    }
    @Transactional
    public boolean addRRUList(Sheet sheet) {
        List<EquipmentRRUAAU> RRUList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        Integer tp=null;
        EquipmentRRUAAU rru;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            try {
                Row row = (Row) sheet.getRow(i);
                if(row ==null){
                    break;//整行为空，跳出
                }
                //获取每个单元格
                /**
                 * "rru编码,rru名称,电信编码,耗电量,类型编码"
                 */
                //bbu编码
                String rruCode = getCellVal(row.getCell(0));
                //bbu名称
                String rruName = getCellVal(row.getCell(1));
                //电信编码
                String dxCode = getCellVal(row.getCell(2));
                //耗电量
                String power = getCellVal(row.getCell(3));
                //类型编码
                String type = getCellVal(row.getCell(4));

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
                if(StringUtils.isBlank(power)){
                    Double bbu1 = getPower(type, "rru");
                    rru.setPower(bbu1);
                }else {
                    rru.setPower(Double.valueOf(power));
                }
                rru.setDxCode(dxCode);
                rru.setRruCode(rruCode);
                rru.setRruName(rruName);
                rru.setNetworkType(Integer.valueOf(type));
                RRUList.add(rru);
                dxCodes.add(dxCode);
            } catch (Exception e){
                continue;
            }
        }
        if(siteMapper.add3GRRU(RRUList)){
            updageRevisedDade();
        }
        return true;
    }
    @Transactional
    public boolean addBBUList(Sheet sheet) {
        List<EquipmentBBU> BBUList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        Integer tp=null;
        EquipmentBBU bbu;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                break;//整行为空，跳出
            }
            //错误原因
            String reason = "";
            //获取每个单元格
            /**
             * "bbu编码,bbu名称,电信编码,电量,类型编码"
             */
            //bbu编码
            String rruCode = getCellVal(row.getCell(0));
            //bbu名称
            String rruName = getCellVal(row.getCell(1));
            //电信编码
            String dxCode = getCellVal(row.getCell(2));
            //耗电量
            String power = getCellVal(row.getCell(3));
            //类型编码
            String type = getCellVal(row.getCell(4));
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
            bbu.setNetworkType(Integer.valueOf(type));
            BBUList.add(bbu);
            dxCodes.add(dxCode);
        }
        if(siteMapper.add3GBBU(BBUList)){
            updageRevisedDade();
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
                return PowerEnum.F_BBU_POWER.getKey();
            }else if(ty.equals(SiteEnum.FIVE_BBU.getKey())){
                return PowerEnum.FIVE_BBU_POWER.getKey();
            }
        }else if("rru".equals(bbu)){
            if(ty.equals(SiteEnum.T_RRU.getKey())){
                return PowerEnum.T_RRU_POWER.getKey();
            }else if (ty.equals(SiteEnum.F_RRU.getKey())){
                return PowerEnum.F_RRU_POWER.getKey();
            }else if(ty.equals(SiteEnum.FIVE_AAU.getKey())){
                return PowerEnum.FIVE_AAU_POWER.getKey();
            }
        }else if("olt".equals(bbu)){
            return PowerEnum.T_OLT_POWER.getKey();
        }else if("ipran".equals(bbu)){
            return PowerEnum.T_IPRAN_POWER.getKey();
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
    @Transactional
    public boolean addOLTList(Sheet sheet) {
        List<EquipmentOLT> OLTList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        EquipmentOLT olt;
        Double olt1 = getPower("0", "olt");
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                continue;//整行为空，跳出
            }
            //错误原因
            String reason = "";
            //获取每个单元格
            /**
             * "bbu编码,bbu名称,电信编码,电量"
             */
            //第一个单元格
            //bbu编码
            String oltCode = getCellVal(row.getCell(0));
            //bbu名称
            String oltName = getCellVal(row.getCell(1));
            //电信编码
            String dxCode = getCellVal(row.getCell(2));
            //理论耗电量
            String power = getCellVal(row.getCell(3));

            olt=new EquipmentOLT();
            if(StringUtils.isNotBlank(power)){
                olt.setPower(Double.valueOf(power));
            }else {
                olt.setPower(olt1);
            }
            olt.setDxCode(dxCode);
            olt.setOltCode(oltCode);
            olt.setOltName(oltName);
            OLTList.add(olt);
            dxCodes.add(dxCode);
        }
        if(siteMapper.addOlt(OLTList)){
            updageRevisedDade();
        }
        return true;
    }
    public boolean addSitManager(Sheet sheet) {
        List<SitManager> sitList = new ArrayList<>();
        SitManager sit;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                continue;//整行为空，跳出
            }
            //获取每个单元格
            /**
             * 站址名称,所属站址编码,铁塔站址编码,机房产权,电费缴纳发,租赁费缴纳发,站址经度,站址纬度,备注
             */
            //站址名称
            String baseName = getCellVal(row.getCell(0));//第一个单元格
            //电信编码
            String dxCode = getCellVal(row.getCell(1));
            //铁塔站址编码
            String ttCode = getCellVal(row.getCell(2));
            //机房产权
            String baseProperty = getCellVal(row.getCell(3));
            //电费缴纳发
            String powerMan = getCellVal(row.getCell(4));
            //租赁费缴纳发
            String rentPayer = getCellVal(row.getCell(5));
            //经度
            String longitude = getCellVal(row.getCell(6));
            //纬度
            String latitude = getCellVal(row.getCell(7));
            //备注
            String remark = getCellVal(row.getCell(8));
            sit=new SitManager();
            sit.setDxCode(dxCode);
            sit.setTtCode(ttCode);
            sit.setBaseProperty(baseProperty);
            sit.setBaseName(baseName);
            sit.setPowerMan(powerMan);
            sit.setRentPayer(rentPayer);
            sit.setLongitude(longitude);
            sit.setLatitude(latitude);
            sit.setRemark(remark);
            sitList.add(sit);
        }
        if(siteMapper.insertStation(sitList)){
            siteMapper.updateRruCountAndPower(sitList);
        }
        return true;
    }
    @Transactional
    public boolean addIpranList(Sheet sheet) {
        List<EquipmentIPRAN> ipranList = new ArrayList<>();
        List<String> dxCodes = new ArrayList<>();
        EquipmentIPRAN ipr;
        Double ipran = getPower("0", "ipran");
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                continue;//整行为空，跳出
            }
            //错误原因
            String reason = "";
            //获取每个单元格
            /**
             * "ipran编码,ipran名称,电信编码,理论耗电量"
             */
            //bbu编码
            String oltCode = getCellVal(row.getCell(0));
            //bbu名称
            String oltName = getCellVal(row.getCell(1));
            //电信编码
            String dxCode = getCellVal(row.getCell(2));
            //理论耗电量
            String power = getCellVal(row.getCell(3));
            ipr=new EquipmentIPRAN();
            if(StringUtils.isNotBlank(power)){
                ipr.setPower(Double.valueOf(power));
            }else {
                ipr.setPower(ipran);
            }
            ipr.setPower(ipran);
            ipr.setDxCode(dxCode);
            ipr.setIpranCode(oltCode);
            ipr.setIpranName(oltName);
            ipranList.add(ipr);
        }
        if(siteMapper.addIPRAN(ipranList)){
            updageRevisedDade();
        }
        return true;
    }

    public List<SitManager> querySiteByIds(String ids) {
        return  siteMapper.querySiteByIds(ids);
    }
    public void updageRevisedDade() {
        Integer i = siteMapper.updageRevisedDade();
        if(i != 0){
            siteMapper.updageRevisedDadeTwo();
        }
    }
    
}
