package com.epam.training.ticketservice.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "screen")
@Component
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

    public String getGenreMovie() {
        return genreMovie;
    }

    public void setGenreMovie(String genreMovie) {
        this.genreMovie = genreMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }

    public static SimpleScreeningBuilder builder() {
        return new SimpleScreeningBuilder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, roomName, screenTime);
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