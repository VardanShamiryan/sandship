package com.rockbitegames.sandship.repository;

import com.rockbitegames.sandship.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialTypeRepository extends JpaRepository<MaterialType, String> {
    boolean existsByName(String name);
}

