package todolist.Todolistpackage;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;
public class TodolistRequestDTO {

    @NotNull(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Completion status is required")
    private Boolean completed;

    private Long groupId;

    public TodolistRequestDTO(String title, String description, Boolean completed, Long groupId) {
        this.title = title;
        this.description = description;
        this.completed = completed;
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public @NotNull(message = "Title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "Title is required") String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public @NotNull(message = "Completion status is required") Boolean getCompleted() {
        return completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistRequestDTO that = (TodolistRequestDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(completed, that.completed) && Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, completed, groupId);
    }
}