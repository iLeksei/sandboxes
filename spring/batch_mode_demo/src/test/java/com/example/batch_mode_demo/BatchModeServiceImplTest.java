package com.example.batch_mode_demo;


import com.example.batch_mode_demo.services.BatchModeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class BatchModeServiceImplTest {

    @Autowired
    private BatchModeServiceImpl batchModeService;

    private MultipartFile multipartFile;

    private File zipFile;

    private List<String> tempJsons = new ArrayList<>();

    @BeforeAll
    public void setUp() throws IOException {
        zipFile = createZipWithUsers(20);
        multipartFile = new MockMultipartFile(
                "test_users.zip",
                zipFile.getName(),
                "text/plain",
                new BufferedInputStream(new FileInputStream(zipFile))
        );
    }

    @AfterAll
    public void cleanUp() throws IOException {
        Files.deleteIfExists(Paths.get(zipFile.getPath()));
        tempJsons.forEach((tempJsonPath) -> {
            try {
                Files.deleteIfExists(Paths.get(tempJsonPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Test
    public void shouldParseZipFile() throws IOException {
        batchModeService.saveBatch(multipartFile);
    }

    private String createUserJson() {
        List<String> names = Arrays.asList(
                "Bob", "Alex", "Marry", "David", "Harry", "Jim", "Jessica", "Olivia", "Charlie", "Hillary");
        List<String> surnames = Arrays.asList(
                "Potter", "Forest", "Smith", "Ivanov", "Jackson", "Petrov", "Stone", "Kovalsky", "Korolev", "Demidov");
        int prevCentury = 1900;
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode user = mapper.createObjectNode();
        user.put("firstName", names.get(new Random().nextInt(names.size() - 1)));
        user.put("sureName", surnames.get(new Random().nextInt(names.size() - 1)));
        user.put("birthDate", LocalDate.of(
                new Random().nextInt(100) + prevCentury,
                new Random().nextInt(11) + 1,
                new Random().nextInt(20) + 1).toString()
        );

        return user.toString();
    }

    private File createZipWithUsers(int amount) throws IOException {
        String tempZipPath = Paths.get(".").normalize().toAbsolutePath() + "/target/test-classes/";
        Path usersZip = Files.createTempFile(Paths.get(tempZipPath), "tmp_users_", ".zip");
        File zipTempFile = usersZip.toFile();
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(usersZip.toUri())));

        for (int i = 1; i <= amount; i++) {
            String user = createUserJson();
            // create temp json and write user in it
            Path tempFile = Files.createTempFile("user_" + i, ".json");

            FileWriter fileWriter = new FileWriter(tempFile.toFile());
            fileWriter.append(user);
            fileWriter.close();
            tempJsons.add(tempFile.toAbsolutePath().toString());

            ZipEntry zipEntry = new ZipEntry("user_" + i + ".json");
            zos.putNextEntry(zipEntry);
            zos.write(user.getBytes(), 0, user.getBytes().length);
        }
        zos.close();
        return zipTempFile;
    }

}
