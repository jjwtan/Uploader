package com.ran.components.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ran on 2/12/2017.
 */
@RestController
public class UploadController {

    private final Logger logger = LoggerFactory.getLogger(UploadController.class);
    private static String UPLOADED_FOLDER = "D:/Admin/transferred";

    @PostMapping("/upload")
    public ResponseEntity uploadFiles(
            @RequestParam("files[]")MultipartFile[] uploadFiles
    ) {

        logger.info("received: " + uploadFiles.length + " files");


        try {
            saveUploadedFiles(Arrays.asList(uploadFiles));
        } catch (IOException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Successfully uploaded", HttpStatus.OK);
    }

    private void saveUploadedFiles(List<MultipartFile> multipartFiles) throws IOException {
        for(MultipartFile file : multipartFiles) {
            logger.info("saving file " + file.getOriginalFilename());
            if(file.isEmpty()) {
                //skip
            }
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, file.getBytes());

            String contents = new String(file.getBytes());
            logger.info("Contents:" + contents);
        }
    }
}
