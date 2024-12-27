package todolist.Todolistpackage;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TodolistController {

    @Autowired
    private TodolistService todolistService;

    @PostMapping("/todos")
    public TodolistResponseDTO createTodo(@Valid @RequestBody TodolistRequestDTO requestDTO) {
        TodolistEntity createdTodo = todolistService.createTodo(requestDTO);
        return createdTodo.toResponseDTO();
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodolistResponseDTO>> getAllTodos() {
        List<TodolistResponseDTO> responseList = todolistService.getAllTodos();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/todos/groups/{groupId}")
    public ResponseEntity<List<TodolistResponseDTO>> getAllTodosByGroupId(@PathVariable Long groupId) {
        List<TodolistResponseDTO> responseList = todolistService.getAllTodosByGroupId(groupId);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/todos/search")
    public ResponseEntity<List<TodolistResponseDTO>> searchTodos(@RequestParam String title) {
        List<TodolistResponseDTO> responseList = todolistService.searchTodosByTitle(title);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/todos/completed")
    public ResponseEntity<List<TodolistResponseDTO>> getTodosByCompleted(@RequestParam boolean completed) {
        List<TodolistResponseDTO> responseList = todolistService.getTodosByCompleted(completed);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/todos/today")
    public ResponseEntity<List<TodolistResponseDTO>> getTodosCreatedToday() {
        List<TodolistResponseDTO> responseList = todolistService.getTodosCreatedToday();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public TodolistResponseDTO updateTodo(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> requestBody) {
        Boolean completed = requestBody.get("completed");
        TodolistEntity updatedTodo = todolistService.updateTodo(id, completed);
        return updatedTodo.toResponseDTO();
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        todolistService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 할 일이 속한 리스트를 다른 리스트로 변경
    @PutMapping("/todos/{id}/group/{newGroupId}")
    public ResponseEntity<TodolistEntity> changeTodoGroup(
            @PathVariable Long id,
            @PathVariable Long newGroupId) {
        TodolistEntity updatedTodo = todolistService.updateListOfTodo(id, newGroupId);
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
}