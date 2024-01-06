package org.zerock.mallapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // 현재 페이지 번호
    @Builder.Default
    private int page = 1;

    // 화면상에 들어가는 페이지 갯수
    @Builder.Default
    private int size = 10;
}
