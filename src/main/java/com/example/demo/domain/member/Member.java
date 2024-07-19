package com.example.demo.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


import com.fasterxml.uuid.Generators;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @Column(length = 36)
    private String uuid;
    private String name;
    private String email;

    public Member(String uuid) {
        this.uuid = uuid;
    }

    public Member(String name, String email) {
        this.uuid = Generators.defaultTimeBasedGenerator().generate().toString();
        this.name = name;
        this.email = email;
    }

    public Member() {

    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
