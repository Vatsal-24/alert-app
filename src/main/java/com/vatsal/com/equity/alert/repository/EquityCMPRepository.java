package com.vatsal.com.equity.alert.repository;

import com.vatsal.com.equity.alert.models.EquityCMP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquityCMPRepository extends JpaRepository<EquityCMP,Integer> {

    EquityCMP findByName(String name);
}
