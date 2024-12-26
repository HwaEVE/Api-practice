package To_do_List_Package;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class To_Do_List_Controller {

    @Autowired
    private To_Do_List_Service toDoListService;

    @PostMapping("/todos")
    public To_Do_List_ResponseDTO createTodo(@RequestBody To_Do_List_RequestDTO requestDTO) {
        To_Do_List_Entity todoEntity = new To_Do_List_Entity(
                requestDTO.getTitle(),
                requestDTO.getDescription(),
                requestDTO.getCompleted(),
                "2024-12-26T00:00:00",
                null
        );
        To_Do_List_Entity createdTodo = toDoListService.createTodo(todoEntity);
        return new To_Do_List_ResponseDTO(
                createdTodo.getId(),
                createdTodo.getTitle(),
                createdTodo.getDescription(),
                createdTodo.getCompleted(),
                createdTodo.getCreatedAt(),
                createdTodo.getUpdatedAt()
        );
    }

    @GetMapping("/todos/{id}")
    public To_Do_List_ResponseDTO getTodoById(@PathVariable Long id) {
        Optional<To_Do_List_Entity> todo = toDoListService.getTodoById(id);
        if (todo.isPresent()) {
            To_Do_List_Entity entity = todo.get();
            return new To_Do_List_ResponseDTO(
                    entity.getId(),
                    entity.getTitle(),
                    entity.getDescription(),
                    entity.getCompleted(),
                    entity.getCreatedAt(),
                    entity.getUpdatedAt()
            );
        } else {
            throw new ResourceNotFoundException("Todo item not found with ID " + id);
        }
    }

    @PutMapping("/todos/{id}")
    public To_Do_List_ResponseDTO updateTodo(@PathVariable Long id, @RequestBody To_Do_List_RequestDTO requestDTO) {
        Optional<To_Do_List_Entity> existingTodo = toDoListService.getTodoById(id);
        if (existingTodo.isPresent()) {
            To_Do_List_Entity todo = existingTodo.get();
            todo.setTitle(requestDTO.getTitle());
            todo.setDescription(requestDTO.getDescription());
            todo.setCompleted(requestDTO.getCompleted());
            todo.setUpdatedAt("2024-12-26T00:00:00");
            To_Do_List_Entity updatedTodo = toDoListService.createTodo(todo);
            return new To_Do_List_ResponseDTO(
                    updatedTodo.getId(),
                    updatedTodo.getTitle(),
                    updatedTodo.getDescription(),
                    updatedTodo.getCompleted(),
                    updatedTodo.getCreatedAt(),
                    updatedTodo.getUpdatedAt()
            );
        } else {
            throw new ResourceNotFoundException("Todo item not found with ID " + id);
        }
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodoById(@PathVariable Long id) {
        toDoListService.deleteTodoById(id);
    }
}