package stan.inc.controllers.property;

import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stan.inc.controllers.images.ImageUploadResponse;
import stan.inc.controllers.images.ImageUtility;
import stan.inc.models.property.Property;
import stan.inc.models.property.PropertyImages;
import stan.inc.service.property.PropertyService;
import stan.inc.service.propertyImages.PropertyImagesImpl;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account/propertyPic")
public class PropertyImageController {

    @Autowired
    PropertyImagesImpl propertyImages;
    @Autowired
    PropertyService propertyService;

    @PostMapping("/upload")
    public ResponseEntity<List<ImageUploadResponse>> uploadImages(@RequestParam Long propertyId, @RequestParam("files") List<MultipartFile> files)
            throws IOException {
        Property property = propertyService.findPropertyById(propertyId);

        List<ImageUploadResponse> uploadResponses = new ArrayList<>();
        List<PropertyImages> imagesList = new ArrayList<>();
        for (MultipartFile file : files) {
            PropertyImages image = new PropertyImages();
            image.setImage(ImageUtility.compressImage(file.getBytes()));
            image.setProperty(property);
            image.setType(file.getContentType());
            image.setName(file.getOriginalFilename());
            imagesList.add(image);

        }
        propertyImages.saveAllPropImages(imagesList);
        //TODO: iterate to get name for each file
        uploadResponses.add(new ImageUploadResponse("Image uploaded successfully: "));


        return ResponseEntity.status(HttpStatus.OK).body(uploadResponses);
    }

        @GetMapping("/getPropertyImages")
//    public ResponseEntity<List<PropertyImages>> getPropImages(@RequestParam Long propertyId)
//            throws IOException {
//        List<PropertyImages> propImages = propertyImages.getAllPropImages(propertyId);
//        return new ResponseEntity<>(propImages, HttpStatus.OK);
//
//    }
    public ResponseEntity<byte[]> getImage(@RequestParam Long propertyId) throws IOException {

        List<PropertyImages> propImages = propertyImages.getAllPropImages(propertyId);
        List<byte[]> results = new ArrayList<>();
//        List<byte[]> propImageBytes = new ArrayList<>();
        if (!propImages.isEmpty()) {
            for (PropertyImages propImage : propImages) {
                byte[] imageBytes = ImageUtility.decompressImage(propImage.getImage());
             results.add(imageBytes);
             System.out.println(results);
            }
//            List<HttpHeaders> headers = new ArrayList<>();
////                HttpHeaders headers = new HttpHeaders();
//                for(int j = 0; j<results.size(); j++) {
//                    HttpHeaders head = new HttpHeaders();
//                    head.setContentType(MediaType.valueOf(propImages.get(j).getType()));
//                    headers.add(head);
//                }
            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.valueOf(propImages.get(i).getType()));
            int i=0;
            List<ResponseEntity<byte[]>> responseEntities = new ArrayList<>();
            while(i<results.size()) {
                headers.setContentType(MediaType.valueOf(propImages.get(i).getType()));
                 ResponseEntity<byte[]> b = new ResponseEntity<>(results.get(i), headers, HttpStatus.OK);
                 i++;
                 responseEntities.add(b);
            }
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }
//    @GetMapping(value = "/getPropertyImages")
//    @GetMapping(value = "/getPropertyImages", produces = MediaType.IMAGE_JPEG_VALUE)
//    public ResponseEntity<List<byte[]>> getImage(@RequestParam Long propertyId) throws IOException {
//        List<PropertyImages> propImages = propertyImages.getAllPropImages(propertyId);
//        List<byte[]> results = new ArrayList<>();
//        if (!propImages.isEmpty()) {
//            for (PropertyImages propImage : propImages) {
//                byte[] imageBytes = ImageUtility.decompressImage(propImage.getImage());
//                results.add(imageBytes);
//            }
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.IMAGE_JPEG);
//            return new ResponseEntity<>(results, headers, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//

