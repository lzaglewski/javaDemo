package com.example.demo.infrastructure;

import com.example.demo.domain.member.Member;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaMemberRepositoryInterface extends JpaRepository<Member, String> {
}
