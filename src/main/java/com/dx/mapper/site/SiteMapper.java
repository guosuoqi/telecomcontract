package com.dx.mapper.site;

import com.dx.model.site.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface SiteMapper {

    int queryBBUCount(HashMap<String, Object> params);

    List<EquipmentBBU> queryBBU(HashMap<String, Object> params);

    boolean add3GBBU(List<EquipmentBBU> bbuList);

    int delAll(@Param("ids") String ids);

    EquipmentBBU query3GBBUById(@Param("id")Integer id);

    void update3GBBU(@Param("equipmentBBU") EquipmentBBU equipmentBBU);

    int queryRRUCount(HashMap<String, Object> params);

    List<EquipmentRRUAAU> queryRRU(HashMap<String, Object> params);

    boolean add3GRRU(List<EquipmentRRUAAU> rruList);

    int delAllRRU(@Param("ids")String ids);

    void updateRRU(@Param("equipmentRRUAAU")EquipmentRRUAAU equipmentRRUAAU);

    EquipmentRRUAAU query3GRRUById(@Param("id")Integer id);

    List<EquipmentBBU> queryBBUByIdsAndType(@Param("ids")String ids);

    List<EquipmentRRUAAU> queryRRByIdsAndType(@Param("ids")String ids);

    int queryStieManagerCount(HashMap<String, Object> params);

    List<SitManager> queryStieManager(HashMap<String, Object> params);

    void updateSite(SitManager sit);

    void updateRruCountAndPower(@Param("list") List<SitManager> sitManager);

    List<SitManager> queryBBUInfo(@Param("list") List<String> dxCodes, @Param("type")Integer type);
    List<SitManager> queryRruInfo(@Param("list") List<String> dxCodes, @Param("type")Integer type);

    List<EquipmentOLT> queryOLTByIds(String ids);

    List<EquipmentIPRAN> queryIPRANByIds(String ids);

    boolean addOLT(List<EquipmentOLT> oltList);

    List<SitManager> queryOLTInfo(@Param("list")List<String> dxCodes);
}
