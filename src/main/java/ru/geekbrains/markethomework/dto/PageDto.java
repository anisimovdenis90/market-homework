package ru.geekbrains.markethomework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@NoArgsConstructor
public class PageDto<T> {

    private List<T> content;
    private int totalPages;

    public PageDto(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
    }
}
