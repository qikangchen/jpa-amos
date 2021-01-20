package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.annotation.DataMysqlTest;
import com.github.qikangchen.spring.data.normalized.db.data.RequestLocalInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@DataMysqlTest
class RequestLocalInfoRepositoryTest {

    @Autowired
    RequestLocalInfoRepository repository;

    @Test
    void findByCityName() {

        RequestLocalInfo cityLocationInfo = new RequestLocalInfo();
        cityLocationInfo.setCityName("Berlin");
        cityLocationInfo.setCentreLongitude(11);
        cityLocationInfo.setCentreLongitude(22);
        cityLocationInfo.setSearchRadiusInKm(30);

        repository.save(cityLocationInfo);

        Optional<RequestLocalInfo> cityLocationInfoFromDb = repository.findByCityName("Berlin");
        assertThat(cityLocationInfoFromDb.isPresent(), equalTo(true));
    }
}