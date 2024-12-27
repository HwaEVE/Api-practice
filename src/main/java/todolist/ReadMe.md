## Todo List API - 포스트맨 테스트 가이드

이 문서는 Todo List API를 포스트맨(Postman)을 사용하여 테스트할 수 있는 방법을 안내합니다. 각 API 엔드포인트는 **그룹**과 **리스트**와 관련된 기능을 제공합니다.

### **1. 그룹 생성하기 (POST /todos/groups)**

새로운 그룹을 생성합니다.

- **메서드**: `POST`
- **URL**: `http://localhost:8080/todos/groups`
- **헤더**:
    - `Content-Type`: `application/json`
- **본문 (Body)**:
    ```json
    {
      "name": "집안일"
    }
    ```

#### 응답 예시:
```json
{
  "id": 1,
  "name": "집안일"
}
```

---

### **2. 특정 그룹에 속한 할 일 목록 조회 (GET /todos/groups/{groupId})**

특정 그룹에 속한 할 일 목록을 조회합니다.

- **메서드**: `GET`
- **URL**: `http://localhost:8080/todos/groups/{groupId}`
    - `{groupId}`: 조회하고자 하는 그룹의 ID (예: `1`)

#### 예시:
```http
GET http://localhost:8080/todos/groups/1
```

#### 응답 예시:
```json
[
  {
    "id": 1,
    "content": "빨래하기"
  },
  {
    "id": 2,
    "content": "설거지하기"
  }
]
```

---

### **3. 그룹 변경 (PUT /todos/{id}/group/{newGroupId})**

특정 할 일을 다른 그룹으로 변경합니다.

- **메서드**: `PUT`
- **URL**: `http://localhost:8080/todos/{id}/group/{newGroupId}`
    - `{id}`: 변경할 할 일의 ID (예: `1`)
    - `{newGroupId}`: 변경할 새로운 그룹의 ID (예: `2`)

#### 예시:
```http
PUT http://localhost:8080/todos/1/group/2
```

#### 응답 예시:
```json
{
  "id": 1,
  "content": "빨래하기",
  "group": {
    "id": 2,
    "name": "집안일"
  }
}
```

---

### **4. 리스트 ID로 할 일 목록 조회 (GET /todos?listId={listId})**

특정 리스트에 속한 할 일 목록을 조회합니다.

- **메서드**: `GET`
- **URL**: `http://localhost:8080/todos?listId={listId}`
    - `{listId}`: 조회하고자 하는 리스트의 ID (예: `1`)

#### 예시:
```http
GET http://localhost:8080/todos?listId=1
```

#### 응답 예시:
```json
[
  {
    "id": 1,
    "content": "빨래하기"
  },
  {
    "id": 3,
    "content": "청소기 돌리기"
  }
]
```

---

### **5. 할 일 생성하기 (POST /todos)**

새로운 할 일을 생성합니다. 이때, 할 일이 속할 **그룹**을 지정합니다.

- **메서드**: `POST`
- **URL**: `http://localhost:8080/todos`
- **헤더**: `Content-Type: application/json`
- **본문 (Body)**:
    ```json
    {
      "title": "청소기 돌리기",
      "description": "집안 청소를 위한 청소기 돌리기",
      "completed": false,
      "groupId": 1
    }
    ```

#### 응답 예시:
```json
{
  "id": 4,
  "content": "청소기 돌리기",
  "completed": false,
  "group": {
    "id": 1,
    "name": "집안일"
  }
}
```

---

### **6. 할 일 업데이트하기 (PUT /todos/{id})**

특정 할 일의 상태를 업데이트합니다.

- **메서드**: `PUT`
- **URL**: `http://localhost:8080/todos/{id}`
    - `{id}`: 업데이트할 할 일의 ID (예: `1`)
- **본문 (Body)**:
    ```json
    {
      "completed": true
    }
    ```

#### 예시:
```http
PUT http://localhost:8080/todos/1
```

#### 응답 예시:
```json
{
  "id": 1,
  "content": "빨래하기",
  "completed": true,
  "group": {
    "id": 1,
    "name": "집안일"
  }
}
```

---

### **7. 할 일 삭제하기 (DELETE /todos/{id})**

특정 할 일을 삭제합니다.

- **메서드**: `DELETE`
- **URL**: `http://localhost:8080/todos/{id}`
    - `{id}`: 삭제할 할 일의 ID (예: `1`)

#### 예시:
```http
DELETE http://localhost:8080/todos/1
```

#### 응답 예시:
```http
HTTP/1.1 204 No Content
```

---

### **8. 완료된 할 일 목록 조회 (GET /todos/completed)**

완료된 할 일 목록을 조회합니다.

- **메서드**: `GET`
- **URL**: `http://localhost:8080/todos/completed`
    - `completed`: 완료 여부 (`true` 또는 `false`)

#### 예시:
```http
GET http://localhost:8080/todos/completed?completed=true
```

#### 응답 예시:
```json
[
  {
    "id": 1,
    "content": "빨래하기",
    "completed": true
  }
]
```

---

### **9. 오늘 생성된 할 일 목록 조회 (GET /todos/today)**

오늘 생성된 할 일 목록을 조회합니다.

- **메서드**: `GET`
- **URL**: `http://localhost:8080/todos/today`

#### 예시:
```http
GET http://localhost:8080/todos/today
```

#### 응답 예시:
```json
[
  {
    "id": 1,
    "content": "빨래하기",
    "completed": false
  }
]
```

---
