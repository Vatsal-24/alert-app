package com.vatsal.com.equity.alert.service;

import com.vatsal.com.equity.alert.Models.EquityCMP;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Service
public class FileService {

    private final WebClient webClient;

    public FileService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081/").build();
    }

    public String upload() throws IOException {

        String excelFilePath = "./src/main/resources/static/sample.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workbook.getSheetAt(0);

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
        for (EquityCMP equityCMP : dataList) {
            System.out.println(equityCMP.getName() + ", " + equityCMP.getCmp());
        }

        webClient.post()
                .uri("/addData")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(dataList))
                .retrieve()
                .bodyToMono(Void.class) // Use the appropriate class for your response
                .subscribe();
        return "OK";
    }
}