package com.dx.mapper.contract;

import com.dx.model.contract.Contract;
import com.dx.model.nav.NavTree;

import java.util.HashMap;
import java.util.List;

public interface ContractMapper {

    int queryContractCount(HashMap<String, Object> params);

    List<Contract> queryContract(HashMap<String, Object> params);
}
