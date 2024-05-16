package com.biel.bossaboxchallenge.controller;

import com.biel.bossaboxchallenge.domain.dtos.ToolDTO;
import com.biel.bossaboxchallenge.domain.entity.Tools;
import com.biel.bossaboxchallenge.service.ToolsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolsController {

    private final ToolsService toolsService;

    public ToolsController(ToolsService toolsService) {
        this.toolsService = toolsService;
    }

    @GetMapping
    public ResponseEntity<List<Tools>> findAll() throws Exception {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(toolsService.findAll());
    }

    @PostMapping
    public ResponseEntity<ToolDTO> save(@RequestBody ToolDTO tools) {
        return ResponseEntity.status(HttpStatus.CREATED).body(toolsService.save(tools));
    }

    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Tools>> findByTag(@PathVariable String tag){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(toolsService.findByTag(tag));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        toolsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tools> editTool(@RequestBody ToolDTO toolDTO, @PathVariable Long id) {
        Tools tools = toolsService.editTool(toolDTO, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(tools);
    }
}
