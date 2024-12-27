package Todolistpackage;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Todolistgrouppackage.TodolistGroupEntity;
import Todolistgrouppackage.TodolistGroupService;
import java.util.List;
import java.util.Map;

@RestController
public class TodolistController {

    @Autowired
    private TodolistService todolistService;
    @Autowired
    private TodolistGroupService groupService;
    @PostMapping("/todos")
    public TodolistResponseDTO createTodo(@Valid @RequestBody TodolistRequestDTO requestDTO) {
        TodolistGroupEntity group = groupService.findListGroupById(requestDTO.getGroupId());
        TodolistEntity todoEntity = new TodolistEntity(
                requestDTO.getTitle(),
                requestDTO.getDescription(),
                requestDTO.getCompleted(),
                group
        );
        TodolistEntity createdTodo = todolistService.createTodo(todoEntity);
        return createdTodo.toResponseDTO();
    }

    // 새로운 엔드포인트 추가
    @GetMapping("/todos")
    public ResponseEntity<List<TodolistResponseDTO>> getAllTodos() {
        List<TodolistEntity> allTodos = todolistService.getAllTodos();
        List<TodolistResponseDTO> responseList = allTodos.stream()
                .map(TodolistEntity::toResponseDTO)
                .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/todos/groups/{groupId}")
    public ResponseEntity<List<TodolistResponseDTO>> getAllTodosByGroupId(@PathVariable Long groupId) {
        List<TodolistEntity> allTodos = todolistService.getAllTodosByGroupId(groupId);
        List<TodolistResponseDTO> responseList = allTodos.stream()
                .map(TodolistEntity::toResponseDTO)
                .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    // 할 일 검색을 위한 GET 엔드포인트 추가
    @GetMapping("/todos/search")
    public ResponseEntity<List<TodolistResponseDTO>> searchTodos(@RequestParam String title) {
        List<TodolistEntity> searchResults = todolistService.searchTodosByTitle(title);
        List<TodolistResponseDTO> responseList = searchResults.stream()
                .map(TodolistEntity::toResponseDTO)
                .toList();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @PutMapping("/todos/{id}")
    public TodolistResponseDTO updateTodo(@PathVariable Long id, @RequestBody Map<String, Boolean> requestBody) {
        TodolistEntity todo = todolistService.findTodoById(id);
        Boolean completed = requestBody.get("completed");
        if (completed != null) {
            todo.setCompleted(completed);
            TodolistEntity updatedTodo = todolistService.updateTodo(todo);
            return updatedTodo.toResponseDTO();
        } else {
            throw new IllegalArgumentException("completed 필드는 필수입니다.");
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Long id) {
        todolistService.deleteTodoById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}