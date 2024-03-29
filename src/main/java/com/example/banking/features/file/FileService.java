package com.example.banking.features.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    String uploadSingleFile(MultipartFile file);
    List<String> uploadMultipleFile(MultipartFile[] files);
    void deleteFile(String fileName);
}
