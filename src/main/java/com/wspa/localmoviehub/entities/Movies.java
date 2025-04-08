package com.wspa.localmoviehub.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movies {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    int id;
    String title;
    String shortDescription;
    String posterUrl;
    String trailerUrl;
    int rating;
    boolean released;
    //вид 9.0 (с точкой)
    public String formatRating() {
        return "" + rating/10 + "." + rating % 10;
    }
}
