package com.wspa.localmoviehub.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movies implements Comparable<Movies> {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private int id;
    private String title;
    private String shortDescription;
    private String mainDescription;
    private String posterUrl;
    private String trailerUrl;
    private int rating;
    private boolean released;

    public String formatRating() {
        return "" + rating/10 + "." + rating % 10;
    }
    @ManyToMany
    @JoinTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @Override
    public int compareTo(Movies other) {
        return Integer.compare(other.rating, this.rating);
    }

    @ElementCollection
    @CollectionTable(
            name = "movie_cities",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "city")
    private Set<String> cities = new HashSet<>();
}
