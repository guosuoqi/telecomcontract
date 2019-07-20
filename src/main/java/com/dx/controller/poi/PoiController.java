package com.dx.controller.poi;

import com.dx.model.contract.Contract;
import com.dx.model.user.UserMain;
import com.dx.service.contract.ContractService;
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
    @Autowired
    private ContractService contractService;
        @RequestMapping("/createExcel")
        public void createExcel(HttpServletRequest request, HttpServletResponse response, String ids) throws IOException {
            HashMap<String, String>result = new HashMap<String, String>();
            OutputStream output=null;
            try {
                List<Contract>list= contractService.queryContractByIds(ids);

                String fileName="合同信息_"+ DateUtils.getDate();
                //创建HSSFWorkbook对象(excel的文档对象)
                HSSFWorkbook wb = new HSSFWorkbook();
                //建立新的sheet对象（excel的表单）
                HSSFSheet sheet=wb.createSheet(fileName);
                //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
                HSSFRow row1=sheet.createRow(0);
                //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
                HSSFCell cell=row1.createCell(0);
                //设置单元格内容
                cell.setCellValue("合同信息一览表");
                //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
                sheet.addMergedRegion(new CellRangeAddress(0,0,0,14));
                HSSFRow row2 = getHeadRow(sheet);
                for (int i = 0; i <list.size() ; i++) {
                    //在sheet里创建第三行
                    HSSFRow row3=sheet.createRow(2+i);
                    row3.createCell(0).setCellValue(list.get(i).getContractNum());
                    row3.createCell(1).setCellValue(list.get(i).getContractName());
                    row3.createCell(2).setCellValue(list.get(i).getCity()+"-"+list.get(i).getCounty());
                    row3.createCell(3).setCellValue(list.get(i).getYearRental());
                    row3.createCell(4).setCellValue(list.get(i).getSunRental());
                    row3.createCell(5).setCellValue(list.get(i).getContractFirst());
                    row3.createCell(6).setCellValue(list.get(i).getPayee());
                    row3.createCell(7).setCellValue(list.get(i).getPlanYear());
                    row3.createCell(8).setCellValue(list.get(i).getStartTime());
                    row3.createCell(9).setCellValue(list.get(i).getEndTime());
                    row3.createCell(10).setCellValue(list.get(i).getPayEndTime());
                    row3.createCell(11).setCellValue(list.get(i).getRoomTypeName());
                    row3.createCell(12).setCellValue(list.get(i).getTowerTypeName());
                    row3.createCell(13).setCellValue(list.get(i).getContractTypeName());
                }
                //输出Excel文件
                output=response.getOutputStream();
                response.reset();
                String agent = request.getHeader("USER-AGENT").toLowerCase();
                response.setContentType("application/vnd.ms-excel");
                String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                if (agent.contains("firefox")) {
                    response.setCharacterEncoding("utf-8");
                    response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
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

    private HSSFRow getHeadRow(HSSFSheet sheet) {
        //在sheet里创建第二行
        HSSFRow row2=sheet.createRow(1);
        //创建单元格并设置单元格内容
        row2.createCell(0).setCellValue("合同编号");
        row2.createCell(1).setCellValue("合同名字");
        row2.createCell(2).setCellValue("地址");
        row2.createCell(3).setCellValue("年租金");
        row2.createCell(4).setCellValue("总租金");
        row2.createCell(5).setCellValue("合同甲方");
        row2.createCell(6).setCellValue("收款人");
        row2.createCell(7).setCellValue("拟租年份");
        row2.createCell(8).setCellValue("开始时间");
        row2.createCell(9).setCellValue("结束时间");
        row2.createCell(10).setCellValue("付费截止日期");
        row2.createCell(11).setCellValue("机房类型");
        row2.createCell(12).setCellValue("塔栀类型");
        row2.createCell(13).setCellValue("合同类型");
        return row2;
    }

    @RequestMapping(value = "uploadBookFileData", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String,String> uploadBookFileData(@RequestParam("file") MultipartFile file, HttpServletRequest request){

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
                return result;
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

}
