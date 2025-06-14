package ru.otus.timofeev.task9.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false, length = 128)
    private String name;

    @NonNull
    @Column(nullable = false, unique = true, length = 128)
    private String email;

    @NonNull
    @Column(nullable = false)
    private Long hashPass;
}
