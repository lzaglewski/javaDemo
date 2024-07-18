package com.example.demo.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MemberServiceTest {

    @Mock
    private MemberRepositoryInterface memberRepositoryInterface;
    @Mock
    private DirectChannel logChannel;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMember() {
        // String name = "Test Name";
        // String email = "test@example.com";

        // memberService.createMember(name, email);

        // verify(memberRepositoryInterface, times(1)).save(any(Member.class));
        // verify(logChannel, times(1)).send(any(Message.class));
    }

    @Test
    void getMembersByEmail() {
        String email = "test@example.com";
        Member member = new Member("Test Name", email);

        when(memberRepositoryInterface.findByEmail(email)).thenReturn(Collections.singletonList(member));

        // List<Member> members = memberService.getMembersByEmail(email);

        // assertEquals(1, members.size());
        // assertEquals(email, members.getFirst().getEmail());
        // verify(memberRepositoryInterface, times(1)).findByEmail(email);
    }
}
