package To_do_List_Package;

import org.springframework.data.jpa.repository.JpaRepository;

public interface To_Do_List_Repository extends JpaRepository<To_Do_List_Entity, Long> {

}