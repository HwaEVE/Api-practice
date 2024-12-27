package Todolistpackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Todolistgrouppackage.TodolistGroupService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class TodolistService {

    @Autowired
    private TodolistRepository repository;
    @Autowired
    private TodolistGroupService groupService;

    public TodolistEntity createTodo(TodolistEntity todo) {
        return repository.save(todo);
    }

    public TodolistEntity updateTodo(TodolistEntity todo) {
        return repository.save(todo);
    }

    public void deleteTodoById(Long id) {
        repository.deleteById(id);
    }

    public List<TodolistEntity> getAllTodos() {
        return repository.findAll();
    }

    public List<TodolistEntity> searchTodosByTitle(String title) {
        return repository.findByTitleContaining(title);
    }
    public TodolistEntity findTodoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo item not found with ID " + id));
    }
    public List<TodolistEntity> getAllTodosByGroupId(Long groupId) {
        return repository.findAllByGroupId(groupId);
    }

    public List<TodolistEntity> getTodosByCompleted(boolean completed) {
        return repository.findByCompleted(completed);
    }

    public List<TodolistEntity> getTodosCreatedToday() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
        return repository.findTodosCreatedToday(startOfDay, endOfDay);
    }
}