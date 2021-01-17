package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@DataMysqlTest
class MyRepoTest {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestLocalInfoRepository requestLocalInfoRepository;

    private MyRepo myRepo;

    @BeforeEach
    void setUp(){
        myRepo = new MyRepo(requestRepository, requestLocalInfoRepository);

        System.out.println(requestRepository);
    }

    @Test
    void getRequest() {
    }

    @Test
    void insertRequest() {
    }

    @Test
    void deleteAll(){
        myRepo.dropAllData();
    }
}