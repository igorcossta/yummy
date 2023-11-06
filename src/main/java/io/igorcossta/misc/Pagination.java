package io.igorcossta.misc;

import java.util.List;

public record Pagination<T>(List<T> items, int currentPage, long totalItems, int totalPages, int pageSize) {
}
