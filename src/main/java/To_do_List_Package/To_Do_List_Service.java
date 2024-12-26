package To_do_List_Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class To_Do_List_Service {

    @Autowired
    private To_Do_List_Repository repository;

    @Autowired
    private To_Do_List_Group_Repository groupRepository;

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

    public List<To_Do_List_Entity> searchTodosByTitle(String title) {
        return repository.findByTitleContaining(title);
    }
    public To_Do_List_Group_Entity createListGroup(To_Do_List_Group_Entity group) {
        return groupRepository.save(group);
    }
    public To_Do_List_Group_Entity findListGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo group not found with ID " + id));
    }
    public List<To_Do_List_Entity> getAllTodosByGroupId(Long groupId) {
        return repository.findAllByGroupId(groupId);
    }
}