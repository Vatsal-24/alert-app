package com.vatsal.com.equity.alert.controller;

import com.vatsal.com.equity.alert.service.FileService;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mhosein-abbasi 11/9/21
 */
@RestController
@RequestMapping("/file")
public class Controller {
    private final FileService fileService;

    public Controller(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/upload")
    public String upload()
            throws Exception {
        return fileService.upload();
    }
}