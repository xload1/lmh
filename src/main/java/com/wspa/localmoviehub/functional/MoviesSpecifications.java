package com.wspa.localmoviehub.functional;

import com.wspa.localmoviehub.entities.Movies;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class MoviesSpecifications {

    public static Specification<Movies> hasGenre(String genreName) {
        return (root, query, builder) -> {
            if (genreName == null || genreName.isEmpty()) {
                return builder.conjunction();
            }
            query.distinct(true);
            Join<Object, Object> genresJoin = root.join("genres", JoinType.LEFT);
            return builder.equal(builder.lower(genresJoin.get("name")), genreName.toLowerCase());
        };
    }

    public static Specification<Movies> ratingGreaterOrEqualTo(Integer minRating) {
        return (root, query, builder) -> {
            if (minRating == null || minRating <= 0 || minRating >= 10) {
                return builder.conjunction();
            }
            return builder.ge(root.get("rating"), minRating*10);
        };
    }

    public static Specification<Movies> ratingLessOrEqualTo(Integer maxRating) {
        return (root, query, builder) -> {
            if (maxRating == null || maxRating <= 0 || maxRating >= 10) {
                return builder.conjunction();
            }
            return builder.le(root.get("rating"), maxRating*10);
        };
    }

    public static Specification<Movies> titleOrDescriptionContains(String keyword) {
        return (root, query, builder) -> {
            if (keyword == null || keyword.isEmpty()) {
                return builder.conjunction();
            }
            String pattern = "%" + keyword.toLowerCase() + "%";
            return builder.or(
                    builder.like(builder.lower(root.get("title")), pattern),
                    builder.like(builder.lower(root.get("shortDescription")), pattern)
            );
        };
    }

    public static Specification<Movies> isReleased() {
        return (root, query, builder) ->
                builder.equal(root.get("released"), true);
    }
}
