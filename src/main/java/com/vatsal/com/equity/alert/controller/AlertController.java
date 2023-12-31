package com.vatsal.com.equity.alert.controller;

import com.vatsal.com.equity.alert.models.Alert;
import com.vatsal.com.equity.alert.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alert")
public class AlertController {

    @Autowired
    private AlertService alertService;

    @PostMapping("/")
    public Alert saveAlert(@RequestBody Alert alert){
        System.out.println(alert.toString());
        return alertService.saveAlert(alert);
    }
    @GetMapping("/")
    public List<Alert> getAlerts(){
        return alertService.getAlerts();
    }

    @DeleteMapping("/{id}")
    public String deleteAlert(@PathVariable int id){
        return alertService.deleteAlert(id);
    }

}
