package To_do_List_Package;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class To_Do_List_Controller {

    @Autowired
    private To_Do_List_Service toDoListService;

    @PostMapping("/todos")
    public To_Do_List_ResponseDTO createTodo(@Valid @RequestBody To_Do_List_RequestDTO requestDTO) {
        To_Do_List_Entity todoEntity = new To_Do_List_Entity(
                requestDTO.getTitle(),
                requestDTO.getDescription(),
                requestDTO.getCompleted()
        );
        To_Do_List_Entity createdTodo = toDoListService.createTodo(todoEntity);
        return createdTodo.toResponseDTO();
    }

    @GetMapping("/todos/{id}")
    public To_Do_List_ResponseDTO getTodoById(@PathVariable Long id) {
        To_Do_List_Entity entity = toDoListService.findTodoById(id);
        return entity.toResponseDTO();
    }

    @GetMapping("/todos")
    public ResponseEntity<List<To_Do_List_ResponseDTO>> getAllTodos() {
        List<To_Do_List_Entity> allTodos = toDoListService.getAllTodos();
        List<To_Do_List_ResponseDTO> responseList = allTodos.stream()
                .map(To_Do_List_Entity::toResponseDTO)
                .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
    // 할 일 검색을 위한 GET 엔드포인트 추가
    @GetMapping("/todos/search")
    public ResponseEntity<List<To_Do_List_ResponseDTO>> searchTodos(@RequestParam String title) {
        List<To_Do_List_Entity> searchResults = toDoListService.searchTodosByTitle(title);
        List<To_Do_List_ResponseDTO> responseList = searchResults.stream()
                .map(To_Do_List_Entity::toResponseDTO)
                .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public To_Do_List_ResponseDTO updateTodo(@PathVariable Long id, @RequestBody Map<String, Boolean> requestBody) {
        To_Do_List_Entity todo = toDoListService.findTodoById(id);
        Boolean completed = requestBody.get("completed");
        if (completed != null) {
            todo.setCompleted(completed);
            To_Do_List_Entity updatedTodo = toDoListService.updateTodo(todo);
            return updatedTodo.toResponseDTO();
        } else {
            throw new IllegalArgumentException("completed 필드는 필수입니다.");
        }
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodoById(@PathVariable Long id) {
        toDoListService.deleteTodoById(id);
    }
}