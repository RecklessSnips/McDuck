package com.example.mcduckshoppingwebsite.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import java.nio.file.*;
import java.util.List;

@RestController
public class FileUploadController {

    @PostMapping("/api/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("demo[]") List<MultipartFile> files) {
        if (files.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded.");
        }

        // 从这里开始处理每个文件
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    Path destinationDirectory = Paths.get("/Users/Ahsoka/McDuck-ShoppingWebsite/McDuck/src/assets/images");
                    Files.createDirectories(destinationDirectory);
                    Path destinationFile = destinationDirectory.resolve(file.getOriginalFilename());

                    // 创建临时文件并移动到最终目录
                    Path tempFile = Files.createTempFile(destinationDirectory, null, "-" + file.getOriginalFilename());
                    file.transferTo(tempFile);
                    Files.move(tempFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                    // 确认文件已经移动，然后删除临时文件
//                    if (Files.exists(destinationFile) && Files.deleteIfExists(tempFile)) {
//                        System.out.println("Original file deleted successfully.");
//                    }
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload and move file: " + e.getMessage());
                }
            }
        }
        return ResponseEntity.ok("Files uploaded and moved successfully.");
    }

    /*
    这个异常处理器会捕获任何由于文件过大而抛出的MaxUploadSizeExceededException，
    并返回一个状态码413 Payload Too Large，告诉用户上传的文件太大。
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Cannot upload file! File is too large.");
    }
}
