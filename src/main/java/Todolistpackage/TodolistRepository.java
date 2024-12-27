package Todolistpackage;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TodolistRepository extends JpaRepository<TodolistEntity, Long> {
    List<TodolistEntity> findByTitleContaining(String title);
    List<TodolistEntity> findAllByGroupId(Long groupId);
}