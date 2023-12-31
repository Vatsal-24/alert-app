package com.vatsal.com.equity.alert.service;

import com.vatsal.com.equity.alert.models.Alert;
import com.vatsal.com.equity.alert.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public Alert saveAlert(Alert alert) {
        System.out.println(alert.toString());
        return alertRepository.save(alert);
    }

    public List<Alert> getAlerts() {
        return alertRepository.findAll();
    }

    public String deleteAlert(int id){
        System.out.println("Inside delete service");
        alertRepository.deleteById(id);
        return "Alert deleted";
    }

}
