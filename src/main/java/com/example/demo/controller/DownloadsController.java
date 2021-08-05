package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DownloadsController {

    @Value("${files_uploading_directory}")
    public String uploadingDir ;

    @RequestMapping(value = "/files/download2/{fileName:.+}")
    @ResponseBody
    public byte[] getImage(@PathVariable String fileName) throws IOException {

        File serverFile = new File(uploadingDir + fileName);

        return Files.readAllBytes(serverFile.toPath());
    }

    @GetMapping("/files/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        Path path = Paths.get(uploadingDir + fileName);
        Resource resource = null;
        String content_type="image/png";
        try {
            content_type= Files.probeContentType(path);
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(content_type))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
