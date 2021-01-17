package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.Location;
import com.github.qikangchen.Spring.Demo.data.Request;
import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
    void testFindAll(){
        Iterable<Request> requestList = requestRepository.findAll();

        requestList.forEach(System.out::println);
        assertThat(requestList, iterableWithSize(1));
    }

    @Test
    void testAdd2NewIncidentAndSave_RequestInDBHasNow4Incident(){
        Request request = requestRepository.findAll().iterator().next();

        Incident newIncident1 = new Incident();
        newIncident1.setDescription("New Incident");
        Incident newIncident2 = new Incident();
        newIncident2.setDescription("New Incident2");

        request.addIncident(newIncident1);
        request.addIncident(newIncident2);

        requestRepository.save(request);

        requestRepository.findById(1).get().getIncidents().forEach(System.out::println);
        assertThat(requestRepository.findById(1).get().getIncidents(), hasSize(4));
    }

    @Test
    void testAdd2NewIncidentWithoutSave_RequestInDBHasNow4Incident(){
        Request request = requestRepository.findAll().iterator().next();

        Incident newIncident1 = new Incident();
        newIncident1.setDescription("New Incident");
        Incident newIncident2 = new Incident();
        newIncident2.setDescription("New Incident2");

        request.addIncident(newIncident1);
        request.addIncident(newIncident2);

        requestRepository.findById(1).get().getIncidents().forEach(System.out::println);
        assertThat(requestRepository.findById(1).get().getIncidents(), hasSize(4));
    }

    @Test
    void testAdd2NewIncidentAndSave_4IncidentInDatabase(){
        Request request = requestRepository.findAll().iterator().next();

        Incident newIncident1 = new Incident();
        newIncident1.setDescription("New Incident");
        Incident newIncident2 = new Incident();
        newIncident2.setDescription("New Incident2");

        request.addIncident(newIncident1);
        request.addIncident(newIncident2);

        incidentRepository.findAll().forEach(System.out::println);
        assertThat(incidentRepository.findAll(), iterableWithSize(4));
    }

    @Test
    void testAdd2NewIncidentWithoutSave_4IncidentInDatabase(){
        Request request = requestRepository.findAll().iterator().next();

        Incident newIncident1 = new Incident();
        newIncident1.setDescription("New Incident");
        Incident newIncident2 = new Incident();
        newIncident2.setDescription("New Incident2");

        request.addIncident(newIncident1);
        request.addIncident(newIncident2);

        incidentRepository.findAll().forEach(System.out::println);
        assertThat(incidentRepository.findAll(), iterableWithSize(4));
    }

    @Test
    void testFindAllTimestamps(){
        requestRepository.findAllTimeStamps().forEach(timestamp -> System.out.println(timestamp.getTimestamp()));

//        assertThat(timestamps.get(0).timestamp(), equalTo(1000));
    }

    @Test
    void testInsertRequestWithIncidentAndLocation(){
        Incident incident = new Incident();
        incident.setDescription("New Incident");
        incident.addLocation(new Location("11.12", "13.14"));

        Request request = new Request();
        request.addIncident(incident);

        requestRepository.save(request);
        Optional<Request> requestInDatabase = requestRepository.findById(request.getId());

        assertThat(requestInDatabase.isPresent(), is(true));
        System.out.println(requestInDatabase.get());
    }

    @Test
    void testFindByRequestTimeStamp(){
        List<Request> requests = requestRepository.findByRequestTimeStamp(1000);

        assertThat(requests, hasSize(1));

        System.out.println(requests.get(0));
    }


    @Test
    void testFindByRequestTimeStampNotInDb(){
        List<Request> requests = requestRepository.findByRequestTimeStamp(12000);

        assertThat(requests, hasSize(0));
    }

    @Test
    void testFindByRequestLocalInfo(){
        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Berlin");
        List<Request> requests = requestRepository.findByRequestLocalInfo(berlin.get());

        assertThat(requests, hasSize(1));

        System.out.println(requests.get(0));
    }

    @Test
    void testFindByRequestLocalInfoNotInDb(){
        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Muenchen");
        assertThat(berlin.isPresent(), equalTo(false));
    }

    @Test
    void testFindByRequestLocalInfoAndTimestamp(){
        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Berlin");
        Optional<Request> requests = requestRepository.findByRequestLocalInfoAndRequestTimeStamp(berlin.get(), 1000);

        assertThat(requests.isPresent(), equalTo(true));

        System.out.println(requests.get());
    }

    @Test
    void testFindByRequestLocalInfoAndTimestampNotInDb(){
        Optional<RequestLocalInfo> berlin = requestLocalInfoRepository.findByCityName("Berlin");
        Optional<Request> requests = requestRepository.findByRequestLocalInfoAndRequestTimeStamp(berlin.get(), 1001);

        assertThat(requests.isPresent(), equalTo(false));
    }
}
