package com.dx.service.site;

import com.dx.mapper.site.SiteMapper;
import com.dx.model.common.SiteEnum;
import com.dx.model.contract.Contract;
import com.dx.model.site.EquipmentBBU;
import com.dx.model.site.EquipmentRRUAAU;
import com.dx.model.site.SitManager;
import com.dx.util.DateUtils;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        EquipmentRRUAAU rru;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                break;//整行为空，跳出
            }
            //错误原因
            String reason = "";
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
            //网管员id
            String userId = getCellVal(row.getCell(3));
            //网管员
            String userName = getCellVal(row.getCell(4));
            //类型编码
            String type = getCellVal(row.getCell(5));
            if(dxCode==null ||rruCode==null){
                continue;
            }
            rru=new EquipmentRRUAAU();
            rru.setDxCode(dxCode);
            rru.setRruCode(rruCode);
            rru.setRruName(rruName);
            rru.setNetCareId(Integer.valueOf(userId));
            rru.setNetCareName(userName);
            rru.setNetworkType(Integer.valueOf(type));
            RRUList.add(rru);
        }
        return siteMapper.add3GRRU(RRUList);
    }

    public boolean addBBUList(Sheet sheet) {
        List<EquipmentBBU> BBUList = new ArrayList<>();
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
             * "电信编码,bbu编码,bbu名称,网管员id,网管员,类型编码"
             */
            //电信编码
            String dxCode = getCellVal(row.getCell(0));//第一个单元格
            //bbu编码
            String rruCode = getCellVal(row.getCell(1));
            //bbu名称
            String rruName = getCellVal(row.getCell(2));
            //网管员id
            String userId = getCellVal(row.getCell(3));
            //网管员
            String userName = getCellVal(row.getCell(4));
            //类型编码
            String type = getCellVal(row.getCell(5));
            if(dxCode==null ||rruCode==null){
                continue;
            }
            bbu=new EquipmentBBU();
            bbu.setDxCode(dxCode);
            bbu.setBbuCode(rruCode);
            bbu.setBbuName(rruName);
            bbu.setNetCareId(Integer.valueOf(userId));
            bbu.setNetCareName(userName);
            bbu.setNetworkType(Integer.valueOf(type));
            BBUList.add(bbu);
        }
        return siteMapper.add3GBBU(BBUList);
    }
    /**
     * 获取单元格内容
     * @param cell 指定单元格
     * @return
     */
    private String getCellVal(Cell cell){
        if(cell == null){
            return "";
        }
        return cell.getStringCellValue().trim();
    }
}
