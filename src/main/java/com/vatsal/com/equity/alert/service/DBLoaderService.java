package com.vatsal.com.equity.alert.service;

import com.vatsal.com.equity.alert.models.EquityCMP;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBLoaderService {

    private final WebClient webClient;

    public DBLoaderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/").build();
    }

    public String loadDataInDb() throws IOException {


        //  Reading Data from RDH file (RDH_Equity_CMP.xlsx)
        String excelFilePath = "./src/main/resources/static/RDH_Equity_CMP.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheetAt(0);
        //  Getting number of rows and columns
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();

        List<EquityCMP> dataList = new ArrayList<>();

        for(int r=0;r<=rows;r++){
            XSSFRow row = sheet.getRow(r);
            String name="";
            double price=0;
            for(int c=0;c<cols;c++){
                XSSFCell cell = row.getCell(c);
                switch (cell.getCellType()) {
                    case STRING -> name = cell.getStringCellValue();
                    case NUMERIC -> price = cell.getNumericCellValue();
                }
            }
            EquityCMP dataModel = new EquityCMP(name, price);
            dataList.add(dataModel);
        }
//        API CALL: Writing in DB

        webClient.post()
                .uri("/equityCMP/addData")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(dataList))
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
        return "OK";
    }
}