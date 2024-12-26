package To_do_List_Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class To_Do_List_Service {

    @Autowired
    private To_Do_List_Repository repository;

    public Optional<To_Do_List_Entity> getTodoById(Long id) {
        return repository.findById(id);
    }

    public To_Do_List_Entity createTodo(To_Do_List_Entity todo) {
        return repository.save(todo);
    }

    public void deleteTodoById(Long id) {
        repository.deleteById(id);
    }
}