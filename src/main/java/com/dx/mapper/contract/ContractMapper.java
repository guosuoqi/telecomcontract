package com.dx.mapper.contract;

import com.dx.model.contract.Contract;
import com.dx.model.contract.ContractExtension;
import com.dx.model.contract.SysCode;
import com.dx.model.user.UserMain;
import org.apache.ibatis.annotations.Param;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.List;

public interface ContractMapper {

    int queryContractCount(HashMap<String, Object> params);

    boolean addContractList(@Param("list")List<Contract> list);

    List<Contract> queryContract(HashMap<String, Object> params);

    int addContract(@Param("contract") Contract contract);

    int delAll(@Param("ids") String ids);

    List<SysCode> queryType();

    Contract queryCcontractById(@Param("contractId") Integer contractId);

    List<Contract>queryCcontractByIds(@Param("ids")String ids);

    int updateContract(@Param("contract")Contract contract);

    int queryContractExtensionCount(HashMap<String, Object> params);

    List<ContractExtension> queryContractExtension(HashMap<String, Object> params);

    int addContractExtension(@Param("contractExtension") ContractExtension contractExtension);

    void updateContractExtension(@Param("contractExtension")ContractExtension contractExtension);

    void updateContractStatus(@Param("contract")Contract contract);

    List<Contract> queryContractByStatus(@Param("contract")Contract contract);

    int queryContractCountSum();

    int querySiteCountSum();

    int queryContractExtensionCount();

    int queryContractRenewCount();

    int querySheBeiSum();
}
