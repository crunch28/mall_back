package org.zerock.mallapi.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {
    // 조회한 내용물
    private List<E> dtoList;
    // 페이지 번호 리스트
    private List<Integer> pageNumList;
    // 현재 페이지, 사이즈
    private PageRequestDTO pageRequestDTO;
    // 이전, 다음
    private boolean prev, next;
    // 내용물 총 갯수, 이전 페이지, 다음 페이지, 총 페이지 갯수, 현재 페이지
    private int totalCount, prevPage, nextPage, totalPage, current;

    // 생성자임 Builder 사용해서 구성
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {
        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int) totalCount;

        // 화면 상에 나타나는 페이지 번호의 끝 번호 10, 20 ,30 ...
        int end = (int) (Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;
        // 화면 상에 나타나는 페이지 번호의 첫 번호 1, 11, 21 ...
        int start = end - 9;
        // 화면 상에 나타나는 페이지 번호의 끝 번호
        int last = (int) (Math.ceil(totalCount / (double) pageRequestDTO.getSize()));
        end = end > last ? last : end;
        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDTO.getSize();
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        if (prev)
            this.prevPage = start - 1;
        if (next)
            this.nextPage = end + 1;

        this.totalPage = this.pageNumList.size();
        this.current = pageRequestDTO.getPage();
    }
}
