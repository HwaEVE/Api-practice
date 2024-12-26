package BoradPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class BoardRestController {

    @Autowired
    private BoardRepository boardRepository;

    // 모든 게시판 목록 조회
    @GetMapping("/boards")
    public List<BoardResponseDTO> getAllBoards() {
        List<BoardEntity> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> new BoardResponseDTO(board.getId(), board.getName()))
                .toList();
    }

    // 특정 게시판 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDTO> getBoardById(@PathVariable String id) {
        Optional<BoardEntity> board = boardRepository.findById(id);
        return board.map(boardEntity ->
                        ResponseEntity.ok(new BoardResponseDTO(boardEntity.getId(), boardEntity.getName())))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // 게시판 생성
    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDTO> createBoard(@Valid @RequestBody BoardRequestDTO requestDTO, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 유효성 검사 실패시 400 오류
        }

        try {
            // BoardEntity 생성 (UUID는 기본 생성자에서 자동으로 생성됨)
            BoardEntity newBoard = new BoardEntity(requestDTO.getName());
            boardRepository.save(newBoard);  // DB에 저장

            // BoardResponseDTO 반환
            BoardResponseDTO responseDTO = new BoardResponseDTO(newBoard.getId(), newBoard.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 게시판 수정
    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponseDTO> updateBoard(@PathVariable String id, @RequestBody BoardRequestDTO requestDTO) {
        Optional<BoardEntity> existingBoard = boardRepository.findById(id);
        if (existingBoard.isPresent()) {
            BoardEntity board = existingBoard.get();
            board.setName(requestDTO.getName());
            boardRepository.save(board);

            return ResponseEntity.ok(new BoardResponseDTO(board.getId(), board.getName()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 게시판 삭제
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable String id) {
        Optional<BoardEntity> board = boardRepository.findById(id);
        if (board.isPresent()) {
            boardRepository.delete(board.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}