package unigap.api.dto.out;

import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public class PageDtoOut<T> implements Serializable {
    @Builder.Default
    private Integer page = 1;
    @Builder.Default
    private Integer pageSize = 10;
    @Builder.Default
    private Long totalElements = 0L;
    private Long totalPages; // Không sử dụng Builder.Default vì totalPages được tính toán

    @Builder.Default
    private List<T> data = new ArrayList<>();

    public static <T> PageDtoOut<T> from(Integer page, Integer pageSize, Long totalElements) {
        Long totalPages = totalElements / pageSize;
        if (totalElements % pageSize != 0) {
            totalPages++;
        }

        return PageDtoOut.<T>builder()
                .page(page)
                .pageSize(pageSize)
                .totalPages(totalPages)
                .totalElements(totalElements)
                .build();
    }
}
