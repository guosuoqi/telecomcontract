package com.dx.mapper.site;

import com.dx.model.site.EquipmentBBU;
import com.dx.model.site.EquipmentRRUAAU;
import com.dx.model.site.SitManager;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SiteMapper {

    int queryBBUCount(HashMap<String, Object> params);

    List<EquipmentBBU> queryBBU(HashMap<String, Object> params);

    int add3GBBU(@Param("equipmentBBU") EquipmentBBU equipmentBBU);

    int delAll(@Param("ids") String ids);

    EquipmentBBU query3GBBUById(@Param("id")Integer id);

    void update3GBBU(@Param("equipmentBBU") EquipmentBBU equipmentBBU);

    int queryRRUCount(HashMap<String, Object> params);

    List<EquipmentRRUAAU> queryRRU(HashMap<String, Object> params);

    int add3GRRU(@Param("equipmentRRUAAU")EquipmentRRUAAU equipmentRRUAAU);

    int delAllRRU(@Param("ids")String ids);

    void updateRRU(@Param("equipmentRRUAAU")EquipmentRRUAAU equipmentRRUAAU);

    EquipmentRRUAAU query3GRRUById(@Param("id")Integer id);

    List<EquipmentBBU> queryBBUByIdsAndType(@Param("ids")String ids);

    List<EquipmentRRUAAU> queryRRByIdsAndType(@Param("ids")String ids);

    int queryStieManagerCount(HashMap<String, Object> params);

    List<SitManager> queryStieManager(HashMap<String, Object> params);
}
