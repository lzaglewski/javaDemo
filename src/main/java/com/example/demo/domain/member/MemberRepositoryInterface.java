package com.example.demo.domain.member;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepositoryInterface extends JpaRepository<Member, Long>, CustomMemberRepositoryInterface {
    List<Member> findByEmail(String name);
}
