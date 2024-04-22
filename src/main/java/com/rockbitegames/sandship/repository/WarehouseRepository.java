package com.rockbitegames.sandship.repository;

import com.rockbitegames.sandship.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, String> {

    boolean existsByName(String name);

}

