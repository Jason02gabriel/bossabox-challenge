package com.biel.bossaboxchallenge.service;

import com.biel.bossaboxchallenge.domain.dtos.ToolDTO;
import com.biel.bossaboxchallenge.domain.entity.Tools;
import com.biel.bossaboxchallenge.exception.IdNotFoundException;
import com.biel.bossaboxchallenge.exception.NoRecordsFoundException;
import com.biel.bossaboxchallenge.repository.ToolsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToolsService {

    private final ToolsRepository toolsRepository;

    public ToolsService(ToolsRepository toolsRepository) {
        this.toolsRepository = toolsRepository;
    }

    public List<Tools> findAll() throws Exception {
        if (toolsRepository.findAll().isEmpty()) {
            //retorne uma exceção personalizada
            throw new NoRecordsFoundException("Nenhum registro encontrado");
        }
        return toolsRepository.findAll();
    }

    public ToolDTO save(ToolDTO toolDTO) {
        Tools tools = new Tools(toolDTO);
        toolsRepository.save(tools);
        return new ToolDTO(tools.getTitle(), tools.getLink(), tools.getDescription(), tools.getTags());
    }

    public List<Tools> findByTag(String tag){
        List<Tools> tools = toolsRepository.findAll();
        List<Tools> toolsByTag = new ArrayList<>();
        for (Tools tool : tools) {
            if (tool.getTags().contains(tag)) {
                toolsByTag.add(tool);
            }
        }

        if (toolsByTag.isEmpty()) {
            throw new NoRecordsFoundException("Nenhum registro encontrado");
        }

        return toolsByTag;
    }

    public void deleteById(Long id) {
        //se o id não existir, retorne uma exceção personalizada
        if (!toolsRepository.existsById(id)) {
            throw new IdNotFoundException("Nenhum registro encontrado");
        }
        toolsRepository.deleteById(id);
    }

    public Tools editTool(ToolDTO toolDTO, Long id) {
        if (!toolsRepository.existsById(id)) {
            throw new IdNotFoundException("Nenhum registro encontrado");
        }
        Tools tool = toolsRepository.findById(id).get();
        tool.setTitle(toolDTO.title());
        tool.setLink(toolDTO.link());
        tool.setDescription(toolDTO.description());
        tool.setTags(toolDTO.tags());
        toolsRepository.save(tool);
        return tool;
    }


}
