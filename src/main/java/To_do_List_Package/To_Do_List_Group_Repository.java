package To_do_List_Package;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface To_Do_List_Group_Repository extends JpaRepository<To_Do_List_Group_Entity, Long> {
    List<To_Do_List_Group_Entity> findByNameContaining(String name);
}