package To_do_List_Package;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class To_Do_List_RequestDTO {

    @NotNull(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Completion status is required")
    private Boolean completed;

    public To_Do_List_RequestDTO(String title, String description, Boolean completed) {
        this.title = title;
        this.description = description;
        this.completed = completed;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull(message = "Completion status is required") Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(@NotNull(message = "Completion status is required") Boolean completed) {
        this.completed = completed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        To_Do_List_RequestDTO that = (To_Do_List_RequestDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(completed, that.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, completed);
    }
}