package ru.itmo.softwaresecurity.lab1.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "person")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    @NotNull
    @Size(min = 1)
    private String firstName;

    @Column(nullable = false)
    @NotNull
    @Size(min = 1)
    private String surname;

    @Column(nullable = false, unique = true)
    @NotNull
    @Size(min = 1)
    private String login;

    @Column(nullable = false)
    @NotNull
    @Size(min = 6)
    private String password;

    @ElementCollection(targetClass = UserRole.class)
    @CollectionTable(
            name = "roles",
            joinColumns = @JoinColumn(name = "role_id")
    )
    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<Post> posts;
}
