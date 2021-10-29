package com.example.batch_mode_demo.services;

import com.example.batch_mode_demo.entities.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Service
public class BatchModeServiceImpl implements BatchModeService {

    @Override
    public void saveBatch(MultipartFile zipArchive) throws IOException {
        if (zipArchive != null && !zipArchive.isEmpty()) {
            Path tempFile = Files.createTempFile(zipArchive.getOriginalFilename() + "_temp", null);
            Files.write(tempFile, zipArchive.getBytes());
            File file = tempFile.toFile();
            ZipFile zipFile = new ZipFile(file, Charset.forName("CP866"));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            ObjectMapper mapper = new ObjectMapper();

            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                User user = extractUser(zipFile, zipEntry, mapper);
                System.out.println(zipEntry);
                System.out.println(user);
            }

            Files.deleteIfExists(tempFile.toAbsolutePath());
        } else {
            System.out.println("zipArchive is null or Empty");
        }

    }

    private User extractUser(ZipFile zipFile, ZipEntry zipEntry, ObjectMapper mapper) throws IOException {
        InputStream fis = zipFile.getInputStream(zipEntry);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fis.read(buffer)) != -1) {
            baos.write(buffer, 0 , length);
        }
        return mapper.readValue(buffer, User.class);

//        return StandardCharsets.UTF_8
//                .newDecoder()
//                .decode(ByteBuffer.wrap(buffer))
//                .toString();

    }
}
