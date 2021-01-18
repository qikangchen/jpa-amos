package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.MatchedItem;
import com.github.qikangchen.Spring.Demo.data.Request;
import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
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
    }

    @Test
    void testInsertRequest() {

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);

        myRepo.insertRequest(request);


        Request requestFromDb = requestRepository.findAll().iterator().next();
        assertThat(requestFromDb, equalTo(request));
    }

    @Test
    void testGetRequest() {

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);

        myRepo.insertRequest(request);

        Request requestFromDb = myRepo.getRequest("Berlin", 1000);
        assertThat(requestFromDb, equalTo(request));
    }

    @Test
    void testGetRequestWrongTimestamp() {

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);

        myRepo.insertRequest(request);

        assertThrows(IllegalStateException.class, () -> myRepo.getRequest("Berlin", 1001));
    }

    @Test
    void testGetRequestWrongCityName() {

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);

        myRepo.insertRequest(request);

        assertThrows(IllegalStateException.class, () -> myRepo.getRequest("Muenchen", 1000));
    }

    @Test
    void testInsertLocalInfo(){

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        requestLocalInfo.setCentreLongitude(111);
        requestLocalInfo.setCentreLatitude(200);
        requestLocalInfo.setSearchRadiusInKm(22);

        myRepo.insertLocalInfo(requestLocalInfo);


        RequestLocalInfo requestLocalInfoFromDb = requestLocalInfoRepository.findAll().iterator().next();

        assertThat(requestLocalInfoRepository.count(), equalTo(1L));
        assertThat(requestLocalInfoFromDb, equalTo(requestLocalInfo));
    }

    @Test
    void testGetLocalInfos(){

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        RequestLocalInfo requestLocalInfo2 = new RequestLocalInfo();
        requestLocalInfo2.setCityName("Muenchen");

        requestLocalInfoRepository.save(requestLocalInfo);
        requestLocalInfoRepository.save(requestLocalInfo2);


        List<RequestLocalInfo> localInfos = myRepo.getLocalInfos();
        assertThat(localInfos, hasSize(2));
        assertThat(localInfos.get(0), equalTo(requestLocalInfo));
        assertThat(localInfos.get(1), equalTo(requestLocalInfo2));
    }

    @Test
    void testRequestLocalInfoFromCityName(){
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        RequestLocalInfo requestLocalInfo2 = new RequestLocalInfo();
        requestLocalInfo2.setCityName("Muenchen");

        requestLocalInfoRepository.save(requestLocalInfo);
        requestLocalInfoRepository.save(requestLocalInfo2);

        RequestLocalInfo requestLocalInfoFromDb = myRepo.getRequestLocalInfoFromCityName("Berlin");
        assertThat(requestLocalInfoFromDb, equalTo(requestLocalInfo));
    }

    @Test
    void testTimestampFromCity(){

        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        RequestLocalInfo requestLocalInfo2 = new RequestLocalInfo();
        requestLocalInfo2.setCityName("Muenchen");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);

        Request request2 = new Request();
        request2.setRequestTimeStamp(2000);
        request2.setRequestLocalInfo(requestLocalInfo);

        Request request3 = new Request();
        request3.setRequestTimeStamp(3000);
        request3.setRequestLocalInfo(requestLocalInfo2);

        myRepo.insertRequest(request);
        myRepo.insertRequest(request2);
        myRepo.insertRequest(request3);


        List<RequestRepository.Timestamp> timestamps = myRepo.getTimeStampsFromCityName("Berlin");
        assertThat(timestamps.get(0).getRequestTimeStamp(), equalTo(request.getRequestTimeStamp()));
        assertThat(timestamps.get(1).getRequestTimeStamp(), equalTo(request2.getRequestTimeStamp()));
    }
}