package stan.inc.controllers.images;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stan.inc.models.image.TrialImage;
import stan.inc.models.user.User;
import stan.inc.repository.ImagesRepository;
import stan.inc.service.auth.UserService;

import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:8082") open for specific port
//@CrossOrigin() // open for all ports
@RequiredArgsConstructor
@RequestMapping("/api/v1/account/profilePic")
public class ImagesController {
  private final UserService userService;

    @Autowired
    ImagesRepository imageRepository;

    @PutMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam Long userId, @RequestParam MultipartFile file)
            throws IOException {
        User user = userService.findUserById(userId);
        TrialImage trialImage = new TrialImage();
//        trialImage.setImage(ImageUtility.compressImage(file.getBytes()));
        if(user.getTrialImage()==null){
            try {
                trialImage.setUser(user);
                trialImage.setImage(ImageUtility.compressImage(file.getBytes()));
                trialImage.setType(file.getContentType());
                trialImage.setName(file.getOriginalFilename());
                imageRepository.save(trialImage);
//                return ResponseEntity.status(HttpStatus.OK)
//                        .body(new ImageUploadResponse("Image uploaded successfully: " +
//                                file.getOriginalFilename()));
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ImageUploadResponse("two images exist: " +e));
            }

            }else {
            TrialImage trialImage1 = imageRepository.findByUserId(user.getId());
            trialImage1.setImage(ImageUtility.compressImage(file.getBytes()));
            trialImage1.setType(file.getContentType());
            trialImage1.setName(file.getOriginalFilename());
            imageRepository.save(trialImage1);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/info/{userId}"})
    public TrialImage getImageDetails(@PathVariable("userId") Long userId) throws IOException {

        final Optional<TrialImage> dbImage = Optional.ofNullable(imageRepository.findByUserId(userId));

        return TrialImage.builder()
                .id(dbImage.get().getId() )
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .user(dbImage.get().getUser())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }

    @GetMapping(path = "/get/{userId}"

//            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getImage(@PathVariable("userId") Long userId) throws IOException {

        Optional<TrialImage> dbImage = Optional.ofNullable(imageRepository.findByUserId(userId));

        if (dbImage.isPresent()) {
            byte[] imageBytes = ImageUtility.decompressImage(dbImage.get().getImage());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(dbImage.get().getType()));
            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/trial/{userId}"
//            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<byte[]> getFile(@PathVariable("userId") Long userId)throws IOException{
        Optional<TrialImage> dbImage = Optional.ofNullable(imageRepository.findByUserId(userId));
        byte[] base64encodedData = Base64.getEncoder().encode(dbImage.get().getImage());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+dbImage.get().getName()+"\"")
                .body(base64encodedData);

    }
}