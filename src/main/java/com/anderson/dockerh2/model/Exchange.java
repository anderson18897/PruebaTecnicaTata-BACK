package com.anderson.dockerh2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class Exchange {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String description;

    @NotNull
    private Double value;

    public Exchange(String description, Double value) {
        this.description = description;
        this.value = value;
    }

}
