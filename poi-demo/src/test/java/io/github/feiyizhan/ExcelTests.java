package io.github.feiyizhan;

import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author 徐明龙 XuMingLong 2022-06-17
 */
public class ExcelTests {
    @Data
    static class Menu{
        String code;
        String name;
        String parentCode;
        int level;
    }


    /**
     *  测试Excel 单元格填充背景色
     * @author 徐明龙 XuMingLong 2022-06-27
     * @param
     * @return void
     */
    private static void test3(){
        String outFile = "d:\\group_test2.xlsx";
        String outFile2 = "d:\\group_test3.xlsx";
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new File(outFile));
            String sheetName = "菜单列表";
            XSSFSheet worksheet = workbook.getSheet(sheetName);
            XSSFRow row = worksheet.getRow(0);


            XSSFCell cell3 = row.createCell(4);
            XSSFCellStyle cellStyle3 = workbook.createCellStyle();
            XSSFColor xssfColor3 = new XSSFColor();
            xssfColor3.setIndexed(IndexedColors.BLUE.getIndex());
            cellStyle3.setFillForegroundColor(IndexedColors.RED.getIndex());
            cellStyle3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell3.setCellStyle(cellStyle3);
//            cellStyle3.setFillPattern();


            workbook.write(new FileOutputStream(outFile2));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试Excel 单元格填充背景色
     * @author 徐明龙 XuMingLong 2022-06-27
     * @param
     * @return void
     */
    private static void test2(){
        String outFile = "d:\\group_test2.xlsx";
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            String sheetName = "菜单列表";
            XSSFSheet worksheet = workbook.getSheet(sheetName);
            if(worksheet !=null){
                workbook.removeSheetAt(workbook.getSheetIndex(sheetName));
            }
            worksheet = workbook.createSheet(sheetName);
            XSSFRow row = worksheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("ABC");
            changeCellBackgroundColor(cell);

            XSSFCell cell2 = row.createCell(1);
            changeCellBackgroundColorWithPattern(cell2);

            workbook.write(new FileOutputStream(new File(outFile)));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void changeCellBackgroundColor(XSSFCell cell) {
        XSSFCellStyle cellStyle = cell.getCellStyle();
        if(cellStyle == null) {
            cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        }
        XSSFColor clr = new XSSFColor();

        clr.setIndexed(IndexedColors.RED.getIndex());

        cellStyle.setFillForegroundColor(clr);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(cellStyle);
    }

    public static void changeCellBackgroundColorWithPattern(Cell cell) {
        CellStyle cellStyle = cell.getCellStyle();
        if(cellStyle == null) {
            cellStyle = cell.getSheet().getWorkbook().createCellStyle();
        }
        cellStyle.setFillBackgroundColor(IndexedColors.BLACK.index);
        cellStyle.setFillPattern(FillPatternType.BIG_SPOTS);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        cell.setCellStyle(cellStyle);
    }

    /**
     * 测试Excel 分组
     * @author 徐明龙 XuMingLong 2022-06-27
     * @param
     * @return void
     */
    @Test
    public void test1(){
        String outFile = "d:\\group_test.xlsx";
        try {
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook();
            List<Menu> menuList = getMenuList();
            writeMenuList(xssfWorkbook,menuList);
            xssfWorkbook.write(new FileOutputStream(new File(outFile)));
            xssfWorkbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Menu> getMenuList(){
        List<Menu> menuList = new ArrayList<>();
        for(int a=0;a<3;a++){
            Menu firstMenu = new Menu();
            firstMenu.setCode("A"+(a+1));
            firstMenu.setName("一级菜单"+(a+1));
            firstMenu.setParentCode("0");
            firstMenu.setLevel(0);
            menuList.add(firstMenu);
            for(int b=0;b<3;b++){
                Menu secondMenu = new Menu();
                secondMenu.setCode(firstMenu.getCode()+"-B"+(b+1));
                secondMenu.setName("二级菜单"+(b+1));
                secondMenu.setParentCode(firstMenu.getCode());
                secondMenu.setLevel(1);
                menuList.add(secondMenu);
                for(int c=0;c<3;c++){
                    Menu thirdMenu = new Menu();
                    thirdMenu.setCode(secondMenu.getCode()+"-C"+(c+1));
                    thirdMenu.setName("三级菜单"+(c+1));
                    thirdMenu.setParentCode(secondMenu.getCode());
                    thirdMenu.setLevel(2);
                    menuList.add(thirdMenu);
                }
            }
        }
        return menuList;
    }
    private static XSSFCellStyle getTitleCellStyle(XSSFWorkbook workbook){
        XSSFCellStyle titleCellStyle =workbook.createCellStyle();
        //设置蓝色背景色
        titleCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        titleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //样式字体
        XSSFFont titleFont = workbook.createFont();
        //加粗
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        //白色
        titleFont.setColor(IndexedColors.WHITE.getIndex());
        //设置字体大小
        titleFont.setFontHeightInPoints((short)13);
        titleCellStyle.setFont(titleFont);
        //设置居中对齐
        titleCellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置边框
        titleCellStyle.setBorderTop(BorderStyle.THICK);
        titleCellStyle.setBorderBottom(BorderStyle.THICK);
        titleCellStyle.setBorderLeft(BorderStyle.THICK);
        titleCellStyle.setBorderRight(BorderStyle.THICK);
        return titleCellStyle;

    }

    private static XSSFCellStyle getDataCellStyle(XSSFWorkbook workbook){
        XSSFCellStyle dataCellStyle =workbook.createCellStyle();
        //样式字体
        XSSFFont dataFont = workbook.createFont();
        //加粗
        dataFont.setFontName("微软雅黑");
        //设置字体大小
        dataFont.setFontHeightInPoints((short)10);
        dataCellStyle.setFont(dataFont);
        //设置居中对齐
        dataCellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置边框
        dataCellStyle.setBorderTop(BorderStyle.MEDIUM);
        dataCellStyle.setBorderBottom(BorderStyle.MEDIUM);
        dataCellStyle.setBorderLeft(BorderStyle.MEDIUM);
        dataCellStyle.setBorderRight(BorderStyle.MEDIUM);
        return dataCellStyle;

    }

    /**
     * 测试Excel分组功能
     * @author 徐明龙 XuMingLong 2022-06-27
     * @param workbook
     * @param menuList
     * @return void
     */
    private static void writeMenuList(XSSFWorkbook workbook, List<Menu> menuList ) {
        String sheetName = "菜单列表";
        XSSFSheet worksheet = workbook.getSheet("sheetName");
        if(worksheet !=null){
            workbook.removeSheetAt(workbook.getSheetIndex("sheetName"));
        }
        worksheet = workbook.createSheet(sheetName);

        XSSFCellStyle titleCellStyle = getTitleCellStyle(workbook);
        // row 0: title
        int cRow = 0;
        XSSFRow row = worksheet.createRow(cRow);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("菜单编号");
        cell.setCellStyle(titleCellStyle);
        worksheet.setColumnWidth(0,20*256);

        cell = row.createCell(1);
        cell.setCellValue("菜单名称");
        cell.setCellStyle(titleCellStyle);
        worksheet.setColumnWidth(1,50*256);

        cell = row.createCell(2);
        cell.setCellValue("上级菜单编号");
        cell.setCellStyle(titleCellStyle);
        worksheet.setColumnWidth(2,30*256);

        // row 1: 菜单编号 | 菜单名称 | 上级菜单编号
        Deque<Integer> levelDeque = new ArrayDeque<>();
        String parentCode = "";
        XSSFCellStyle dataCellStyle1 = getDataCellStyle(workbook);
        dataCellStyle1.setFillForegroundColor(IndexedColors.YELLOW1.getIndex());
        dataCellStyle1.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFCellStyle dataCellStyle2 = getDataCellStyle(workbook);
        dataCellStyle2.setFillForegroundColor(IndexedColors.PINK.getIndex());
        dataCellStyle2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFCellStyle dataCellStyle3 = getDataCellStyle(workbook);
        dataCellStyle3.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        dataCellStyle3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        List<XSSFCellStyle> dataCellStyleList = Arrays.asList(dataCellStyle1,dataCellStyle2,dataCellStyle3);
        XSSFCellStyle dataCellStyle = null;
        Map<String,Integer> groupBeginRowMap = new HashMap<>();
        Map<String,Integer> groupEndRowMap = new HashMap<>();
        for (Menu menu : menuList) {
            cRow++;
            row = worksheet.createRow(cRow);
            if(!Objects.equals(parentCode,menu.getParentCode())){
                Integer groupBeginRow = groupBeginRowMap.get(menu.getParentCode());
                if(groupBeginRow==null){
                    groupBeginRowMap.put(menu.getParentCode(),cRow);
                }else{
                    groupEndRowMap.put(menu.getParentCode(),cRow);
                }
            }else{
                groupEndRowMap.put(menu.getParentCode(),cRow);
            }
            dataCellStyle = dataCellStyleList.get(menu.getLevel());
            parentCode = menu.getParentCode();
            cell = row.createCell(0);
            cell.setCellStyle(dataCellStyle);
            cell.setCellValue(menu.getCode());
            cell = row.createCell(1);
            cell.setCellStyle(dataCellStyle);
            cell.setCellValue(menu.getName());
            cell = row.createCell(2);
            cell.setCellStyle(dataCellStyle);
            cell.setCellValue(menu.getParentCode());
        }
        //组合
        final XSSFSheet groupWorksheet = worksheet;
        groupBeginRowMap.forEach((k,v)->{
            groupWorksheet.groupRow(v,groupEndRowMap.get(k));
            groupWorksheet.setRowGroupCollapsed(v,true);
        });

    }


    private void setBold(HSSFWorkbook workbook, HSSFCell cell) {
    }
}
