package com.vatsal.com.equity.alert.service;

import com.vatsal.com.equity.alert.models.EquityCMP;
import com.vatsal.com.equity.alert.repository.EquityCMPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquityCMPService {

    @Autowired
    private EquityCMPRepository equityCMPRepository;

    public List<EquityCMP> saveEquityCMPData(List<EquityCMP> equityCMPData){
        System.out.println(equityCMPData.toString());
        return equityCMPRepository.saveAll(equityCMPData);
    }
    public EquityCMP getEquityCMPByName(String name){
        return equityCMPRepository.findByName(name);
    }

    public List<EquityCMP> getEquityCMPData(){
        return equityCMPRepository.findAll();
    }


}