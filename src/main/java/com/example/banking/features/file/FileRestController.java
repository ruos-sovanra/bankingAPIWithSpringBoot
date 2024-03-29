package com.example.banking.features.file;

import com.example.banking.features.file.dto.FileResponse;
import com.example.banking.utility.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("api/v1/files")
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;

    @PostMapping(value = "",consumes = "multipart/form-data")
    public BaseResponse<FileResponse> uploadFile(@RequestBody MultipartFile file) throws IOException {
        return BaseResponse.<FileResponse>createSuccess()
                .setPayload(new FileResponse(fileService.uploadSingleFile(file),"" ));
    }
    @PostMapping(value = "/multiple",consumes = "multipart/form-data")
    public BaseResponse<FileResponse> uploadMultipleFiles(@RequestBody MultipartFile[] files) {

        return BaseResponse.<FileResponse>createSuccess()
                .setPayload(new FileResponse( fileService.uploadMultipleFile(files).toString(),null ));
    }
    @DeleteMapping("/{fileName}")
    public BaseResponse<FileResponse> deleteFile(@PathVariable String fileName){
        fileService.deleteFile(fileName);
        return BaseResponse.<FileResponse>createSuccess()
                .setPayload(new FileResponse(null,"" ));
    }
}
