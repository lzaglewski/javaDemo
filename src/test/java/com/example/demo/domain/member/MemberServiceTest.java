package com.example.demo.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
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
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createMember() {
        String name = "Test Name";
        String email = "test@example.com";

        memberService.createMember(name, email);

        verify(memberRepositoryInterface, times(1)).add(any(Member.class));
        verify(logChannel, times(1)).send(any(Message.class));
    }
}
