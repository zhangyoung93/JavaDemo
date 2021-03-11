package com.zy.demo.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文档工具类
 * @author zy
 */
public class DocumentUtil {

    /**
     * 读取excel文件
     * @param filePath 文件路径
     * @return 哈希表
     */
    public static Map<String,Object> readExcel(String filePath){
        //结果集
        Map<String,Object> map = null;
        InputStream is = null;
        //poi文件对象
        Workbook workbook;
        try {
            //文件路径
            Path path = Paths.get(filePath);
            if(!Files.exists(path)){
                return null;
            }
            //获取文件对象
            File file = path.toFile();
            //获取文件输入流
            is = new FileInputStream(file);
            if(file.getName().endsWith("xlsx")){
                //解析xlsx文件
                workbook = new XSSFWorkbook(is);
            }else if(file.getName().endsWith("xls")){
                //解析xls文件
                workbook = new HSSFWorkbook(is);
            }else{
                return null;
            }
            //解析sheet
            Sheet sheet = workbook.getSheetAt(1);
            if(sheet == null){
                return null;
            }
            //解析行
            Row row = sheet.getRow(1);
            //工作岗位
            Cell cell = row.getCell(0);
            if(cell == null){
                return null;
            }
            String post = cell.getStringCellValue();
            post = post.split("：")[1].replace(" ","");
            //应用级别
            cell = row.getCell(3);
            if(cell == null){
                return null;
            }
            String level = cell.getStringCellValue();
            level = level.split("：")[1].replace(" ","").replace("级","");
            map = new HashMap<>();
            map.put("post",post);
            map.put("level",level);
            Map<String,String> postLevelRelMap = new HashMap<>();
            //遍历行
            for(int rowNum = 3 ; rowNum <= sheet.getLastRowNum() ; rowNum++){
                row = sheet.getRow(rowNum);
                String authName;
                String authValue;
                //左侧小类
                cell = row.getCell(2);
                if(cell != null){
                    authName = cell.getStringCellValue();
                    for(int i = 3 ; i <= 6 ; i++){
                        cell = row.getCell(i);
                        if(cell != null){
                            authValue = String.valueOf((int)cell.getNumericCellValue());
                            if(cell.getCellStyle().getFillForegroundColor() == IndexedColors.RED.getIndex()){
                                postLevelRelMap.put(authName,authValue);
                            }
                        }
                    }
                }
                //右侧小类
                cell = row.getCell(9);
                if(cell != null){
                    authName = cell.getStringCellValue();
                    for(int i = 10 ; i <= 13 ; i++){
                        cell = row.getCell(i);
                        if(cell != null){
                            authValue = String.valueOf((int)cell.getNumericCellValue());
                            if(cell.getCellStyle().getFillForegroundColor() == IndexedColors.RED.getIndex()){
                                postLevelRelMap.put(authName,authValue);
                            }
                        }
                    }
                }
            }
            map.put("postLevelRel",postLevelRelMap);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(is != null){
                    is.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 批量读取文件
     * @param dirPath 批量文件所在的目录
     */
    public static List<Map<String,Object>> readFileBatch(String dirPath){
        List<Map<String,Object>> resultMapList = new ArrayList<>();
        List<String> failList = new ArrayList<>();
        try {
            Path path = Paths.get(dirPath);
            if(!Files.exists(path)){
                return null;
            }
            //遍历目录
            Files.walkFileTree(path, new FileVisitor<Path>() {
                //访问目录前触发
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
                //访问文件时触发
                @Override
                public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                    String fileName = path.toFile().getName();
                    if(fileName.endsWith("xlsx") || fileName.endsWith("xls")){
                        Map<String,Object> map = readExcel(path.toString());
                        if(map != null){
                            resultMapList.add(map);
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
                //访问文件失败时触发
                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    failList.add(file.toString());
                    return FileVisitResult.CONTINUE;
                }
                //访问目录后触发
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMapList;
    }
}
