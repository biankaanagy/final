package com.epam.training.ticketservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "screen")
@Component
@Data
public class Screening implements Comparable<Screening>{

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String genreMovie;
    private String roomName;
    private String screenTime;

    protected Screening() {
    }

    public Screening(String title, String genreMovie, String roomName, String screenTime) {
        this.title = title;
        this.genreMovie = genreMovie;
        this.roomName = roomName;
        this.screenTime = screenTime;
    }

    public Screening(final SimpleScreeningBuilder simpleScreeningBuilder) {
        this.title = simpleScreeningBuilder.title;
        this.genreMovie = simpleScreeningBuilder.genreMovie;
        this.roomName = simpleScreeningBuilder.roomName;
        this.screenTime = simpleScreeningBuilder.screenTime;
    }

    public static SimpleScreeningBuilder builder() {
        return new SimpleScreeningBuilder();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(id, screening.id) && Objects.equals(title, screening.title) && Objects.equals(genreMovie, screening.genreMovie) && Objects.equals(roomName, screening.roomName) && Objects.equals(screenTime, screening.screenTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genreMovie, roomName, screenTime);
    }

    @Override
    public int compareTo(Screening s) {
        if (getScreenTime() == null || s.getScreenTime() == null) {
            return 0;
        }
        return getScreenTime().compareTo(s.getScreenTime());
    }

    @Override
    public String toString() {
        return "Screening{" +
                "title='" + title + '\'' +
                ", roomName='" + roomName + '\'' +
                ", screenTime=" + screenTime +
                '}';
    }

    public static final class SimpleScreeningBuilder{

        private String title;
        private String genreMovie;
        private String roomName;
        private String screenTime;

        private SimpleScreeningBuilder() {
        }

        public SimpleScreeningBuilder withTitle(String title){
            this.title = title;
            return this;
        }

        public SimpleScreeningBuilder withGenremovie(String genreMovie){
            this.genreMovie = genreMovie;
            return this;
        }

        public SimpleScreeningBuilder withRoomName(String roomName){
            this.roomName = roomName;
            return this;
        }

        public SimpleScreeningBuilder withScreenTime(String screenTime){
            this.screenTime = screenTime;
            return this;
        }

        public Screening build() {
            return new Screening(this);
        }

    }

}