package com.vatsal.com.equity.alert.controller;

import com.vatsal.com.equity.alert.Models.EquityCMP;
import com.vatsal.com.equity.alert.service.EquityCMPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EquityCMPController {

    @Autowired
    private EquityCMPService equityCMPService;

    @PostMapping("/addData")
    public List<EquityCMP> savaEquityCMPData(@RequestBody List<EquityCMP> equityCMPData){
        System.out.println(equityCMPData.toString());
        return equityCMPService.saveEquityCMPData(equityCMPData);
    }

//    @GetMapping("/getProductByName/{name}")
//    public Product getProductByName(@PathVariable String name){
//        return productService.getProductByName(name);
//    }
//
    @GetMapping("/getData")
    public List<EquityCMP> getEquityCMPData(){
        return equityCMPService.getEquityCMPData();
    }
//
//    @PutMapping("/updateProduct")
//    public Product updateProduct(@RequestBody Product product){
//        return productService.updateProduct(product);
//    }

}