package com.biel.bossaboxchallenge.service;

import com.biel.bossaboxchallenge.domain.dtos.ToolDTO;
import com.biel.bossaboxchallenge.domain.entity.Tools;
import com.biel.bossaboxchallenge.exception.NoRecordsFoundException;
import com.biel.bossaboxchallenge.repository.ToolsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ToolsServiceTest {

    @Mock
    private ToolsRepository toolsRepository;

    @InjectMocks
    private ToolsService toolsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<Tools> toolsList = new ArrayList<>();
        when(toolsRepository.findAll()).thenReturn(toolsList);

        // Act
        Exception exception = assertThrows(NoRecordsFoundException.class, () -> toolsService.findAll());

        // Assert
        assertEquals("Nenhum registro encontrado", exception.getMessage());
    }

    @Test
    public void testSave() {
        // Arrange
        ToolDTO toolDTO = new ToolDTO("Title", "Link", "Description", Arrays.asList("tag1", "tag2"));
        Tools tools = new Tools(toolDTO);
        when(toolsRepository.save(any(Tools.class))).thenReturn(tools);

        // Act
        ToolDTO savedToolDTO = toolsService.save(toolDTO);

        // Assert
        assertEquals(toolDTO, savedToolDTO);
    }

    @Test
    public void testFindByTag() {
        // Arrange
        List<Tools> toolsList = new ArrayList<>();
        ToolDTO toolDTO = new ToolDTO("Title", "Link", "Description", Arrays.asList("tag1", "tag2"));
        Tools tools = new Tools(toolDTO);
        toolsList.add(tools);
        when(toolsRepository.findAll()).thenReturn(toolsList);

        // Act
        List<Tools> foundTools = toolsService.findByTag("tag1");

        // Assert
        assertFalse(foundTools.isEmpty());
        assertEquals(1, foundTools.size());
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Long id = 1L;
        when(toolsRepository.existsById(id)).thenReturn(true);

        // Act
        toolsService.deleteById(id);

        // Assert
        verify(toolsRepository, times(1)).deleteById(id);
    }

    @Test
    public void testEditTool() {
        // Arrange
        Long id = 1L;
        ToolDTO toolDTO = new ToolDTO("Title", "Link", "Description", Arrays.asList("tag1", "tag2"));
        Tools existingTool = new Tools(toolDTO);
        when(toolsRepository.existsById(id)).thenReturn(true);
        when(toolsRepository.findById(id)).thenReturn(java.util.Optional.of(existingTool));

        // Act
        Tools editedTool = toolsService.editTool(toolDTO, id);

        // Assert
        assertEquals(toolDTO.title(), editedTool.getTitle());
        assertEquals(toolDTO.link(), editedTool.getLink());
        assertEquals(toolDTO.description(), editedTool.getDescription());
        assertEquals(toolDTO.tags(), editedTool.getTags());
    }
}
