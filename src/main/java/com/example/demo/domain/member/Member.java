package com.example.demo.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "member")
public class Member {
    @Id
    private UUID uuid;
    private String name;
    private String email;

    public Member(String name, String email) {
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.email = email;
    }

    public Member() {

    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getId() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
