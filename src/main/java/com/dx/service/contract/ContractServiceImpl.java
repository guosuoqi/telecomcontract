package com.dx.service.contract;

import com.alibaba.fastjson.JSON;
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
import com.dx.service.task.TaskServeceImpl;
import com.dx.util.DateUtils;
import com.dx.util.PageResult;
import com.dx.util.PageUtil;
import com.alibaba.fastjson.JSONObject;
import com.dx.util.StringUtil;
import org.apache.catalina.mbeans.UserMBean;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TaskServeceImpl taskServeceImpl;

    private String XU_YUE_Content="NAME-机房 合同待续费 ！！ 现缴费截止日期为-YYYYMMDD,请尽快完成续费";
    private String XU_FEI_Content="NAME-机房 合同待续签 ！！ 现合同截止日期为-YYYYMMDD,请尽快完成续签";

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
        if(contract.getContractId()!=null){
            return contractMapper.updateContract(contract);
        }
        return contractMapper.addContract(contract);
    }
    public boolean addContractList(Sheet sheet, String UserName) {
        List<SysCode> typeList=contractMapper.queryType();
        List<Contract> contractList = new ArrayList<>();
        Contract contract;
        for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) { // 获取每行
            Row row = (Row) sheet.getRow(i);
            if(row ==null){
                continue;//整行为空，跳出
            }
            //错误原因
            String reason = "";
            //获取每个单元格
            /**
             * 0 机房名字 1区县 2具体地址  3 年租金 4 总租金 5合同编号
             * 6合同甲方 7 收款人 8 拟租开始年份  9 拟租结束事件
             * 10付费截至时间 11机房类型 12是否有基站
             */
            String jifangName = getCellVal(row.getCell(0));
            String county = getCellVal(row.getCell(1));
            String address = getCellVal(row.getCell(2));
            String yearRental = getCellVal(row.getCell(3));
            String sunRental = getCellVal(row.getCell(4));
            String department = getCellVal(row.getCell(5));
            String contractFirst = getCellVal(row.getCell(6));
            String payee = getCellVal(row.getCell(7));
            //开始时间  getStartTime
            String startTime = getCellVal(row.getCell(8));
            //结束时间  getEndTime
            String endTime = getCellVal(row.getCell(9));
            // 付费截止日期   getPayEndTime
            String payEndTim = getCellVal(row.getCell(10));
            // 机房类型  getRoomTypeName
            String roomTypeName = getCellVal(row.getCell(11));
            Integer roomType=getTypeByName(typeList,roomTypeName);
            // 是否有基站
            String towerTypeName = getCellVal(row.getCell(12));


            contract = new Contract();
            contract.setCounty(county);
            contract.setJifangName(jifangName);
            contract.setAddress(address);
            contract.setYearRental(yearRental);
            contract.setSunRental(sunRental);
            contract.setContractNum(department);
            contract.setContractFirst(contractFirst);
            contract.setPayee(payee);
            contract.setStartTime(startTime);
            contract.setEndTime(endTime);
            contract.setPayEndTime(payEndTim);
            contract.setRoomType(roomType);
            contract.setRoomTypeName(roomTypeName);
            contract.setTowerTypeName(towerTypeName);
            contractList.add(contract);
        }
        return contractMapper.addContractList(contractList);
    }

    private Integer getTypeByName(List<SysCode> typeList, String poomTypeName) {
        if(StringUtils.isBlank(poomTypeName)){
            return null;
        }
        for (SysCode sys:typeList) {
            if (sys.getCodeName().equals(poomTypeName)){
                return Integer.valueOf(sys.getCodeId());
            }
        }
        return null;
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
//查询合同总个数
    @Override
    public int queryContractCountSum() {

        return contractMapper.queryContractCountSum();
    }
    //查询站点总个数
    @Override
    public int querySiteCountSum() {
        return contractMapper.querySiteCountSum();
    }

    @Override
    public int queryContractExtensionCount() {
        return contractMapper.queryContractExtensionCount();
    }
    @Override
    public int queryContractRenewCount() {
        return contractMapper.queryContractRenewCount();
    }
    @Override
    public int querySheBeiSum() {
        return contractMapper.querySheBeiSum();
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
        //设置单元格类型
        cell.setCellType(CellType.STRING);
        String stringCellValue = cell.getStringCellValue();
        if(StringUtils.isBlank(stringCellValue)){
            return null;
        }
        return stringCellValue.trim();
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
        contract.setRenewStatus(1);
        contract.setExtenxionStatus(1);
        List<Contract>list=contractMapper.queryContractByStatus(contract);
        if(list!=null && !list.isEmpty()){
            sendTaskToEmail(list);
        }
    }
    public void sendTaskToEmail(List<Contract>list){
        List<UserMain> users=null;
        List<TaskModel> taskModels=new ArrayList<>();
        TaskModel taskModel=null;
        JSONObject body=null;
        for (Contract con:list) {
            if(con.getExtenxionStatus()==1){
                System.out.println("合同地区："+con.getCounty());
                users=userMapper.queryUserByCounty(con.getCounty());
                for (UserMain user:users) {
                    body= getCommJson(user,1,con);
                    SendTask(taskModel,user,body,taskModels);
                }
            }else if(con.getRenewStatus()==1){
                users=userMapper.queryUserByCounty(con.getCounty());
                for (UserMain user:users) {
                    body= getCommJson(user,1,con);
                    SendTask(taskModel,user,body,taskModels);
                }
            }
        }
        if(taskModels.isEmpty()){
            return;
        }
        taskServeceImpl.addTask(taskModels);
    }
    private void SendTask(TaskModel taskModel, UserMain user, JSONObject body,List<TaskModel>taskModels) {
        if(user.getEmail()==null){
            return;
        }
        taskModel=new TaskModel();
        taskModel.setStatus(0);
        taskModel.setType(TaskEnum.SEND_EMAIL.getKey());
        taskModel.setUserEmail(user.getEmail());
        taskModel.setContent(body.toJSONString());
        taskModels.add(taskModel);
    }
    private JSONObject getCommJson(UserMain user, int type,Contract con) {
        JSONObject body =new JSONObject();
        body.put("userEmail",user.getEmail());
        String roomName=getRoomName(user,con);
        if(type==1){
            body.put("subject","续费待处理通知");
            body.put("content",XU_YUE_Content.replace("NAME",roomName).replace("YYYYMMDD",con.getPayEndTime()));

        }else {
            body.put("subject","续约待处理通知");
            body.put("content",XU_FEI_Content.replace("NAME",roomName).replace("YYYYMMDD",con.getEndTime()));
        }
        return body;
    }

    private String getRoomName(UserMain user, Contract con) {
       if(con.getRoomName()!=null){
            return con.getRoomName();
        }else {
            return "未知机房，合同编码为："+con.getContractNum();
        }
    }


    //Mysql支持的时间戳限制
    static long minTime = Timestamp.valueOf("1970-01-01 09:00:00").getTime();
    static long maxTime = Timestamp.valueOf("2038-01-19 11:00:00").getTime();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    //判断 并转换时间格式 ditNumber = 43607.4166666667
    public static String getTime(String ditNumber) {
        if(StringUtils.isBlank(ditNumber)){
            return null ;
        }
        //如果不是数字
        if(!isNumeric(ditNumber)){
            return null;
        }
        //如果是数字 小于0则 返回
        BigDecimal bd = new BigDecimal(ditNumber);
        int days = bd.intValue();//天数
        int mills = (int) Math.round(bd.subtract(new BigDecimal(days)).doubleValue() * 24 * 3600);

        //获取时间
        Calendar c = Calendar.getInstance();
        c.set(1900, 0, 1);
        c.add(Calendar.DATE, days - 2);
        int hour = mills / 3600;
        int minute = (mills - hour * 3600) / 60;
        int second = mills - hour * 3600 - minute * 60;
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);

        Date d = c.getTime();//Date
        try {
                return dateFormat.format(c.getTime());
        } catch (Exception e) {
            System.out.println("传入日期错误" + c.getTime());
        }
        return "Error";
    }

    //校验是否数据含小数点
    private static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+\\.*[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches()){
            return false;
        }
        return true;
    }
}