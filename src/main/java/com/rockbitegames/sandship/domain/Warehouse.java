package com.rockbitegames.sandship.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyJoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    String id;
    @Column(unique = true)
    String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User owner;

    @ElementCollection
    @CollectionTable(name = "warehouse_materials", joinColumns = @JoinColumn(name = "warehouse_id"))
    @MapKeyJoinColumn(name = "material_id")
    @Column(name = "quantity")
    private Map<Material, Integer> materials = new HashMap<>();

}
