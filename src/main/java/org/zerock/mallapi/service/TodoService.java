package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

public interface TodoService {
    // 등록
    Long register(TodoDTO todoDTO);

    // 조회
    TodoDTO get(Long tno);

    // 수정
    void modify(TodoDTO todoDTO);

    // 삭제
    void remove(Long tno);

    // 페이지 조회
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
