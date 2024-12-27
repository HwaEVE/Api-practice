package todolist.Todolistgrouppackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todolist.Todolistpackage.ResourceNotFoundException;

import java.util.List;

@Service
public class TodolistGroupService {

    @Autowired
    private TodolistGroupRepository groupRepository;

    public TodolistGroupEntity createListGroup(TodolistGroupRequestDTO requestDTO) {
        TodolistGroupEntity newGroup = new TodolistGroupEntity(requestDTO.getName());
        return groupRepository.save(newGroup);
    }

    public TodolistGroupEntity findListGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo group not found with ID " + id));
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

    public TodolistGroupEntity updateGroupById(Long id, TodolistGroupRequestDTO requestDTO) {
        TodolistGroupEntity existingGroup = findListGroupById(id);
        existingGroup.setName(requestDTO.getName());
        return groupRepository.save(existingGroup);
    }
}