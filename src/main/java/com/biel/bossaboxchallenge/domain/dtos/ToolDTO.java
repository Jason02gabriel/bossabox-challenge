package com.biel.bossaboxchallenge.domain.dtos;

import java.util.List;

public record ToolDTO(String title, String link, String description, List<String> tags) {
}
