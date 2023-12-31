package com.vatsal.com.equity.alert.controller;

import com.vatsal.com.equity.alert.service.DBLoaderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cmpFile")
public class DbLoaderController {
    private final DBLoaderService DBLoaderService;

    public DbLoaderController(DBLoaderService DBLoaderService) {
        this.DBLoaderService = DBLoaderService;
    }

    @GetMapping("/loadData")
    public String loadDataInDb()
            throws Exception {
        return DBLoaderService.loadDataInDb();
    }
}