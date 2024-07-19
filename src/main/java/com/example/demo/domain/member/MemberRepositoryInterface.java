package com.example.demo.domain.member;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepositoryInterface {
    List<Member> findByEmail(String name);

    Page<Member> findAll(Pageable pageable);

    void add(Member member);

    Optional<Member> findByUUID(String id);

    void deleteById(String id);

    void save(Member member);
}
