package To_do_List_Package;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface To_Do_List_Repository extends JpaRepository<To_Do_List_Entity, Long> {

    Optional<To_Do_List_Entity> findById(Long id);

    Optional<To_Do_List_Entity> findByTitle(String title);
}