package stan.inc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.inc.models.image.TrialImage;

public interface ImagesRepository extends JpaRepository<TrialImage, Long> {
	TrialImage findByUserId(Long userId);

//	List<TrialImage> findByUserId(Long userId);
}
