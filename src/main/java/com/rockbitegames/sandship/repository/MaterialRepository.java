package com.rockbitegames.sandship.repository;

import com.rockbitegames.sandship.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, String> {
    boolean existsByName(String name);
    Optional<Material> findById(String id);

}

