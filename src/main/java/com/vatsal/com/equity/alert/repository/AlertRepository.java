package com.vatsal.com.equity.alert.repository;

import com.vatsal.com.equity.alert.models.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Integer> {
}
