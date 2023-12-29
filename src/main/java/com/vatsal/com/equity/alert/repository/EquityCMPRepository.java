package com.vatsal.com.equity.alert.repository;

import com.vatsal.com.equity.alert.Models.EquityCMP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquityCMPRepository extends JpaRepository<EquityCMP,Integer> {

    EquityCMP findByName(String name);
}
