package todolist.Todolistgrouppackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodolistGroupController {

    @Autowired
    private TodolistGroupService groupService;

    @PostMapping("/groups")
    public ResponseEntity<TodolistGroupEntity> createGroup(@RequestBody TodolistGroupEntity group) {
        TodolistGroupEntity createdGroup = groupService.createListGroup(group);
        return new ResponseEntity<>(createdGroup, HttpStatus.CREATED);
    }
    @GetMapping("/groups/{id}")
    public ResponseEntity<TodolistGroupEntity> getGroupById(@PathVariable Long id) {
        TodolistGroupEntity group = groupService.findListGroupById(id);
        return new ResponseEntity<>(group, HttpStatus.OK);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<TodolistGroupEntity>> getAllGroups() {
        List<TodolistGroupEntity> groupList = groupService.getAllGroups();
        return new ResponseEntity<>(groupList, HttpStatus.OK);
    }

    @GetMapping("/groups/search")
    public ResponseEntity<List<TodolistGroupEntity>> searchGroups(@RequestParam String name) {
        List<TodolistGroupEntity> searchResults = groupService.searchGroupsByName(name);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<TodolistGroupEntity> updateGroup(@PathVariable Long id, @RequestBody TodolistGroupEntity group) {
        TodolistGroupEntity updatedGroup = groupService.updateGroupById(id, group);
        return new ResponseEntity<>(updatedGroup, HttpStatus.OK);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long id) {
        groupService.deleteGroupById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}