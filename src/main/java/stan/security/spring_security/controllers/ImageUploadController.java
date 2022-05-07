package stan.security.spring_security.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stan.security.spring_security.exceptions.ResourceNotFoundException;
import stan.security.spring_security.models.ImageModel;
import stan.security.spring_security.models.User;
import stan.security.spring_security.repository.ImageRepository;
import stan.security.spring_security.services.auth.UserService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/image")
public class ImageUploadController {
    private  final UserService userService;
    @Autowired
    ImageRepository imageRepository;

    @PostMapping(value = "/upload",produces = {MediaType.APPLICATION_JSON_VALUE})
    public HttpEntity<ImageModel> uploadImage(@RequestParam("userId") Long userId, @RequestParam("imageFile") MultipartFile file)
            throws ResourceNotFoundException{
        try {
            System.out.println("Original Image Byte Size - " + file.getBytes().length);
            ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                    compressBytes(file.getBytes()));
            User user = userService.findUserById(userId);
            img.setUser(user);
            imageRepository.save(img);
            return new ResponseEntity<>(img,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @GetMapping("/get/{userId}")
    public ResponseEntity<ImageModel> getImage(@PathVariable("userId") Long userId) throws ResourceNotFoundException {

        try {
            final Optional<ImageModel> retrievedImage = imageRepository.findImageById(userId);
            ImageModel img = new ImageModel(
//                retrievedImage.get().getUser(),
                    retrievedImage.get().getName(),
                    retrievedImage.get().getType(),
                    decompressBytes(retrievedImage.get().getPicByte())
            );
            return new ResponseEntity<>(img,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}