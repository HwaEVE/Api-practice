package To_do_List_Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class To_Do_List_Service {

    @Autowired
    private To_Do_List_Repository repository;

    public To_Do_List_Entity findTodoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo item not found with ID " + id));
    }

    public To_Do_List_Entity createTodo(To_Do_List_Entity todo) {
        return repository.save(todo);
    }

    public To_Do_List_Entity updateTodo(To_Do_List_Entity todo) {
        return repository.save(todo);
    }

    public void deleteTodoById(Long id) {
        repository.deleteById(id);
    }

    public List<To_Do_List_Entity> getAllTodos() {
        return repository.findAll();
    }
    // 검색 기능 추가
    public List<To_Do_List_Entity> searchTodosByTitle(String title) {
        return repository.findByTitleContaining(title);
    }
}