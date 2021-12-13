package com.epam.training.ticketservice.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "room")
@Component
@Data
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int chairNumber;
    private int rowOfChairs;
    private int columnOfChairs;

    protected Room(){
    }

    public Room(String name, int rowOfChairs, int columnOfChairs) {
        this.name = name;
        this.rowOfChairs = rowOfChairs;
        this.columnOfChairs = columnOfChairs;
        this.chairNumber = rowOfChairs * columnOfChairs;
    }

    public Room(final SimpleRoomBuilder simpleRoomBuilder) {
        this.name = simpleRoomBuilder.name;
        this.rowOfChairs = simpleRoomBuilder.rowOfChairs;
        this.columnOfChairs = simpleRoomBuilder.columnOfChairs;
        this.chairNumber = simpleRoomBuilder.rowOfChairs * simpleRoomBuilder.columnOfChairs;
    }

    public static SimpleRoomBuilder builder() {
        return new SimpleRoomBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return chairNumber == room.chairNumber && rowOfChairs == room.rowOfChairs && columnOfChairs == room.columnOfChairs && Objects.equals(id, room.id) && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chairNumber, rowOfChairs, columnOfChairs);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", rowOfChairs=" + rowOfChairs +
                ", columnOfChairs=" + columnOfChairs +
                '}';
    }

    public static final class SimpleRoomBuilder {
        private String name;
        private int rowOfChairs;
        private int columnOfChairs;

        private SimpleRoomBuilder() {
        }

        public SimpleRoomBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SimpleRoomBuilder withRow(int r) {
            this.rowOfChairs = r;
            return this;
        }

        public SimpleRoomBuilder withCol(int c) {
            this.columnOfChairs = c;
            return this;
        }

        public Room build() {
            return new Room(this);
        }
    }
}
