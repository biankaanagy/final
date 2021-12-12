package com.epam.training.ticketservice.model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "account")
@Component
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    protected Account(){
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account(final SimpleAccountBuilder simpleProductBuilder) {
        this.name = simpleProductBuilder.name;
        this.password = simpleProductBuilder.password;
    }

    public static SimpleAccountBuilder builder() {
        return new SimpleAccountBuilder();
    }


    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password);
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static final class SimpleAccountBuilder {
        private String name;
        private String password;

        private SimpleAccountBuilder() {
        }

        public SimpleAccountBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public SimpleAccountBuilder withPwd(String password) {
            this.password = password;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
