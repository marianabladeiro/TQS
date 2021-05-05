package com.example.P2_books;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "books")
public class Book {
    private String name;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
