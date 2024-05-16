package com.biel.bossaboxchallenge.repository;

import com.biel.bossaboxchallenge.domain.entity.Tools;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolsRepository extends JpaRepository<Tools, Long>{

    //findbytag
    List<Tools> findByTags(String tag);
}
