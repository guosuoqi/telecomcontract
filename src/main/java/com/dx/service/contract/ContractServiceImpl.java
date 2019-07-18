package com.dx.service.contract;

import com.alibaba.fastjson.JSONObject;
import com.dx.mapper.contract.ContractMapper;
import com.dx.mapper.task.TaskMapper;
import com.dx.mapper.user.UserMapper;
import com.dx.model.Task.TaskModel;
import com.dx.model.common.TaskEnum;
import com.dx.model.contract.Contract;
import com.dx.model.contract.ContractExtension;
import com.dx.model.contract.SysCode;
import com.dx.model.user.UserMain;
import com.dx.util.DateUtils;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import org.apache.catalina.mbeans.UserMBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private UserMapper userMapper;

    //查询合同管理页面
    @Override
    public PageResult queryContract(Integer page, Integer rows, Contract contract) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("contract",contract);
        int count=contractMapper.queryContractCount(params);
        pageResult.setTotal(count);
        PageUtil<Contract> pageUtil = new PageUtil<Contract>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<Contract> list = contractMapper.queryContract(params);
        pageResult.setRows(list);
        return pageResult;
    }

    @Override
    public void updContract(Contract contract) {

    }
    //合同管理页面新增
    @Override
    public int addContract(Contract contract) {

        return contractMapper.addContract(contract);
    }
    public boolean addContractList(Sheet sheet, String UserName) {
        String time= DateUtils.getDate("yyyyMMddHHmmss");
        List<SysCode> typeList=contractMapper.queryType();
        List<Contract> contractList = new ArrayList<>();
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                break;//整行为空，跳出
            }
            //获取每个单元格
            /**
             * 0 id  1合同名字 2省-市 3年租金 4 总租金 5 合同编号
             * 6合同甲方 7 收款人 8 拟租年份 9开始时间 10 结束事件
             * 11付费截至时间 12机房类型 13塔栀类型 14合同类型
             */
            Cell cell = row.getCell(0);//第一个单元格
            if(cell == null) break;//第1个单元格为空，跳出
            //错误原因
            String reason = "";
            Contract contract = new Contract();
            //账号
            String userid = cell.getStringCellValue().trim();
            if("".equals(userid)){
                break;
            }
           // userInfoTemp.setUserid(userid);
            //合同名
            String name = getCellVal(row.getCell(1));
            if("".equals(name)){
                reason += "合同名字,";
            }
            //地址
            String cityAndCounty = getCellVal(row.getCell(2));
            if("".equals(cityAndCounty)){
                reason += "地址不可为空,";
            }
            String[] split = cityAndCounty.split("-");
            String city="";
            String county="";
            if(split.length>=2){
                city=getCityOrCounty(split,0);
                county=getCityOrCounty(split,1);
            }else{
                reason += "地址格式不正确,";
            }
            //年租金
            String yearRental = getCellVal(row.getCell(3));
            if(!"".equals(yearRental)){
                reason += "年租金不允许为空,";
            }
            //总租金
            String sunRental = getCellVal(row.getCell(4));
            if(!"".equals(sunRental)){
                reason += "总租金不允许为空,";
            }
            //合同编号
            String department = getCellVal(row.getCell(5));
            if(!"".equals(department)){
                reason += "合同编号不可为空,";
            }
            //合同甲方    getContractFirst
            String contractFirst = getCellVal(row.getCell(6));
            if(!"".equals(contractFirst)){
                reason += "合同甲方不可为空,";
            }
            //收款人   getPayee
            String payee = getCellVal(row.getCell(7));
            if(!"".equals(contractFirst)){
                reason += "合同甲方不可为空,";
            }
            //拟租年份  getPlanYear
            String planYear = getCellVal(row.getCell(8));
            if(!"".equals(planYear)){
                reason += "拟租年份不可为空,";
            }
            //开始时间  getStartTime
            String startTime = getCellVal(row.getCell(8));
            if(!"".equals(planYear)){
                reason += "开始时间不可为空,";
            }
            //结束时间  getEndTime
            String endTime = getCellVal(row.getCell(8));
            if(!"".equals(endTime)){
                reason += "结束时间不可为空,";
            }
            // 付费截止日期   getPayEndTime
            String payEndTim = getCellVal(row.getCell(8));
            if(!"".equals(payEndTim)){
                reason += "付费截止日期不可为空,";
            }
            // 机房类型  getRoomTypeName
            String roomTypeName = getCellVal(row.getCell(8));
            String roomType=getTypeByName(typeList,roomTypeName);
            if(!"".equals(roomType)){
                reason += "机房类型不可为空,";
            }
            // 塔栀类型 getTowerTypeName
            String towerTypeName = getCellVal(row.getCell(8));
            String towerType=getTypeByName(typeList,towerTypeName);
            if(!"".equals(towerType)){
                reason += "塔栀类型不可为空,";
            }
            //合同类型 getContractTypeName
            String contractTypeName = getCellVal(row.getCell(8));
            String contractType=getTypeByName(typeList,contractTypeName);
            if(!"".equals(towerType)){
                reason += "合同类型不可为空,";
            }
            contract = new Contract();
            contract.setContractName(name);
            contract.setCity(city);
            contract.setCounty(county);
            contract.setYearRental(yearRental);
            contract.setSunRental(sunRental);
            contract.setContractNum(contractTypeName);
            contract.setContractFirst(contractFirst);
            contract.setPayee(payee);
            contract.setStartTime(startTime);
            contract.setEndTime(endTime);
            contract.setPayEndTime(payEndTim);
            contract.setRoomType(Integer.valueOf(roomType));
            contract.setRoomTypeName(roomTypeName);
            contract.setTowerType(Integer.valueOf(towerType));
            contract.setTowerTypeName(towerTypeName);
            contract.setContractType(Integer.valueOf(contractType));
            contract.setContractTypeName(contractTypeName);
            contractList.add(contract);
        }
        return contractMapper.addContractList(contractList);
    }

    private String getTypeByName(List<SysCode> typeList, String poomTypeName) {
        for (SysCode sys:typeList) {
            if (sys.getCodeName().equals(poomTypeName)){
                return sys.getCodeId();
            }
        }
        return "";
    }

    private String getCityOrCounty(String[] split, int i) {
        return split[i];
    }
    //合同管理页面批量删除
    @Override
    public int delAll(String ids) {
        return contractMapper.delAll(ids);
    }
    //合同类型查询
    @Override
    public List<SysCode> queryType() {
        return contractMapper.queryType();
    }
    //去修改合同管理页面
    @Override
    public Contract queryContractById(Integer contractId) {
        return contractMapper.queryCcontractById(contractId);
    }

    @Override
    public List<Contract> queryContractByIds(String ids) {
        return contractMapper.queryCcontractByIds(ids);
    }


    //合同续约页面查询
    @Override
    public PageResult queryContractExtension(Integer page, Integer rows, ContractExtension contractExtension) {
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("contractExtension",contractExtension);
        int count=contractMapper.queryContractExtensionCount(params);
        pageResult.setTotal(count);
        PageUtil<ContractExtension> pageUtil = new PageUtil<ContractExtension>(count,page,rows);
        params.put("startIndex", pageUtil.getStartIndex());
        params.put("endIndex",rows);
        List<ContractExtension> list = contractMapper.queryContractExtension(params);
        pageResult.setRows(list);
        return pageResult;
    }
    //合同续约页面新增
    @Override
    public int addContractExtension(ContractExtension contractExtension) {
        return contractMapper.addContractExtension(contractExtension);
    }

    //修改合同管理页面
    @Override
    public void updateContract(Contract contract) {
         contractMapper.updateContract(contract);
    }

    //修改合同续约页面
    @Override
    public void updateContractExtension(ContractExtension contractExtension) {
        contractMapper.updateContractExtension(contractExtension);
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
    /**
     * 搜索合同续费/续费数
     */
    public int queryContractByEndTime(Contract contract){
        PageResult pageResult = new PageResult();
        HashMap<String, Object> params = new HashMap<>();
        params.put("contract",contract);
        return contractMapper.queryContractCount(params);
    }

    /**
     * 修正合同续费 续约状态
     */
    public void updateContractStatus(){
        Contract contract = new Contract();
        String pastDate = DateUtils.getPastDate(3);
        contract.setEndTime(pastDate);
        contractMapper.updateContractStatus(contract);
        contract = new Contract();
        contract.setPayEndTime(pastDate);
        contractMapper.updateContractStatus(contract);
    }
    public void sendContractTaskToEmail() {
        TaskModel model;
        Contract contract = new Contract();
        contract.setExtenxionStatus(1);
        List<Contract>list=contractMapper.queryContractByStatus(contract);
        if(list!=null && !list.isEmpty()){

        }
    }
    public void sendTaskToEmail(List<Contract>list){
        UserMain user=null;
        TaskModel taskModel=null;
        JSONObject body=null;
        for (Contract con:list) {
            if(con.getNumber()==0){
                continue;
            }
            if(!con.getRenewOperator().isEmpty()){
                user=userMapper.queryUserByName(con.getRenewOperator());
                body= getCommJson(user,1,con.getNumber());
            }else if(!con.getExtenxionOperator().isEmpty()){
                user=userMapper.queryUserByName(con.getExtenxionOperator());
                body= getCommJson(user,2,con.getNumber());
            }
            if(user==null){
                continue;
            }
            taskModel.setStatus(0);
            taskModel.setType(TaskEnum.SEND_EMAIL.getKey());
            taskModel.setUser(user.getUserName());
            taskModel.setContent(body.toJSONString());

        }
    }

    private JSONObject getCommJson(UserMain user, int type,Integer number) {
        JSONObject body =new JSONObject();
        body.put("userEmail",user.getEmail());
        if(type==1){
            body.put("subject","续费待处理通知");
            body.put("content",user.getUserName()+"您好：有"+number+"笔合同已进入续费阶段，请尽快进行处理！！！\n \n \n  本条信息为系统信息，请勿回复！");

        }else {
            body.put("subject","续约待处理通知");
            body.put("subject",user.getUserName()+"您好：有"+number+"笔合同已进入续费阶段，请尽快进行处理！！！\n \n \n  本条信息为系统信息，请勿回复！");
        }

        return body;
    }

}