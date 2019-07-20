package com.dx.service.site;

import com.dx.mapper.site.SiteMapper;
import com.dx.model.contract.Contract;
import com.dx.model.site.EquipmentBBU;
import com.dx.model.site.EquipmentRRUAAU;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int add3GBBU(EquipmentBBU equipmentBBU) {
        return siteMapper.add3GBBU(equipmentBBU);
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
    public int add3GRRU(EquipmentRRUAAU equipmentRRUAAU) {
        return siteMapper.add3GRRU(equipmentRRUAAU);
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
}
