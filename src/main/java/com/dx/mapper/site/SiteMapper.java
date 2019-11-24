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

    List<SitManager> queryOLTInfo(@Param("list")List<String> dxCodes);

    List<EquipmentOLT> queryOlt(HashMap<String, Object> params);

    boolean addOlt(List<EquipmentOLT> oltList);

    EquipmentOLT queryOltById(Integer id);

    void updateOlt(@Param("equipmentOLT") EquipmentOLT equipmentOLT);

    int delAllOlt(@Param("ids") String ids);

    List<EquipmentIPRAN> queryIPRAN(HashMap<String, Object> params);

    int queryIPRANCount(HashMap<String, Object> params);

    boolean addIPRAN(List<EquipmentIPRAN> ipranList);

    void updateIPRAN(@Param("equipmentIPRAN")EquipmentIPRAN equipmentIPRAN);

    int delAllIPRAN(@Param("ids")String ids);

    EquipmentIPRAN queryIPRANById(Integer id);

    boolean insertStation(@Param("list")List<SitManager> sitManagerList);

    int delAllSit(@Param("ids")String ids);

    List<SitManager> querySiteByIds(@Param("ids")String ids);
}
