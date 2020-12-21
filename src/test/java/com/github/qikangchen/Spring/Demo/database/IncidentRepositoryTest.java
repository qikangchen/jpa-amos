package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DataMysqlTest
class IncidentRepositoryTest {

    @Autowired
    private IncidentRepository repo;

    @Test
    void testPrint(){
        repo.findAll().forEach(System.out::println);
    }


    @Test
    void testAmountWithoutSavingNewIncident(){
        Incident incident = new Incident();
        incident.setDescription("New Incident");


        assertThat(repo.findAll(), iterableWithSize(2));
    }

    @Test
    void testAmountAfterSavingNewIncident(){
        Incident incident = new Incident();
        incident.setDescription("New Incident");

        repo.save(incident);

        System.out.println(repo.findAll());
        assertThat(repo.findAll(), iterableWithSize(3));
    }

    @Test
    void testAmountAfterSaving2NewIncident(){
        Incident incident = new Incident();
        incident.setDescription("New Incident");

        Incident incident2 = new Incident();
        incident2.setDescription("New Incident2");

        repo.save(incident);
        repo.save(incident2);

        System.out.println(repo.findAll());
        assertThat(repo.findAll(), iterableWithSize(4));
    }

    @Test
    void testAlteringExistingIncidentWithoutSaving(){
        Incident incident = repo.findById(1).get();
        incident.setDescription("Altered");


        System.out.println(repo.findAll());
        assertThat(repo.findById(1).get().getDescription(), equalTo("Altered"));
    }

    @Test
    void testAlteringExistingIncident(){
        Incident incident = repo.findById(1).get();
        incident.setDescription("Altered");

        repo.save(incident);

        assertThat(repo.findById(1).get().getDescription(), equalTo("Altered"));
    }

    @Test
    void testDeletingIncident(){
        Incident incident = repo.findById(1).get();
        repo.delete(incident);

        assertThat(repo.findById(1).isPresent(), is(false));

    }

    @Test
    void testFindByDescription(){
        List<Incident> incident = repo.findByDescription("Baustelle");

        assertThat(incident, hasSize(1));
    }

    @Test
    void testFindByDescriptionQuery(){
        List<Incident> incidents = repo.findByDescriptionQuery("Baustelle");

        assertThat(incidents, hasSize(1));
    }

    @Test
    void testFindByDescriptionQueryChange(){
        List<Incident> incidents = repo.findByDescriptionQuery("Baustelle"); // ID=1

        incidents.get(0).setDescription("New description");

        assertThat(repo.findById(1).get().getDescription(), equalTo("New description"));
    }

    @Test
    void testInsertIncidentWithLocation(){
        Incident incident = new Incident();

        Location location = new Location();
        location.setLatitude("10.11");
        location.setLongitude("12.13");

        incident.addLocation(location);

        repo.save(incident);

        assertThat(incident.getId(), not(equalTo(0)));
        assertThat(repo.findById(incident.getId()).get().getLocations(), hasSize(1));
    }

    @Test
    void testInsert100IncidentsWithSaveAll(){

        List<Incident> incidents = new ArrayList<>();
        final int INCIDENT_AMOUNT = 100;
        int LOCATION_AMOUNT = 10;
        for (int i = 0; i < INCIDENT_AMOUNT; i++) {
            Incident incident = new Incident();

            for (int j = 0; j < LOCATION_AMOUNT; j++) {
                Location location = new Location();
                location.setLatitude("10.11");
                location.setLongitude("12.13");
                incident.addLocation(location);
            }

            incident.setDescription("Incident " + i);
            incidents.add(incident);
        }

        repo.saveAll(incidents);

        assertThat(repo.findAll(), iterableWithSize(greaterThanOrEqualTo(100)));
    }
}