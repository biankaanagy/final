package com.epam.training.ticketservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "screen")
public class Screening {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String roomName;
    private Date screenTime;

    protected Screening(SimpleScreeningBuilder simpleScreeningBuilder) {
    }

    public Screening(String title, String roomName, Date screenTime) {
        this.title = title;
        this.roomName = roomName;
        this.screenTime = screenTime;
    }

    public Screening() {
    }

    public String getTitle() {
        return title;
    }

    public String getRoomName() {
        return roomName;
    }

    public Date getScreenTime() {
        return screenTime;
    }

    public static SimpleScreeningBuilder builder() {
        return new SimpleScreeningBuilder();
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, roomName, screenTime);
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
        private String roomName;
        private Date screenTime;

        private SimpleScreeningBuilder() {
        }

        public SimpleScreeningBuilder withTitle(String title){
            this.title = title;
            return this;
        }

        public SimpleScreeningBuilder withRoomName(String roomName){
            this.roomName = roomName;
            return this;
        }

        public SimpleScreeningBuilder withScreenTime(Date screenTime){
            this.screenTime = screenTime;
            return this;
        }

        public Screening build() {
            return new Screening(this);
        }

    }

}