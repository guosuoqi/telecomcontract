package com.dx.service.site;

import com.dx.model.site.EquipmentBBU;
import com.dx.model.site.EquipmentRRUAAU;
import com.dx.model.site.SitManager;
import com.dx.util.PageResult;

public interface SiteService {
    PageResult queryBBU(Integer page, Integer rows, EquipmentBBU equipmentBBU);

    boolean add3GBBU(EquipmentBBU equipmentBBU);

    int delAll(String ids);

    EquipmentBBU query3GBBUById(Integer id);

    void update3GBBU(EquipmentBBU equipmentBBU);

    PageResult queryRRU(Integer page, Integer rows, EquipmentRRUAAU equipmentRRUAAU);

    int delAllRRU(String ids);

    boolean add3GRRU(EquipmentRRUAAU equipmentRRUAAU);

    void updateRRU(EquipmentRRUAAU equipmentRRUAAU);

    EquipmentRRUAAU query3GRRUById(Integer id);

    PageResult queryStieManager(Integer page, Integer rows, SitManager sitManager);
}
