package nl.mitwnn.ch17.ctrl_z.receptopzak.service;

import nl.mitwnn.ch17.ctrl_z.receptopzak.model.Image;
import nl.mitwnn.ch17.ctrl_z.receptopzak.repositories.ImageRepository;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * @author Sabien Ruijgrok
 * Doel van het project
 */

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void saveImage(MultipartFile file) throws IOException {

        imageRepository.findByFileName(file.getOriginalFilename())
                .ifPresent(imageRepository::delete);

        MediaType contentType = MediaType.IMAGE_JPEG;
        if (file.getContentType() != null) {
            contentType = MediaType.parseMediaType(file.getContentType());
        }

        Image image = new Image();
        image.setFileName(file.getOriginalFilename());
        image.setContentType(contentType);
        image.setData(file.getBytes());

        imageRepository.save(image);
    }

    public Image getImage(String fileName) {
        return imageRepository.findByFileName(fileName)
                .orElseThrow(() -> new NoSuchElementException(fileName));
    }

}

