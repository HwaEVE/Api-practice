package Todolistgrouppackage;

import Todolistpackage.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodolistGroupService {

    @Autowired
    private TodolistGroupRepository groupRepository;

    public TodolistGroupEntity createListGroup(TodolistGroupEntity group) {
        return groupRepository.save(group);
    }

    public TodolistGroupEntity findListGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo group not found with ID " + id));
    }

    public List<TodolistGroupEntity> searchGroupsByName(String name) {
        return groupRepository.findByNameContaining(name);
    }
    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }
    public List<TodolistGroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }
    public TodolistGroupEntity updateGroup(TodolistGroupEntity group){
        return groupRepository.save(group);
    }
}