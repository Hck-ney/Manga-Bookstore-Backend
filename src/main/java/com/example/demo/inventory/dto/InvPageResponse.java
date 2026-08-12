package com.example.demo.inventory.dto;

import java.util.List;

public record InvPageResponse(
        List<InvResponse> manga,
        int totalPages
) {
}
