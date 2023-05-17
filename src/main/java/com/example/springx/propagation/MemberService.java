package com.example.springx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final LogRepository logRepository;

    public void joinV1(String username){
        Member member = new Member(username);
        Log logMessage = new Log(username);

        log.info("==memberRepository 호출 시작");
        memberRepository.save(member);
        log.info("==memberRepository 호출 종료");

        log.info("==logRepository 호출 시작");
        logRepository.save(logMessage);
        log.info("==logRepository 호출 종료");
    }
    public void joinV2(String username){
        Member member = new Member(username);
        Log logMessage = new Log(username);

        log.info("==memberRepository 호출 시작");
        memberRepository.save(member);
        log.info("==memberRepository 호출 종료");

        log.info("==logRepository 호출 시작");
        try{
            logRepository.save(logMessage);
        }catch (RuntimeException e){
            log.info("로그 저장에 실패했습니다. logMEssage={}",logMessage.getMessage());
            log.info("정상 흐름 반환");
        }
        log.info("==logRepository 호출 종료");
    }
}