package com.example.springmovie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "actor")

@Getter
@Setter
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @Column(name = "imdb_url")
    private String imdbUrl;

    @JsonIgnore
    @OneToMany(mappedBy = "actor")
    private Set<CastMember> actorCastMembers = new HashSet<>();

    public Actor(Long id, String name, Date birthDate, String profilePicUrl) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.profilePicUrl = profilePicUrl;
    }
}
