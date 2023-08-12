//package stan.inc.controllers.images;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import stan.inc.models.user.Image;
//import stan.inc.models.user.User;
//import stan.inc.repository.ImagesRepository;
//import stan.inc.service.auth.UserService;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.UUID;
//
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/account/profilePic")
//public class ProfilePicController{
//    private static final String UPLOAD_DIR = "uploads/";
//    private final UserService userService;
//    @Autowired
//    private ImagesRepository imageRepository;
//
//    @PostMapping("/upload")
//    public ResponseEntity<Image> uploadImage(@RequestParam Long userId,@RequestParam("file") MultipartFile file) throws IOException {
//     St   User user = userService.findUserById(userId);
////        ring fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
//        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
//        String newFileName = UUID.randomUUID().toString() + fileExtension;
//        Path uploadPath = Paths.get(UPLOAD_DIR);
//
//        if (!Files.exists(uploadPath)) {
//            Files.createDirectories(uploadPath);
//        }
//
//        Path filePath = uploadPath.resolve(newFileName);
//
//        Files.copy(file.getInputStream(), filePath);
//
//        Image image = new Image();
//        image.setName(newFileName);
//        image.setUrl("/api/v1/images/" + newFileName);
//        image.setUser(user);
//
//        imageRepository.save(image);
//
//        return new ResponseEntity<>(image, HttpStatus.OK);
//    }
//
//    @GetMapping("/pic")
//    public ResponseEntity<Resource> downloadImage(@RequestParam Long userId , HttpServletRequest request) throws MalformedURLException {
//        Optional<Image> image = imageRepository.findByUserId(userId);
//        String fileName = image.get().getName();
//
//        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).normalize();
//        Resource resource = new UrlResource(filePath.toUri());
//
//        if (!resource.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            System.out.println("Could not determine file type.");
//        }
//
//        if (contentType == null) {
//            contentType = "application/octet-stream";
//        }
//
//        return ResponseEntity.ok()
//                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
//                .body(resource);
//    }
//}