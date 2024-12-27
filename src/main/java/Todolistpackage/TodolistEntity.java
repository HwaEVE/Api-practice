package Todolistpackage;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import Todolistgrouppackage.TodolistGroupEntity;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class TodolistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Boolean completed;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private TodolistGroupEntity group;

    public TodolistEntity() {}

    public TodolistEntity(String title, String description, Boolean completed, TodolistGroupEntity group) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TodolistGroupEntity getGroup() {
        return group;
    }

    public void setGroup(TodolistGroupEntity group) {
        this.group = group;
    }

    public TodolistResponseDTO toResponseDTO() {
        return new TodolistResponseDTO(
                this.id,
                this.title,
                this.description,
                this.completed,
                this.createdAt.toString(),
                this.updatedAt != null ? this.updatedAt.toString() : null
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistEntity that = (TodolistEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(completed, that.completed) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, completed, createdAt, updatedAt, group);
    }
}