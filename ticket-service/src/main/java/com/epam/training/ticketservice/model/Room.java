package com.epam.training.ticketservice.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "room")
@Component
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

    public String getName() {
        return name;
    }

    public int getRowOfChairs() {
        return rowOfChairs;
    }

    public int getColumnOfChairs() {
        return columnOfChairs;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setRowOfChairs(int rowOfChairs) {
        this.rowOfChairs = rowOfChairs;
    }

    public void setColumnOfChairs(int columnOfChairs) {
        this.columnOfChairs = columnOfChairs;
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
