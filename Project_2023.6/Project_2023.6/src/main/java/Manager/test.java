package Manager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        //创建Excel对象
        FileInputStream fis;
        try {
             fis = new FileInputStream("D:\\学习\\编程\\java\\Project_2023.6\\997309758用户的科目记录.xls");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HSSFSheet sheet = workbook.getSheetAt(0);

       int rowgeshu=sheet.getLastRowNum();

      HSSFRow row=sheet.getRow(10);
      int cellgeshu=row.getLastCellNum();
     HSSFCell cell=row.getCell(1);
    String s= cell.getStringCellValue();
        System.out.println(s);
    }
}
