package com.example.batch_mode_demo.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BatchModeService {

    public void saveBatch(MultipartFile file) throws IOException;

}
