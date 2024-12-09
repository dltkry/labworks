package com.example.demo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
public class PhotoService {
    @Value("${upload.dir}")
    private String uploadDir;
    private final PhotoRepository photoRepository;
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }
    public Photo uploadPhoto(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, file.getBytes());

        Photo photo = new Photo();
        photo.setFileName(fileName);
        return photoRepository.save(photo);
    }
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }
}

