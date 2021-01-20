package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.annotation.DataMysqlTest;
import com.github.qikangchen.spring.data.normalized.db.data.Incident;
import com.github.qikangchen.spring.data.normalized.db.data.MatchedItem;
import com.github.qikangchen.spring.data.normalized.db.data.Request;
import com.github.qikangchen.spring.data.normalized.db.data.RequestLocalInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataMysqlTest
public class RequestRepositoryTest {

    @Autowired
    private IncidentRepository incidentRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private RequestLocalInfoRepository requestLocalInfoRepository;

    @Test
    void testSaveRequestAmount(){
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);

        assertThat(requestRepository.count(), equalTo(0L));

        requestRepository.save(request);
        assertThat(requestRepository.count(), equalTo(1L));
    }

    @Test
    void testSaveRequestEquals(){
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);
        request.addIncident(DummyData.getIncidentDummy());
        requestRepository.save(request);


        Request requestFromDatabase = requestRepository.findAll().iterator().next();
        assertThat(requestFromDatabase, equalTo(request));
    }

    @Test
    void testSaveRequestWithMatchedItem() {

        Incident hereIncident = new Incident();
        hereIncident.setDescription("Here");

        Incident tomtomIncident = new Incident();
        tomtomIncident.setDescription("TomTom");

        MatchedItem matchedItem = new MatchedItem();
        matchedItem.setHereIncident(hereIncident);
        matchedItem.setTomtomIncident(tomtomIncident);
        matchedItem.setConfidenceLevel(4);


        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");


        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);
        request.addMatchedItem(matchedItem);
        request.addIncident(hereIncident);
        request.addIncident(tomtomIncident);


        requestRepository.save(request);


        Request requestFromDb = requestRepository.findAll().iterator().next();
        assertThat(requestFromDb, equalTo(request));
    }

    @Test
    void testAlterRequestAddIncidents(){
        Request request = new Request();
        request.setRequestTimeStamp(1000);
        requestRepository.save(request);

        Request requestFromDb = requestRepository.findAll().iterator().next();
        requestFromDb.setRequestTimeStamp(200);


        Request requestFromDb2 = requestRepository.findAll().iterator().next();

        assertThat(requestFromDb2.getRequestTimeStamp(), equalTo(200));
    }

    @Test
    void testFindAllTimestampsByCity(){
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        request.setRequestLocalInfo(requestLocalInfo);
        requestRepository.save(request);

        Request request2 = new Request();
        request2.setRequestTimeStamp(2000);
        request2.setRequestLocalInfo(requestLocalInfo);
        requestRepository.save(request2);

        List<RequestRepository.Timestamp> timestamps = requestRepository.findByRequestLocalInfo(requestLocalInfo, RequestRepository.Timestamp.class);

        assertThat(timestamps, hasSize(2));
        assertThat(timestamps.get(0).getRequestTimeStamp(), equalTo(1000));
        assertThat(timestamps.get(1).getRequestTimeStamp(), equalTo(2000));
    }

    @Test
    void testFindByRequestTimeStamp(){

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        requestRepository.save(request);

        List<Request> requests = requestRepository.findByRequestTimeStamp(1000);
        assertThat(requests, hasSize(1));
        assertThat(requests.get(0).getRequestTimeStamp(), equalTo(1000));
    }

    @Test
    void testFindByRequestTimeStampNotInDb(){

        Request request = new Request();
        request.setRequestTimeStamp(1000);
        requestRepository.save(request);

        List<Request> requests = requestRepository.findByRequestTimeStamp(12000);

        assertThat(requests, hasSize(0));
    }

    @Test
    void testFindByRequestLocalInfo(){
        Request request = new Request();
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        request.setRequestLocalInfo(requestLocalInfo);
        requestRepository.save(request);

        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Berlin");
        List<Request> requests = requestRepository.findByRequestLocalInfo(berlin.get());

        assertThat(requests, hasSize(1));
    }

    @Test
    void testFindByRequestLocalInfoNotInDb(){
        Request request = new Request();
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        request.setRequestLocalInfo(requestLocalInfo);
        requestRepository.save(request);

        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Muenchen");
        assertThat(berlin.isPresent(), equalTo(false));
    }

    @Test
    void testFindByRequestLocalInfoAndTimestamp(){
        Request request = new Request();
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        request.setRequestLocalInfo(requestLocalInfo);
        request.setRequestTimeStamp(1000);
        requestRepository.save(request);

        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Berlin");
        Optional<Request> requests = requestRepository.findByRequestLocalInfoAndRequestTimeStamp(berlin.get(), 1000);

        assertThat(requests.isPresent(), equalTo(true));

        System.out.println(requests.get());
    }

    @Test
    void testFindByRequestLocalInfoAndTimestampNotInDb(){
        Request request = new Request();
        RequestLocalInfo requestLocalInfo = new RequestLocalInfo();
        requestLocalInfo.setCityName("Berlin");
        request.setRequestLocalInfo(requestLocalInfo);
        request.setRequestTimeStamp(1000);
        requestRepository.save(request);


        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Berlin");
        Optional<Request> requests = requestRepository.findByRequestLocalInfoAndRequestTimeStamp(berlin.get(), 1001);

        assertThat(requests.isPresent(), equalTo(false));
    }
}
