package BoradPackage;

import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class BoardRequestDTO {

    @NotNull
    private String name;


    public BoardRequestDTO() {
    }

    public BoardRequestDTO(String name) {
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
        BoardRequestDTO that = (BoardRequestDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}