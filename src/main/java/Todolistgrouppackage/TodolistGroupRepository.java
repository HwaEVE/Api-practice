package Todolistgrouppackage;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodolistGroupRepository extends JpaRepository<TodolistGroupEntity, Long> {
    List<TodolistGroupEntity> findByNameContaining(String name);
}