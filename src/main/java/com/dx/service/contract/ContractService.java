package com.dx.service.contract;

import com.dx.model.contract.Contract;
import com.dx.model.contract.ContractExtension;
import com.dx.model.contract.SysCode;
import com.dx.model.site.EquipmentBBU;
import com.dx.util.PageResult;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.List;

public interface ContractService {


    PageResult queryContract(Integer page, Integer rows, Contract contract);

    void updContract(Contract contract);

    boolean addContractList(Sheet sheet,String UserName);

    int addContract(Contract contract);

    int delAll(String ids);

    List<SysCode> queryType();

    Contract queryContractById(Integer contractId);

    void updateContract(Contract contract);

    int queryContractByEndTime(Contract contract);

    List<Contract> queryContractByIds(String ids);

    PageResult queryContractExtension(Integer page, Integer rows, ContractExtension contractExtension);

    int addContractExtension(ContractExtension contractExtension);

    void updateContractExtension(ContractExtension contractExtension);

    int queryContractCountSum();

    int querySiteCountSum();

    int queryContractExtensionCount();

    int queryContractRenewCount();

    int querySheBeiSum();
}
