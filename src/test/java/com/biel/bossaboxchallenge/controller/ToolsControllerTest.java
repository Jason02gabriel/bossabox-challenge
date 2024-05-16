package com.biel.bossaboxchallenge.controller;

import com.biel.bossaboxchallenge.domain.dtos.ToolDTO;
import com.biel.bossaboxchallenge.domain.entity.Tools;
import com.biel.bossaboxchallenge.service.ToolsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ToolsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ToolsService toolsService;

    @InjectMocks
    private ToolsController toolsController;

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<Tools> toolsList = new ArrayList<>();
        when(toolsService.findAll()).thenReturn(toolsList);

        // Act & Assert
        mockMvc.perform(get("/tools"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testSave() throws Exception {
        // Arrange
        ToolDTO toolDTO = new ToolDTO("Title", "Link", "Description", Arrays.asList("tag1", "tag2"));
        when(toolsService.save(any(ToolDTO.class))).thenReturn(toolDTO);

        // Act & Assert
        mockMvc.perform(post("/tools")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Title\",\"link\":\"Link\",\"description\":\"Description\",\"tags\":[\"tag1\",\"tag2\"]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.link").value("Link"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.tags[0]").value("tag1"))
                .andExpect(jsonPath("$.tags[1]").value("tag2"));
    }

    @Test
    public void testFindByTag() throws Exception {
        // Arrange
        List<Tools> toolsList = new ArrayList<>();
        when(toolsService.findByTag("tag")).thenReturn(toolsList);

        // Act & Assert
        mockMvc.perform(get("/tools/tag/tag"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testDeleteById() throws Exception {
        // Arrange & Act & Assert
        mockMvc.perform(delete("/tools/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testEditTool() throws Exception {
        // Arrange
        ToolDTO toolDTO = new ToolDTO("Title", "Link", "Description", Arrays.asList("tag1", "tag2"));
        Tools tools = new Tools(toolDTO);
        when(toolsService.editTool(any(ToolDTO.class), any(Long.class))).thenReturn(tools);

        // Act & Assert
        mockMvc.perform(put("/tools/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Title\",\"link\":\"Link\",\"description\":\"Description\",\"tags\":[\"tag1\",\"tag2\"]}"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.title").value("Title"))
                .andExpect(jsonPath("$.link").value("Link"))
                .andExpect(jsonPath("$.description").value("Description"))
                .andExpect(jsonPath("$.tags[0]").value("tag1"))
                .andExpect(jsonPath("$.tags[1]").value("tag2"));
    }
}
