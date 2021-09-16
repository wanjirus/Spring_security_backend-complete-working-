package stan.security.spring_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import stan.security.spring_security.models.FileDB;

public interface FileDBRepository extends JpaRepository<FileDB, String> {
}
