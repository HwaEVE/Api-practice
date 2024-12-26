package BoradPackage;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BoardEntity {

    @Id
    private String id;  // UUID를 사용하여 ID를 수동으로 생성

    private String name;

    // 기본 생성자 추가
    public BoardEntity() {
        this.id = UUID.randomUUID().toString();  // UUID로 자동 ID 생성
    }

    // 이름을 전달받는 생성자 (명시적으로 id가 없으면 UUID로 생성)
    public BoardEntity(String name) {
        this.id = UUID.randomUUID().toString();  // UUID로 자동 ID 생성
        this.name = name;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        BoardEntity that = (BoardEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}