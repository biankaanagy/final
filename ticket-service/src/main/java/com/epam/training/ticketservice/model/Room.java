package com.epam.training.ticketservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int rowOfChairs;
    private int columnOfChairs;

    protected Room(SimpleRoomBuilder simpleRoomBuilder){
    }

    public Room(String name, int rowOfChairs, int columnOfChairs) {
        this.name = name;
        this.rowOfChairs = rowOfChairs;
        this.columnOfChairs = columnOfChairs;
    }

    public Room() {

    }

    public static SimpleRoomBuilder builder() {
        return new SimpleRoomBuilder();
    }


    public String getName() {
        return name;
    }

    public int getRowOfChairs() {
        return rowOfChairs;
    }

    public int getColumnOfChairs() {
        return columnOfChairs;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rowOfChairs, columnOfChairs);
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
