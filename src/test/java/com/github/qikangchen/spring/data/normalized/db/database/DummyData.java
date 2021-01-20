package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.data.Incident;
import com.github.qikangchen.spring.data.normalized.db.data.Location;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DummyData {

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
