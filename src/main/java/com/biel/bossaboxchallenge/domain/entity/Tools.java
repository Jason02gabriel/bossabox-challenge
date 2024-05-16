package com.biel.bossaboxchallenge.domain.entity;

import com.biel.bossaboxchallenge.domain.dtos.ToolDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tools")
public class Tools {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String description;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "tool_tag",
//            joinColumns = @JoinColumn(name = "tool_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    @Column(length = 1000) // Ajuste o tamanho conforme necessário
    private String tags; // Lista de tags como uma única string separada por vírgula

    public Tools(ToolDTO toolDTO) {

        this.title = toolDTO.title();
        this.link = toolDTO.link();
        this.description = toolDTO.description();
        tags = String.join(",", toolDTO.tags());
    }

    public List<String> getTags() {
        return Arrays.asList(tags.split(","));
    }

    public void setTags(List<String> tags) {
        this.tags = String.join(",", tags);
    }
}
