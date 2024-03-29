package com.example.banking.features.file.dto;

import lombok.Builder;

@Builder
public record FileResponse(String filename, String fullUrl) {
}
