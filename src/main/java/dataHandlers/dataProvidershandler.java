package main.java.dataHandlers;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class dataProvidershandler {

    public static String[][] fileHandler(String filename, String sheetname) throws IOException {

        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetname);

        int totalRows = sheet.getLastRowNum();
        int totalColumns = sheet.getRow(1).getLastCellNum();
        String[][] dataObject = new String[totalRows][totalColumns];

        for (int i=0;i<totalRows;i++){
            for (int j=0;j<totalColumns;j++){
                dataObject[i][j]= String.valueOf(sheet.getRow(i+1).getCell(j));
            }
        }
        return dataObject;
    }


}
