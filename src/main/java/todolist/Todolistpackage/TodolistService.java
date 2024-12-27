package todolist.Todolistpackage;

import todolist.Todolistgrouppackage.TodolistGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.Todolistgrouppackage.TodolistGroupService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodolistService {

    @Autowired
    private TodolistRepository repository;
    @Autowired
    private TodolistGroupService groupService;

    public TodolistEntity createTodo(TodolistRequestDTO requestDTO) {
        TodolistGroupEntity group = groupService.findListGroupById(requestDTO.getGroupId());
        TodolistEntity todoEntity = new TodolistEntity(
                requestDTO.getTitle(),
                requestDTO.getDescription(),
                requestDTO.getCompleted(),
                group
        );
        return repository.save(todoEntity);
    }
    public TodolistEntity updateTodo(Long id, Boolean completed) {
        TodolistEntity todo = findTodoById(id);
        if (completed != null) {
            todo.setCompleted(completed);
            return repository.save(todo);
        } else {
            throw new IllegalArgumentException("completed 필드는 필수입니다.");
        }
    }

    public void deleteTodoById(Long id) {
        repository.deleteById(id);
    }

    public List<TodolistResponseDTO> getAllTodos() {
        List<TodolistEntity> allTodos = repository.findAll();
        return allTodos.stream()
                .map(TodolistEntity::toResponseDTO)  // 변환 로직을 여기서 수행
                .collect(Collectors.toList());
    }

    public List<TodolistResponseDTO> searchTodosByTitle(String title) {
        List<TodolistEntity> searchResults = repository.findByTitleContaining(title);
        return searchResults.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TodolistEntity findTodoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo item not found with ID " + id));
    }

    public List<TodolistResponseDTO> getAllTodosByGroupId(Long groupId) {
        List<TodolistEntity> allTodos = repository.findAllByGroupId(groupId);
        return allTodos.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TodolistResponseDTO> getTodosByCompleted(boolean completed) {
        List<TodolistEntity> todos = repository.findByCompleted(completed);
        return todos.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TodolistResponseDTO> getTodosCreatedToday() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
        List<TodolistEntity> todos = repository.findTodosCreatedToday(startOfDay, endOfDay);
        return todos.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }
}