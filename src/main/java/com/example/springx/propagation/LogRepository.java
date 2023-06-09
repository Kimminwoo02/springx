package com.example.springx.propagation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
@Transactional
public class LogRepository {
    private final EntityManager em;


    public void save(Log logMessage){
        em.persist(logMessage);

        if(logMessage.getMessage().contains("로그 예외")){
            log.info("log 저장 시 예외발생");
            throw new RuntimeException("예외 발생");
        }
    }


    public Optional<Log> find(String message){
        return em.createQuery("select l from Log l where l.message =:message", Log.class)
                .setParameter("message",message)
                .getResultList().stream().findAny();
    }
}
