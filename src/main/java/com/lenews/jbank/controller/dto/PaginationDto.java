package com.lenews.jbank.controller.dto;

public record PaginationDto(Integer page,
                            Integer size,
                            Long totalElements,
                            Integer totalPages) {
}
