package com.example.banking.features.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Value("${file_storage.image_location}")
    String fileLocation;
    @Override
    public String uploadSingleFile(MultipartFile file) {
        try{
            Path imageStorageLocation = Path.of(fileLocation);
            if (!Files.exists(imageStorageLocation)) {
                Files.createDirectories(imageStorageLocation);
            }
            String newFileName = UUID.randomUUID()+"."+file.getOriginalFilename().split("\\.")[1];
            Path targetLocation = imageStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return newFileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<String> uploadMultipleFile(MultipartFile[] files) {
        List<String> newFileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            try{
                Path imageStorageLocation = Path.of(fileLocation);
                if (!Files.exists(imageStorageLocation)) {
                    Files.createDirectories(imageStorageLocation);
                }
                String newFileName = UUID.randomUUID()+"."+file.getOriginalFilename().split("\\.")[1];
                Path targetLocation = imageStorageLocation.resolve(newFileName);
                Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                newFileNames.add(newFileName);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return newFileNames;
    }

    @Override
    public void deleteFile(String fileName) {
        try{
            Path imageStorageLocation = Path.of(fileLocation);
            Path targetLocation = imageStorageLocation.resolve(fileName);
            Files.delete(targetLocation);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
