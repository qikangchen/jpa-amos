package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;

@DataMysqlTest
class RequestLocalInfoRepositoryTest {

    @Autowired
    RequestLocalInfoRepository repository;

    @Test
    void findByCityName() {
        Optional<RequestLocalInfo> cityLocationInfo = repository.findByCityName("Berlin");

        System.out.println(cityLocationInfo);
        assertThat(cityLocationInfo.isPresent(), equalTo(true));
    }
}