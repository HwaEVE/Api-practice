package todolist.Todolistpackage;

import jakarta.persistence.EntityNotFoundException;
import todolist.Todolistgrouppackage.TodolistGroupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.Todolistgrouppackage.TodolistGroupRepository;
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
    @Autowired
    private TodolistRepository todolistRepository;
    @Autowired
    private TodolistGroupRepository groupRepository;

    // 1. 할 일 생성 기능
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

    // 2. 할 일 수정 기능
    public TodolistEntity updateTodo(Long id, Boolean completed) {
        TodolistEntity todo = findTodoById(id);
        if (completed != null) {
            todo.setCompleted(completed);
            return repository.save(todo);
        } else {
            throw new IllegalArgumentException("completed 필드는 필수입니다.");
        }
    }

    // 3. 할 일 삭제 기능
    public void deleteTodoById(Long id) {
        repository.deleteById(id);
    }

    // 4. 전체 할 일 목록 조회 기능
    public List<TodolistResponseDTO> getAllTodos() {
        List<TodolistEntity> allTodos = repository.findAll();
        return allTodos.stream()
                .map(TodolistEntity::toResponseDTO)  // 변환 로직을 여기서 수행
                .collect(Collectors.toList());
    }

    // 5. 특정 리스트에 속한 할 일 목록 조회 기능
    public List<TodolistResponseDTO> getTodosByListId(Long listId) {
        List<TodolistEntity> todos = todolistRepository.findByListId(listId); // listId로 할 일 조회
        return todos.stream()
                .map(TodolistEntity::toResponseDTO) // 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }

    // 6. 리스트 ID로 필터링된 할 일 목록 조회 또는 전체 할 일 목록 조회 기능
    public List<TodolistResponseDTO> getTodosByListIdOrAll(Long listId) {
        if (listId != null) {
            return getTodosByListId(listId); // listId로 필터링된 할 일 목록 조회
        } else {
            return getAllTodos(); // listId가 null이면 모든 할 일 목록 조회
        }
    }

    // 7. 제목으로 할 일 목록 검색 기능
    public List<TodolistResponseDTO> searchTodosByTitle(String title) {
        List<TodolistEntity> searchResults = repository.findByTitleContaining(title);
        return searchResults.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 8. 할 일 ID로 특정 할 일 조회 기능
    public TodolistEntity findTodoById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo item not found with ID " + id));
    }

    // 9. 특정 그룹에 속한 할 일 목록 조회 기능
    public List<TodolistResponseDTO> getAllTodosByGroupId(Long groupId) {
        List<TodolistEntity> allTodos = repository.findAllByGroupId(groupId);
        return allTodos.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 10. 완료된 할 일 목록 조회 기능
    public List<TodolistResponseDTO> getTodosByCompleted(boolean completed) {
        List<TodolistEntity> todos = repository.findByCompleted(completed);
        return todos.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 11. 오늘 생성된 할 일 목록 조회 기능
    public List<TodolistResponseDTO> getTodosCreatedToday() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.MAX);
        List<TodolistEntity> todos = repository.findTodosCreatedToday(startOfDay, endOfDay);
        return todos.stream()
                .map(TodolistEntity::toResponseDTO)
                .collect(Collectors.toList());
    }

    // 12. 할 일의 그룹 변경 기능
    public TodolistEntity updateListOfTodo(Long todoId, Long newListId) {
        TodolistEntity todo = todolistRepository.findById(todoId)
                .orElseThrow(() -> new EntityNotFoundException("Todo not found"));
        TodolistGroupEntity newGroup = groupRepository.findById(newListId)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
        todo.setGroup(newGroup);
        return todolistRepository.save(todo);
    }
}