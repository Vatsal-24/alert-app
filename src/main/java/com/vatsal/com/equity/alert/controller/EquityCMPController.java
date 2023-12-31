package com.vatsal.com.equity.alert.controller;

import com.vatsal.com.equity.alert.models.EquityCMP;
import com.vatsal.com.equity.alert.service.EquityCMPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equityCMP")
public class EquityCMPController {

    @Autowired
    private EquityCMPService equityCMPService;

    @PostMapping("/addData")
    public List<EquityCMP> savaEquityCMPData(@RequestBody List<EquityCMP> equityCMPData){
        System.out.println(equityCMPData.toString());
        return equityCMPService.saveEquityCMPData(equityCMPData);
    }

    @GetMapping("/getData/{name}")
    public EquityCMP getProductByName(@PathVariable String name){
        return equityCMPService.getEquityCMPByName(name);
    }

    @GetMapping("/getData")
    public List<EquityCMP> getEquityCMPData(){
        return equityCMPService.getEquityCMPData();
    }


}