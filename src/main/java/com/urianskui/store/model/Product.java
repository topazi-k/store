package com.urianskui.store.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @SequenceGenerator(name = "productsSequence", sequenceName = "products_id_seq",allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productsSequence")
    private long id;

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
}
