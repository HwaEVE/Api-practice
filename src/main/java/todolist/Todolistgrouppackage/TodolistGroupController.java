package todolist.Todolistgrouppackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TodolistGroupController {

    @Autowired
    private TodolistGroupService groupService;

    @PostMapping("/groups")
    public ResponseEntity<TodolistGroupResponseDTO> createGroup(@RequestBody TodolistGroupRequestDTO requestDTO) {
        TodolistGroupEntity createdGroup = groupService.createListGroup(requestDTO);
        TodolistGroupResponseDTO responseDTO = new TodolistGroupResponseDTO(
                createdGroup.getId(),
                createdGroup.getName(),
                createdGroup.getCreatedAt(),
                createdGroup.getUpdatedAt()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<TodolistGroupResponseDTO> getGroupById(@PathVariable Long id) {
        TodolistGroupEntity group = groupService.findListGroupById(id);
        TodolistGroupResponseDTO responseDTO = new TodolistGroupResponseDTO(
                group.getId(),
                group.getName(),
                group.getCreatedAt(),
                group.getUpdatedAt()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<TodolistGroupResponseDTO>> getAllGroups() {
        List<TodolistGroupEntity> groupList = groupService.getAllGroups();
        List<TodolistGroupResponseDTO> responseDTOList = groupList.stream()
                .map(group -> new TodolistGroupResponseDTO(
                        group.getId(),
                        group.getName(),
                        group.getCreatedAt(),
                        group.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

    @GetMapping("/groups/search")
    public ResponseEntity<List<TodolistGroupResponseDTO>> searchGroups(@RequestParam String name) {
        List<TodolistGroupEntity> searchResults = groupService.searchGroupsByName(name);
        List<TodolistGroupResponseDTO> responseDTOList = searchResults.stream()
                .map(group -> new TodolistGroupResponseDTO(
                        group.getId(),
                        group.getName(),
                        group.getCreatedAt(),
                        group.getUpdatedAt()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<TodolistGroupResponseDTO> updateGroup(@PathVariable Long id, @RequestBody TodolistGroupRequestDTO requestDTO) {
        TodolistGroupEntity updatedGroup = groupService.updateGroupById(id, requestDTO);
        TodolistGroupResponseDTO responseDTO = new TodolistGroupResponseDTO(
                updatedGroup.getId(),
                updatedGroup.getName(),
                updatedGroup.getCreatedAt(),
                updatedGroup.getUpdatedAt()
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long id) {
        groupService.deleteGroupById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}