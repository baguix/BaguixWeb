/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.doc;

import com.baguix.utils.data.DateTool;
import com.baguix.utils.data.ReflectTool;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * <b>Excle(.xls)电子表格文件生成器</b><br>
 *     必须制作一个.xls的模版文件，根据传入的List<obj>进行渲染。<br>
 *     模版中第65535行作为参数设置行<br>
 * @author Scott(SG)
 */
@SuppressWarnings("unchecked")
public class ExcelGenerator<T> {
    private String template;
    private Collection<T> list;

    // 构造方法
    public ExcelGenerator(String template, List<T> list) {
        this.template = template;
        this.list = list;
    }

    public ByteArrayOutputStream genXls() {
        //完成反射后，将数据写入excel电子表格
        HSSFWorkbook xls = writeXls();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            if (xls != null) {
                xls.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return os;
    }

    public ByteArrayOutputStream getXlsOS(HSSFWorkbook xls) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            if (xls != null) {
                xls.write(os);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return os;
    }

    public HSSFWorkbook getXls() {
        return writeXls();
    }

    //数据模型
    private Map getRowModel(T obj) {
        ReflectTool<T> rf = new ReflectTool<T>();
        // 定义数据模型
        Map map = rf.simpleBean2Map(obj);
        return map;
    }

    //写整表数据
    private HSSFWorkbook writeXls() {
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(template));
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet1 = wb.getSheetAt(0);
            HSSFRow inforow = sheet1.getRow(65535);
            // 行号, 列号, 列数
            int start_row = 1;
            int start_column = 0;
            int col_number = 10;
            // 读取模版中65536行上的配置参数
            if (inforow.getCell(1).getCellType() == inforow.getCell(1).CELL_TYPE_NUMERIC) {
                start_row = (int) inforow.getCell(1).getNumericCellValue() - 1;
            }
            if (inforow.getCell(3).getCellType() == inforow.getCell(3).CELL_TYPE_NUMERIC) {
                start_column = (int) inforow.getCell(3).getNumericCellValue() - 1;
            }
            if (inforow.getCell(5).getCellType() == inforow.getCell(5).CELL_TYPE_NUMERIC) {
                col_number = (int) inforow.getCell(5).getNumericCellValue();
            }
            sheet1.removeRow(inforow);
            // 读取开始行row1上的每个单元格的格式和字段名
            HSSFRow row1 = sheet1.getRow(start_row);
            List<HSSFCellStyle> styles = new ArrayList<HSSFCellStyle>();
            List<String> fields = new ArrayList<String>();

            for (int i = start_column; i < col_number; i++) {
                styles.add(row1.getCell(i).getCellStyle());
                String field = row1.getCell(i).getStringCellValue();
                fields.add(field);
            }

            // 开始用数据填充
            int j = start_row;
            for (T obj : list) {
                Map row = getRowModel(obj);
                HSSFRow hrow = sheet1.getRow(j);
                if (hrow == null) {
                    hrow = sheet1.createRow(j);
                }

                // 根据需要填写的字段名填充该行
                for (int i = start_column; i < col_number; i++) {
                    String key = fields.get(i);
                    String value = (String) row.get(key);
                    HSSFCell cell = hrow.getCell(i);
                    if (cell == null) {
                        cell = hrow.createCell(i);
                    }
                    HSSFCellStyle style = styles.get(i);
                    // 向单元格填入数据
                    cell.setCellValue(value);
                    cell.setCellStyle(style);
                }
                j++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return wb;
    }

    /**
     * 每个单元格的数据都转成String类型
     *
     * @param hssfCell
     * @return
     */
    private String getValue(HSSFCell hssfCell) {
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}