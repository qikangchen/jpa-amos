package com.github.qikangchen.Spring.Demo.integration;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.MatchedItem;
import com.github.qikangchen.Spring.Demo.data.Request;
import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import com.github.qikangchen.Spring.Demo.database.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataMysqlTest
public class IntegrationTest {

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
    void test(){

        // On startup: Add ctiy information
        RequestLocalInfo requestLocalInfoBerlin = new RequestLocalInfo();
        requestLocalInfoBerlin.setCityName("Berlin");
        requestLocalInfoBerlin.setCentreLongitude(111);
        requestLocalInfoBerlin.setCentreLatitude(200);
        requestLocalInfoBerlin.setSearchRadiusInKm(22);

        RequestLocalInfo requestLocalInfoMunich = new RequestLocalInfo();
        requestLocalInfoMunich.setCityName("Muenchen");
        requestLocalInfoMunich.setCentreLongitude(222);
        requestLocalInfoMunich.setCentreLatitude(333);
        requestLocalInfoMunich.setSearchRadiusInKm(33);

        myRepo.insertLocalInfo(requestLocalInfoBerlin);
        myRepo.insertLocalInfo(requestLocalInfoMunich);


        // Insert request
        RequestLocalInfo requestLocalInfoBerlinFromDb = myRepo.getRequestLocalInfoFromCityName("Berlin");

        Incident incidentHere = DummyData.getIncidentDummy();
        incidentHere.setProvider(Incident.Provider.HERE);

        Incident incidentTomTom = DummyData.getIncidentDummy();
        incidentTomTom.setProvider(Incident.Provider.TOMTOM);

        MatchedItem matchedItem = new MatchedItem();
        matchedItem.setHereIncident(incidentHere);
        matchedItem.setTomtomIncident(incidentTomTom);
        matchedItem.setConfidenceLevel(4);

        Request request = new Request();
        request.addIncident(incidentHere);
        request.addIncident(incidentTomTom);
        request.setRequestLocalInfo(requestLocalInfoBerlinFromDb);
        request.setRequestTimeStamp(2000);
        myRepo.insertRequest(request);


        // Get request
        Request requestFromDb = myRepo.getRequest("Berlin", 2000);
        System.out.println(requestFromDb);
        assertThat(requestFromDb, equalTo(request));

    }

}
