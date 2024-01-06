package org.zerock.mallapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.service.TodoService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    // @Test
    public void testInsert() {
        for (int i = 1; i <= 100; i++) {
            Todo todo = Todo.builder()
                    .title("title.." + i)
                    .dueDate(LocalDate.of(2024, 12, 31))
                    .writer("user" + i)
                    .build();
            todoRepository.save(todo);
        }
    }

    // @Test
    public void testRead() {
        Long tno = 33L;
        java.util.Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info(todo);
    }

    // @Test
    public void testModify() {
        Long tno = 33L;
        Optional<Todo> result = todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        todo.changeTitle("Modified 33");
        todo.changeComplete(true);
        todo.changeDueDate(LocalDate.of(2024, 01, 01));
        todoRepository.save(todo);
    }

    // @Test
    public void testDelete() {
        Long tno = 1L;
        todoRepository.deleteById(tno);
    }

    // @Test
    public void testPaging() {
        Pageable Pageable = PageRequest.of(0, 10, Sort.by("tno").descending());
        Page<Todo> result = todoRepository.findAll(Pageable);
        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo -> log.info(todo));
    }

    // @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("테스트")
                .writer("tester")
                .duDate(LocalDate.of(2024, 01, 01))
                .build();

        Long tno = todoService.register(todoDTO);
        log.info(tno);
    }

    // @Test
    public void testGet() {
        Long tno = 101L;
        TodoDTO todoDTO = todoService.get(tno);
        log.info(todoDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(2)
                .size(10)
                .build();

        PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);
        log.info(response);
    }
}
