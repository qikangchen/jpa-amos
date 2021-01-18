package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

@DataMysqlTest
public class IncidentRepositoryTest {

    @Autowired
    private IncidentRepository repo;

    @Test
    void testPrint(){
        repo.findAll().forEach(System.out::println);
    }

    @Test
    void testTrafficId(){
        Incident incident = new Incident();
        incident.setTrafficId("TDD2339393939393");

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getTrafficId(), equalTo("TDD2339393939393"));
    }

    @Test
    void testDescription(){
        Incident incident = new Incident();
        incident.setDescription("My description");

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getDescription(), equalTo("My description"));
    }

    @Test
    void testCity(){
        Incident incident = new Incident();
        incident.setCity("Berlin");

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getCity(), equalTo("Berlin"));
    }

    @Test
    void testCountry(){
        Incident incident = new Incident();
        incident.setCountry("Deutschland");

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getCountry(), equalTo("Deutschland"));
    }

    @Test
    void testLengthInMeter(){
        Incident incident = new Incident();
        incident.setLengthInMeter(12355.45);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getLengthInMeter(), equalTo(12355.45));
    }

    @Test
    void testTypes(){

        List<Incident.Types> types = new ArrayList<>();
        types.add(Incident.Types.ACCIDENT);
        types.add(Incident.Types.CONSTRUCTION);

        Incident incident = new Incident();
        incident.setTypes(types);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getTypes(), equalTo(types));
    }

    @Test
    void testSize(){
        Incident incident = new Incident();
        incident.setSize(3);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getSize(), equalTo(3));
    }

    @Test
    void testStartPosition(){
        Incident incident = new Incident();
        Location startPosition = new Location("123", "456");
        incident.setStartPosition(startPosition);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getStartPosition(), equalTo(startPosition));
    }

    @Test
    void testEndPosition(){
        Incident incident = new Incident();
        Location endPosition = new Location("123", "456");
        incident.setEndPosition(endPosition);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getEndPosition(), equalTo(endPosition));
    }

    @Test
    void testLocations(){

        Location location1 = new Location("123", "456");
        Location location2 = new Location("789", "101");
        List<Location> locations = new ArrayList<>();
        locations.add(location1);
        locations.add(location2);

        Incident incident = new Incident();
        incident.setLocations(locations);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getLocations(), equalTo(locations));
    }

    @Test
    void testStartPositionStreet(){
        Incident incident = new Incident();
        incident.setStartPositionStreet("My Street");

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getStartPositionStreet(), equalTo("My Street"));
    }

    @Test
    void testEndPositionStreet(){
        Incident incident = new Incident();
        incident.setEndPositionStreet("My Street");

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getEndPositionStreet(), equalTo("My Street"));
    }

    @Test
    void testVerified(){
        Incident incident = new Incident();
        incident.setVerified(true);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.isVerified(), equalTo(true));
    }

    @Test
    void testProvider(){
        Incident incident = new Incident();
        incident.setProvider(Incident.Provider.HERE);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getProvider(), equalTo(Incident.Provider.HERE));
    }

    @Test
    void testEntryTime(){
        Incident incident = new Incident();
        LocalDateTime now = LocalDateTime.now();
        incident.setEntryTime(now);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getEntryTime(), equalTo(now));
    }

    @Test
    void testEndTime(){
        Incident incident = new Incident();
        LocalDateTime now = LocalDateTime.now();
        incident.setEndTime(now);

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getEndTime(), equalTo(now));
    }

    @Test
    void testSave100Incidents(){
        List<Incident> incidents = new ArrayList<>();
        final int INCIDENT_AMOUNT = 100;
        final int LOCATION_AMOUNT = 10;

        for (int i = 0; i < INCIDENT_AMOUNT; i++) {
            Incident incident = new Incident();

            incident.setTrafficId("MYTRAFFICID" + i);

            List<Incident.Types> types = new ArrayList<>();
            types.add(Incident.Types.ACCIDENT);
            incident.setTypes(types);

            incident.setSize(3);

            incident.setDescription("My descritpiont" + i);

            incident.setCity("Berlin");

            incident.setCountry("Deutschland");

            incident.setLengthInMeter(123.333);

            incident.setStartPosition(new Location("13", "14"));

            incident.setEndPosition(new Location("25", "33"));

            List<Location> locations = new ArrayList<>();
            for (int j = 0; j < LOCATION_AMOUNT; j++) {
                Location location = new Location();
                location.setLatitude("10.11" + i);
                location.setLongitude("12.13" + i);
                locations.add(location);
            }
            incident.setLocations(locations);

            incident.setStartPositionStreet("My sTREEEET");

            incident.setEndPositionStreet("My other street");

            incident.setVerified(true);

            incident.setProvider(Incident.Provider.HERE);

            incident.setEntryTime(LocalDateTime.now());

            incident.setEndTime(LocalDateTime.now());

            incidents.add(incident);
        }

        repo.saveAll(incidents);

        assertThat(repo.findAll(), iterableWithSize(100));
    }


    @Test
    void testAmountAfterSavingNewIncident(){
        Incident incident = new Incident();
        incident.setDescription("New Incident");

        repo.save(incident);

        System.out.println(repo.findAll());
        assertThat(repo.findAll(), iterableWithSize(1));
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
        assertThat(repo.findAll(), iterableWithSize(2));
    }

    @Test
    void testAlteringExistingIncidentWithoutSaving(){
        Incident incident = new Incident();
        incident.setDescription("My Incident");
        repo.save(incident);

        Incident incidentFromDb = repo.findAll().iterator().next();
        incidentFromDb.setDescription("Altered");

        assertThat(repo.findAll().iterator().next().getDescription(), equalTo("Altered"));
    }

    @Test
    void testAlteringExistingIncident(){
        Incident incident = new Incident();
        incident.setDescription("My Incident");
        repo.save(incident);

        Incident incidentFromDb = repo.findAll().iterator().next();
        incidentFromDb.setDescription("Altered");
        repo.save(incidentFromDb);

        assertThat(repo.findAll().iterator().next().getDescription(), equalTo("Altered"));
    }

    @Test
    void testDeletingIncident(){
        Incident incident = new Incident();
        incident.setDescription("My Incident");

        repo.save(incident);
        assertThat(repo.count(), equalTo(1L));

        repo.delete(incident);
        assertThat(repo.count(), equalTo(0L));

    }

    @Test
    void testFindByDescription(){
        Incident incident = new Incident();
        incident.setDescription("Baustelle");
        repo.save(incident);

        List<Incident> incidentsFromDb = repo.findByDescription("Baustelle");

        assertThat(incidentsFromDb, hasSize(1));
    }

    @Test
    void testFindByDescriptionQuery(){
        Incident incident = new Incident();
        incident.setDescription("Baustelle");
        repo.save(incident);

        List<Incident> incidentsFromDb = repo.findByDescriptionQuery("Baustelle");

        assertThat(incidentsFromDb, hasSize(1));
    }


    @Test
    void testInsertIncidentWithLocation(){
        Incident incident = new Incident();

        Location location = new Location();
        location.setLatitude("10.11");
        location.setLongitude("12.13");
        List<Location> locations = new ArrayList<>();
        locations.add(location);

        incident.setLocations(locations);

        repo.save(incident);
        assertThat(incident.getId(), not(equalTo(0)));

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb.getLocations(), hasSize(1));
        assertThat(incidentFromDb.getLocations().get(0).getLatitude(), equalTo("10.11"));
        assertThat(incidentFromDb.getLocations().get(0).getLongitude(), equalTo("12.13"));
    }

    @Test
    void testIncidentEquals(){
        Incident incident = getIncidentDummy();

        repo.save(incident);

        Incident incidentFromDb = repo.findById(incident.getId()).get();
        assertThat(incidentFromDb, equalTo(incident));
    }

    public static Incident getIncidentDummy() {
        Incident incident = new Incident();

        incident.setTrafficId("MYTRAFFICID");

        List<Incident.Types> types = new ArrayList<>();
        types.add(Incident.Types.ACCIDENT);
        incident.setTypes(types);

        incident.setSize(3);

        incident.setDescription("My descritpiont");

        incident.setCity("Berlin");

        incident.setCountry("Deutschland");

        incident.setLengthInMeter(123.333);

        incident.setStartPosition(new Location("13", "14"));

        incident.setEndPosition(new Location("25", "33"));

        List<Location> locations = new ArrayList<>();
        Location location1 = new Location();
        location1.setLatitude("10.11");
        location1.setLongitude("12.13");
        locations.add(location1);
        Location location2 = new Location();
        location1.setLatitude("33.11");
        location1.setLongitude("44.13");
        locations.add(location2);
        incident.setLocations(locations);

        incident.setStartPositionStreet("My sTREEEET");

        incident.setEndPositionStreet("My other street");

        incident.setVerified(true);

        incident.setProvider(Incident.Provider.HERE);

        incident.setEntryTime(LocalDateTime.now());

        incident.setEndTime(LocalDateTime.now());
        return incident;
    }
}