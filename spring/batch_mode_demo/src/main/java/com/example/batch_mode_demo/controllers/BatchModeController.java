package com.example.batch_mode_demo.controllers;

import com.example.batch_mode_demo.services.BatchModeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
public class BatchModeController {

    private BatchModeServiceImpl batchModeService;

    @Autowired
    public BatchModeController(BatchModeServiceImpl batchModeService) {
        this.batchModeService = batchModeService;
    }

    public ResponseEntity<HttpStatus> loadBatch(@RequestBody MultipartFile zipArchive) throws IOException {
        String fileName = zipArchive.getOriginalFilename();
        log.info("Got zip file: " + fileName);

        if (fileName == null || !fileName.endsWith(".zip")) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        batchModeService.saveBatch(zipArchive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
