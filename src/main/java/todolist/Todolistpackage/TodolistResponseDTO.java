package todolist.Todolistpackage;

import java.util.Objects;

public class TodolistResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private String createdAt;
    private String updatedAt;

    public TodolistResponseDTO() {}

    public TodolistResponseDTO(Long id, String title, String description, Boolean completed, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public String getDescription() {
        return description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistResponseDTO that = (TodolistResponseDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(completed, that.completed) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, completed, createdAt, updatedAt);
    }
}