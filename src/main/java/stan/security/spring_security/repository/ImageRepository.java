package stan.security.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.security.spring_security.models.ImageModel;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
//    Optional<ImageModel> findByName(String name);
    Optional<ImageModel> findImageById(Long imageId);
    //void save(ImageModel img, Long userId);
}
