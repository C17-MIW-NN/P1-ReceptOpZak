package nl.mitwnn.ch17.ctrl_z.receptopzak.model;

import jakarta.persistence.*;
import org.springframework.http.MediaType;

import java.awt.*;

/**
 * @author Sabien Ruijgrok
 * Image entity that stores an image with its name,type and data
 */

@Entity
public class Image {

    @Id @GeneratedValue
    private Long imageId;

    @Column(unique = true)
    private String fileName;
    private String contentType;

    @Lob
    private byte[] data;

    @Override
    public String toString() {
        return "Image{" +
                "id=" + imageId +
                ", fileName='" + fileName + '\'' +
                ", contentType=" + contentType +
                '}';
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public MediaType getContentType() {
        return MediaType.parseMediaType(contentType);
    }

    public void setContentType(MediaType contentType) {
        this.contentType = contentType.toString();
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
