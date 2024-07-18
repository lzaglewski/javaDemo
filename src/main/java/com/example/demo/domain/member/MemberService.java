package com.example.demo.domain.member;

import com.example.demo.domain.common.log.LogMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService {
    private final MemberRepositoryInterface memberRepositoryInterface;
    private final DirectChannel logChannel;
    private final ApplicationEventPublisher eventPublisher;

    public MemberService(MemberRepositoryInterface memberRepositoryInterface, @Qualifier("logChannel") DirectChannel logChannel, ApplicationEventPublisher eventPublisher) {
        this.memberRepositoryInterface = memberRepositoryInterface;
        this.logChannel = logChannel;
        this.eventPublisher = eventPublisher;
    }

    public void createMember(String name, String email) {
        Member member = new Member(name, email);
        memberRepositoryInterface.add(member);

        LogMessage logMessage = new LogMessage("Member created", System.currentTimeMillis());
        logChannel.send(MessageBuilder.withPayload(logMessage).build());
        
        MemberCreatedEvent event = new MemberCreatedEvent(this, name, email);
        eventPublisher.publishEvent(event);
    }

    public Page<Member> list(Pageable pageable) {
        return memberRepositoryInterface.findAll(pageable);
    }

    public Member getMember(UUID id) {
        return memberRepositoryInterface.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid member Id:" + id));
    }

    public void deleteMember(UUID id) {
        memberRepositoryInterface.deleteById(id);
    }
}
