package ru.itmo.softwaresecurity.lab1.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "subject", nullable = false)
    @NotNull
    private String subject;

    @Column(name = "body", nullable = false)
    @NotNull
    private String body;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
}
