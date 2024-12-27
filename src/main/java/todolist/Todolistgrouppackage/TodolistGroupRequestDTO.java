package todolist.Todolistgrouppackage;

import jakarta.validation.constraints.NotNull;
import java.util.Objects;

public class TodolistGroupRequestDTO {

    @NotNull(message = "Group name is required")
    private String name;

    public TodolistGroupRequestDTO() {}

    public TodolistGroupRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodolistGroupRequestDTO that = (TodolistGroupRequestDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}