package com.github.qikangchen.Spring.Demo.service;

import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.database.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Interval {

    @Autowired
    IncidentRepository repository;

    @Scheduled(fixedRate = 2000)
    public void cronJob(){
        System.out.println(LocalDateTime.now());
        repository.findAll().forEach(System.out::println);
    }
}
