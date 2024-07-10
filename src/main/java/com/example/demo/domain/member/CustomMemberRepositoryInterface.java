package com.example.demo.domain.member;

import java.util.List;

public interface CustomMemberRepositoryInterface {
    List<Member> findByEmail(String email);
}
