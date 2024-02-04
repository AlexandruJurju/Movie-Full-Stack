package com.example.springmovie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "biography", columnDefinition = "TEXT")
    @Schema(description = "Short biography about the actor")
    private String biography;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Cast> cast;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private List<Crew> crew;
}