package com.dx.controller.poi;

import com.dx.model.common.PoiTypeEnum;
import com.dx.model.contract.Contract;
import com.dx.model.site.*;
import com.dx.model.user.UserMain;
import com.dx.service.contract.ContractService;
import com.dx.service.site.SiteServiceImpl;
import com.dx.util.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class PoiController {
    private final String bbuStr="BBU标识,BBU名称,所属站址编码,月理论耗电量,类型编码";
    private final String conStr="机房名称,区县,具体地址,年租金,总租金,合同编号,合同甲方,收款人,拟租开始年份,拟租结束时间,付费截止日期,机房类型,是否有基站";
    private final String rruStr="RRU标识,RRU名称,所属站址编码,月理论耗电量,类型编码";
    private final String TrruStr="CI,小区名称,所属站址编码,月理论耗电量,类型编码";
    private final String oltStr="OLT标识,OLT名称,所属站址编码,月理论耗电量";
    private final String ipranStr="IPRAN标识,IPRAN名称,所属站址编码,月理论耗电量";
   // private final String siteStr="基站编码,基站产权,电信编码,铁塔站址编码";
    private final String siteStr="站点名称,所属站址编码,铁塔站址编码,机房产权,电费缴费方,租赁费缴费方,站址经度,站址纬度,备注";
    @Autowired
    private ContractService contractService;
    @Autowired
    private SiteServiceImpl siteServiceImpl;

    @Autowired
    private SiteServiceImpl siteService;
        @RequestMapping("/createExcel")
        public void createExcel(HttpServletRequest request, HttpServletResponse response, String ids,Integer type) throws IOException {
            HashMap<String, String>result = new HashMap<String, String>();
            OutputStream output=null;
            try {
                List<Contract>list= null;
                List<EquipmentBBU>equipmentBBUlist= null;
                List<EquipmentRRUAAU>equipmentRRUAAUList= null;
                List<EquipmentOLT>equipmentOLTList= null;
                List<EquipmentIPRAN>equipmentIPRANList= null;
                List<SitManager>siteList= null;

                String fileName=null;
                String []sheetMerged=null;
                if(type.equals(PoiTypeEnum.POI_TYPE_CONTRACT.getKey())){
                    sheetMerged= conStr.split(",");
                    fileName="合同信息_";
                    list=contractService.queryContractByIds(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_3G_BBU.getKey())){
                    sheetMerged= bbuStr.split(",");
                    fileName="3G_BBU_";
                    equipmentBBUlist=siteService.queryBBUByIdsAndType(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_4G_BBU.getKey())){
                   sheetMerged= bbuStr.split(",");
                    fileName="4G_BBU_";
                    equipmentBBUlist=siteService.queryBBUByIdsAndType(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_5G_BBU.getKey())){
                    sheetMerged= bbuStr.split(",");
                    fileName="5G_BBU_";
                    equipmentBBUlist=siteService.queryBBUByIdsAndType(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_3G_RRU.getKey())){
                   sheetMerged= TrruStr.split(",");
                    fileName="3G_RRU_";
                    equipmentRRUAAUList=siteService.queryRRByIdsAndType(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_4G_RRU.getKey())){
                    sheetMerged= rruStr.split(",");
                    fileName="4G_RRU_";
                    equipmentRRUAAUList=siteService.queryRRByIdsAndType(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_5G_AAU.getKey())){
                   sheetMerged= rruStr.split(",");
                    fileName="5G_AAU_";
                    equipmentRRUAAUList=siteService.queryRRByIdsAndType(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_OLT.getKey())){
                    sheetMerged= oltStr.split(",");
                    fileName="OLT_";
                    equipmentOLTList=siteService.queryOLTByIds(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_IPRAN.getKey())){
                    sheetMerged= ipranStr.split(",");
                    fileName="IPRAN_";
                    equipmentIPRANList=siteService.queryIPRANByIds(ids);
                }else if(type.equals(PoiTypeEnum.POI_TYPE_SITE.getKey())){
                    sheetMerged= siteStr.split(",");
                    fileName="SITE_";
                    siteList=siteService.querySiteByIds(ids);
                }

                //创建HSSFWorkbook对象(excel的文档对象)
                HSSFWorkbook wb = new HSSFWorkbook();
                //建立新的sheet对象（excel的表单）
                HSSFSheet sheet=wb.createSheet(fileName+ DateUtils.getDate());
                //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
                HSSFRow row1=sheet.createRow(0);
                //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
                HSSFCell cell=row1.createCell(0);
                //设置单元格内容
                cell.setCellValue(fileName+"一览表");
                //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,sheetMerged.length-1));
                HSSFRow row2=sheet.createRow(1);
                for (int i = 0; i < sheetMerged.length; i++) {
                    row2.createCell(i).setCellValue(sheetMerged[i]);
                }
                if(type.equals(PoiTypeEnum.POI_TYPE_CONTRACT.getKey())){
                    for (int i = 0; i <list.size() ; i++) {
                    //在sheet里创建第三行
                    HSSFRow row3=sheet.createRow(2+i);
                    setContractPoi(row3,list.get(i));
                    }
                }else if(isBBU(type)){
                    for (int i = 0; i <equipmentBBUlist.size() ; i++) {
                        //在sheet里创建第三行
                        HSSFRow row3=sheet.createRow(2+i);
                        setBBUPoi(row3,equipmentBBUlist.get(i));
                    }
                }else if(isRRU(type)){
                    for (int i = 0; i <equipmentRRUAAUList.size() ; i++) {
                        //在sheet里创建第三行
                        HSSFRow row3=sheet.createRow(2+i);
                        setRRUPoi(row3,equipmentRRUAAUList.get(i));
                    }
                }else if(type.equals(PoiTypeEnum.POI_TYPE_OLT.getKey())){
                    for (int i = 0; i <equipmentOLTList.size() ; i++) {
                        //在sheet里创建第三行
                        HSSFRow row3=sheet.createRow(2+i);
                        setOLTPoi(row3,equipmentOLTList.get(i));
                    }
                }else if(type.equals(PoiTypeEnum.POI_TYPE_IPRAN.getKey())){
                    for (int i = 0; i <equipmentIPRANList.size() ; i++) {
                        //在sheet里创建第三行
                        HSSFRow row3=sheet.createRow(2+i);
                        setIPRANPoi(row3,equipmentIPRANList.get(i));
                    }
                }else if(type.equals(PoiTypeEnum.POI_TYPE_SITE.getKey())){
                    for (int i = 0; i <siteList.size() ; i++) {
                        //在sheet里创建第三行
                        HSSFRow row3=sheet.createRow(2+i);
                        setSitePoi(row3,siteList.get(i));
                    }
                }
                //输出Excel文件
                output=response.getOutputStream();
                response.reset();
                String agent = request.getHeader("USER-AGENT").toLowerCase();
                response.setContentType("application/vnd.ms-excel");
                String codedFileName = java.net.URLEncoder.encode(fileName +DateUtils.getDate(), "UTF-8");
                if (agent.contains("firefox")) {
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("content-disposition", "attachment;filename=" + new String(codedFileName.getBytes(), "ISO8859-1") + ".xls");
                } else {
                    response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
                }

                wb.write(output);

            }catch (Exception e){
                System.out.println(e);
            }finally {
                output.close();
            }
        }
        @RequestMapping("/createExcelMoban")
        public void createExcelMoban(HttpServletRequest request, HttpServletResponse response, String ids,Integer type) throws IOException {
            HashMap<String, String>result = new HashMap<String, String>();
            OutputStream output=null;
            try {
                String fileName=null;
                String []sheetMerged=null;
                if(type.equals(PoiTypeEnum.POI_TYPE_CONTRACT.getKey())){
                    sheetMerged= conStr.split(",");
                    fileName="合同信息_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_3G_BBU.getKey())){
                    sheetMerged= bbuStr.split(",");
                    fileName="3G_BBU_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_4G_BBU.getKey())){
                   sheetMerged= bbuStr.split(",");
                    fileName="4G_BBU_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_5G_BBU.getKey())){
                    sheetMerged= bbuStr.split(",");
                    fileName="5G_BBU_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_3G_RRU.getKey())){
                   sheetMerged= TrruStr.split(",");
                    fileName="3G_RRU_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_4G_RRU.getKey())){
                    sheetMerged= rruStr.split(",");
                    fileName="4G_RRU_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_5G_AAU.getKey())){
                   sheetMerged= rruStr.split(",");
                    fileName="5G_AAU_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_OLT.getKey())){
                    sheetMerged= oltStr.split(",");
                    fileName="OLT_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_IPRAN.getKey())){
                    sheetMerged= ipranStr.split(",");
                    fileName="IPRAN_";
                }else if(type.equals(PoiTypeEnum.POI_TYPE_SITE.getKey())){
                    sheetMerged= siteStr.split(",");
                    fileName="SITE_";
                }

                //创建HSSFWorkbook对象(excel的文档对象)
                HSSFWorkbook wb = new HSSFWorkbook();
                //建立新的sheet对象（excel的表单）
                HSSFSheet sheet=wb.createSheet(fileName+ DateUtils.getDate());
                //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
                HSSFRow row2=sheet.createRow(0);
                for (int i = 0; i < sheetMerged.length; i++) {
                    row2.createCell(i).setCellValue(sheetMerged[i]);
                }

                //输出Excel文件
                output=response.getOutputStream();
                response.reset();
                String agent = request.getHeader("USER-AGENT").toLowerCase();
                response.setContentType("application/vnd.ms-excel");
                String codedFileName = java.net.URLEncoder.encode(fileName +"template", "UTF-8");
                if (agent.contains("firefox")) {
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("content-disposition", "attachment;filename=" + new String(codedFileName.getBytes(), "ISO8859-1") + ".xls");
                } else {
                    response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
                }

                wb.write(output);

            }catch (Exception e){
                System.out.println(e);
            }finally {
                output.close();
            }
        }


    @RequestMapping(value = "importContractFile", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> importContractFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        HashMap<String,String>  result = new HashMap<> ();
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())==null){
            result.put("code","1");
            result.put("msg","请先登录！");
            return result;
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Workbook workbook = null;
            if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //操作人
            UserMain userMain = (UserMain) session.getAttribute(session.getId());
            //上载表格到库中
            if(!contractService.addContractList(sheet,userMain.getUserName())){
                result.put("code","2");
                result.put("msg","保存失败！");
            }else {
                result.put("code","0");
                result.put("msg","保存成功！");
            }
        } catch (IOException e) {
            //异常输出
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //异常输出

                }
            }
        }
        return result;
    }

    @RequestMapping(value = "importRRUFile", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> importRRUFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        HashMap<String,String>  result = new HashMap<> ();
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())==null){
            result.put("code","1");
            result.put("msg","请先登录！");
            return result;
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Workbook workbook = null;
            if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //操作人
           //UserMain userMain = (UserMain) session.getAttribute(session.getId());
            //上载表格到库中
            if(!siteServiceImpl.addRRUList(sheet)){
                result.put("code","2");
                result.put("msg","保存失败！");
            }else {
                result.put("code","0");
                result.put("msg","保存成功！");
            }
        } catch (IOException e) {
            //异常输出
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //异常输出

                }
            }
        }
        return result;
    }
    @RequestMapping(value = "importBBUFile", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> importBBUFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        HashMap<String,String>  result = new HashMap<> ();
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())==null){
            result.put("code","1");
            result.put("msg","请先登录！");
            return result;
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Workbook workbook = null;
            if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //操作人
            //UserMain userMain = (UserMain) session.getAttribute(session.getId());
            //上载表格到库中
            if(!siteServiceImpl.addBBUList(sheet)){
                result.put("code","2");
                result.put("msg","保存失败！");
            }else {
                result.put("code","0");
                result.put("msg","保存成功！");
            }
        } catch (IOException e) {
            //异常输出
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //异常输出

                }
            }
        }
        return result;
    }
    @RequestMapping(value = "importSite", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> importSite(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        HashMap<String,String>  result = new HashMap<> ();
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())==null){
            result.put("code","1");
            result.put("msg","请先登录！");
            return result;
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Workbook workbook = null;
            if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //操作人
            //UserMain userMain = (UserMain) session.getAttribute(session.getId());
            //上载表格到库中
            if(!siteServiceImpl.addSitManager(sheet)){
                result.put("code","2");
                result.put("msg","保存失败！");
            }else {
                result.put("code","0");
                result.put("msg","保存成功！");
            }
        } catch (IOException e) {
            //异常输出
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //异常输出

                }
            }
        }
        return result;
    }
    @RequestMapping(value = "importOLTFile", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> importOLTFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        HashMap<String,String>  result = new HashMap<> ();
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())==null){
            result.put("code","1");
            result.put("msg","请先登录！");
            return result;
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Workbook workbook = null;
            if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //操作人
            //UserMain userMain = (UserMain) session.getAttribute(session.getId());
            //上载表格到库中
            if(!siteServiceImpl.addOLTList(sheet)){
                result.put("code","2");
                result.put("msg","保存失败！");
            }else {
                result.put("code","0");
                result.put("msg","保存成功！");
            }
        } catch (IOException e) {
            //异常输出
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //异常输出

                }
            }
        }
        return result;
    }
    @RequestMapping(value = "importIPRANFile", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> importIPRANFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        HashMap<String,String>  result = new HashMap<> ();
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())==null){
            result.put("code","1");
            result.put("msg","请先登录！");
            return result;
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            Workbook workbook = null;
            if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
            }
            // 打开Excel中的第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //操作人
            //UserMain userMain = (UserMain) session.getAttribute(session.getId());
            //上载表格到库中
            if(!siteServiceImpl.addIpranList(sheet)){
                result.put("code","2");
                result.put("msg","保存失败！");
            }else {
                result.put("code","0");
                result.put("msg","保存成功！");
            }
        } catch (IOException e) {
            //异常输出
        }finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    //异常输出
                }
            }
        }
        return result;
    }
    private void setRRUPoi(HSSFRow row3, EquipmentRRUAAU equipmentBBU) {
        row3.createCell(0).setCellValue(equipmentBBU.getRruCode());
        row3.createCell(1).setCellValue(equipmentBBU.getRruName());
        row3.createCell(2).setCellValue(equipmentBBU.getDxCode());
        row3.createCell(3).setCellValue(equipmentBBU.getPower());
        row3.createCell(4).setCellValue(equipmentBBU.getNetworkType());
    }

    private void setBBUPoi(HSSFRow row3, EquipmentBBU equipmentBBU) {
        row3.createCell(0).setCellValue(equipmentBBU.getBbuCode());
        row3.createCell(1).setCellValue(equipmentBBU.getBbuName());
        row3.createCell(2).setCellValue(equipmentBBU.getDxCode());
        row3.createCell(3).setCellValue(equipmentBBU.getPower());
        row3.createCell(4).setCellValue(equipmentBBU.getNetworkType());
    }

    private boolean isRRU(Integer type) {
        if(type.equals(PoiTypeEnum.POI_TYPE_3G_RRU.getKey())
                ||type.equals(PoiTypeEnum.POI_TYPE_4G_RRU.getKey())
                ||type.equals(PoiTypeEnum.POI_TYPE_5G_AAU.getKey())){
            return true;
        }
        return false;
    }

    private boolean isBBU(Integer type) {
        if(type.equals(PoiTypeEnum.POI_TYPE_3G_BBU.getKey())
                ||type.equals(PoiTypeEnum.POI_TYPE_4G_BBU.getKey())
                ||type.equals(PoiTypeEnum.POI_TYPE_5G_BBU.getKey())){
            return true;
        }
        return false;
    }

    private void setContractPoi(HSSFRow row3, Contract contract) {
        row3.createCell(0).setCellValue(contract.getJifangName());
        row3.createCell(1).setCellValue(contract.getCounty());
        row3.createCell(2).setCellValue(contract.getAddress());
        row3.createCell(3).setCellValue(contract.getYearRental());
        row3.createCell(4).setCellValue(contract.getSunRental());
        row3.createCell(5).setCellValue(contract.getContractNum());
        row3.createCell(6).setCellValue(contract.getContractFirst());
        row3.createCell(7).setCellValue(contract.getPayee());
        row3.createCell(8).setCellValue(contract.getStartTime());
        row3.createCell(9).setCellValue(contract.getEndTime());
        row3.createCell(10).setCellValue(contract.getPayEndTime());
        row3.createCell(11).setCellValue(contract.getRoomTypeName());
        row3.createCell(12).setCellValue(contract.getTowerTypeName());
    }
    private void setOLTPoi(HSSFRow row3, EquipmentOLT equipmentOLT) {
        row3.createCell(0).setCellValue(equipmentOLT.getOltCode());
        row3.createCell(1).setCellValue(equipmentOLT.getOltName());
        row3.createCell(2).setCellValue(equipmentOLT.getDxCode());
        row3.createCell(3).setCellValue(equipmentOLT.getPower());
    }
    private void setIPRANPoi(HSSFRow row3, EquipmentIPRAN equipmentIPRAN) {
        row3.createCell(0).setCellValue(equipmentIPRAN.getIpranCode());
        row3.createCell(1).setCellValue(equipmentIPRAN.getIpranName());
        row3.createCell(2).setCellValue(equipmentIPRAN.getDxCode());
        row3.createCell(3).setCellValue(equipmentIPRAN.getPower());
    }
    private void setSitePoi(HSSFRow row3, SitManager site) {
        row3.createCell(0).setCellValue(site.getBaseName());
        row3.createCell(1).setCellValue(site.getDxCode());
        row3.createCell(2).setCellValue(site.getTtCode());
        row3.createCell(3).setCellValue(site.getBaseProperty());
        row3.createCell(4).setCellValue(site.getPowerMan());
        row3.createCell(5).setCellValue(site.getRentPayer());
        row3.createCell(6).setCellValue(site.getLongitude());
        row3.createCell(7).setCellValue(site.getLatitude());
        row3.createCell(8).setCellValue(site.getRemark());
    }
}
