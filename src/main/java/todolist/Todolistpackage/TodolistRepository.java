package todolist.Todolistpackage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TodolistRepository extends JpaRepository<TodolistEntity, Long> {
    List<TodolistEntity> findByTitleContaining(String title);
    List<TodolistEntity> findAllByGroupId(Long groupId);
    List<TodolistEntity> findByCompleted(boolean completed);

    @Query("SELECT t FROM TodolistEntity t WHERE t.createdAt >= :startOfDay AND t.createdAt < :endOfDay")
    List<TodolistEntity> findTodosCreatedToday(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

}