//    public List<Resource> getImage(@RequestParam Long propertyId) throws IOException {
//        List<PropertyImages> propImages = propertyImages.getAllPropImages(propertyId);
//        List<Resource> results = new ArrayList<>();
//        List<HttpHeaders> headers = new ArrayList<>();
//        if (!propImages.isEmpty()) {
//            for (int i=0;i<propImages.size();i++) {
//                for(int j=0;j<propImages.size();j++){
//                    HttpHeaders header = new HttpHeaders();
//                    header.setContentType(MediaType.valueOf(propImages.get(i).getType()));
//                    headers.add(header);
//                }
//                byte[] imageBytes = ImageUtility.decompressImage(propImages.get(i).getImage());
//              results.add(new ByteArrayResource(imageBytes));
//            }
////            System.out.println(results.size());
//            for (int i=0; i<results.size();i++){
//                for(int j =0; j< headers.size();j++){
////                    return new ResponseEntity<>(results.get(i),headers.get(j),HttpStatus.OK);
//                }
//         }
//            return (results);
//        }
//        System.out.println(results);
////        return new ResponseEntity<>(HttpStatus.OK);
//
//        return results;
//    }


//
//
//
//    public ResponseEntity<byte[]> getallimages(@RequestParam Long propertyId) throws IOException {
//
//        List<PropertyImages> propImages = propertyImages.getAllPropImages(propertyId);
//        List<String> imagenames = new ArrayList<>();
//        List<String> contenttypes = new ArrayList<>();
//        List<byte[]> imagesdata = new ArrayList<>();
//        int i = 0;
//        while (i < propImages.size()) {
//            imagenames.add(propImages.get(i).getName());
//            contenttypes.add(propImages.get(i).getType());
//            byte[] image = ImageUtility.decompressImage(propImages.get(i).getImage());
//            i++;
//        }
////        List<String> contenttypes = Arrays.asList(new String[]{MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_JPEG_VALUE});
////        List<byte[]> imagesdata = new ArrayList<>();
////        imagesdata.add(ArrayUtils.toObject(IOUtils.toByteArray(getClass().getResourceAsStream("/images/1.jpg"))));
////        imagesdata.add(ArrayUtils.toObject(IOUtils.toByteArray(getClass().getResourceAsStream("/images/2.jpg"))));
//
//        byte[] htmldata = getHtmlData(imagenames, contenttypes, imagesdata);
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.TEXT_HTML);
//        return new ResponseEntity<>(htmldata, headers, HttpStatus.OK);
//    }
//
//
//    private static byte[] getHtmlData(List<String> imagenames, List<String> contenttypes, List<byte[]> imagesdata){
//        String htmlcontent="<!doctype html><html><head><title>images</title></head><body>";
//        Integer imagescounter = -1;
//        for(String imagename : imagenames){
//            imagescounter++;
//            htmlcontent = htmlcontent + "<br/><br/><b>" + imagename + "</b><br/></br/><img src='data:" + contenttypes.get(imagescounter) + ";base64, " + org.apache.commons.binary.stringutils.newstringutf8(Base64.encodeBase64(ArrayUtils.toprimitive(imagesdata.get(imagescounter)))) + "'/>";
//        }
//        htmlcontent = htmlcontent + "</body></html>";
//        return htmlcontent.getBytes();
//    }


    @GetMapping(value = "/zip-download", produces="application/zip")
    public void zipDownload(@RequestParam Long propertyId, HttpServletResponse response) throws IOException {
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        List<PropertyImages> propImages = propertyImages.getAllPropImages(propertyId);
        for (PropertyImages propImage : propImages) {
            FileSystemResource resource = new FileSystemResource(String.valueOf(propImage.getImage()));
            ZipEntry zipEntry = new ZipEntry(Objects.requireNonNull(resource.getFilename()));
            zipEntry.setSize(resource.contentLength());
            zipOut.putNextEntry(zipEntry);
            StreamUtils.copy(resource.getInputStream(), zipOut);
            zipOut.closeEntry();
        }
        zipOut.finish();
        zipOut.close();
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""  + "\"");
    }

}





        //
//        if (!propImages.isEmpty()) {
//            for (PropertyImages propertyImage : propImages) {
//                byte[] imageBytes = ImageUtility.decompressImage(propertyImage.getImage());
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.valueOf(propertyImage.getType()));
////                propImageBytes.add(imageBytes);
//                return new ResponseEntity<>(imageBytes,headers, HttpStatus.OK);
//         }
//            return new ResponseEntity<>( HttpStatus.OK);
//
//        }
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
    // Other methods...



