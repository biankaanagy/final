package com.epam.training.ticketservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "movie")
@Component
@Data
public class Movie {

    @Id
    @GeneratedValue
    private long id;

    private String title;
    private String genre;
    private int movieLengthMin;

    protected Movie() {
    }

    public Movie(String title, String genre, int movieLengthMin) {
        this.title = title;
        this.genre = genre;
        this.movieLengthMin = movieLengthMin;
    }

    public Movie(final SimpleMovieBuilder simpleMovieBuilder) {
        this.title = simpleMovieBuilder.title;
        this.genre = simpleMovieBuilder.genre;
        this.movieLengthMin = simpleMovieBuilder.movieLengthMin;
    }

    public static SimpleMovieBuilder builder() {
        return new SimpleMovieBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Movie that = (Movie) o;
        return Double.compare(that.movieLengthMin, movieLengthMin) == 0 &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, movieLengthMin);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", movieLengthMin=" + movieLengthMin +
                '}';
    }

    public static final class SimpleMovieBuilder {
        private String title;
        private String genre;
        private int movieLengthMin;

        private SimpleMovieBuilder() {
        }

        public SimpleMovieBuilder withTitle(String name) {
            this.title = name;
            return this;
        }

        public SimpleMovieBuilder withGenre(String genre) {
            this.genre = genre;
            return this;
        }

        public SimpleMovieBuilder withValue(int length) {
            this.movieLengthMin = length;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
