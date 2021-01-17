package com.github.qikangchen.Spring.Demo.data;

import com.github.qikangchen.Spring.Demo.data.converter.LocationConverter;
import com.github.qikangchen.Spring.Demo.data.converter.LocationListConverter;
import com.github.qikangchen.Spring.Demo.data.converter.TypeListConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "incident")
@Table(name = "incident_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incident extends BaseEntity {

    public enum Provider{
        TOMTOM, HERE
    }

    public enum Types{
        ACCIDENT, CONSTRUCTION
    }

    @Column(name = "traffic_id")
    private String trafficId;

    @Convert(converter = TypeListConverter.class)
    @Column(name = "types")
    private List<Types> types;

    @Column(name = "size")
    private int size;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "length_in_meter")
    private double lengthInMeter;

    @Convert(converter = LocationConverter.class)
    @Column(name = "start_position")
    private Location startPosition;

    @Convert(converter = LocationConverter.class)
    @Column(name = "end_position")
    private Location endPosition;

    @Convert(converter = LocationListConverter.class)
    @Column(name = "locations")
    private List<Location> locations;

    @Column(name = "start_position_street")
    private String startPositionStreet;

    @Column(name = "end_position_street")
    private String endPositionStreet;

    @Column(name = "verified")
    private boolean verified;

    @Enumerated
    @Column(columnDefinition = "smallint")
    private Provider provider;

    @Column(name = "entry_time")
    private LocalDateTime entryTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "request_info_mapping",
            joinColumns = @JoinColumn(name = "incident_item_id"),
            inverseJoinColumns = @JoinColumn(name = "request_time_stamp_id")
    )
    private Request request;

    @Override
    public String toString() {
        return new StringJoiner(", ", Incident.class.getSimpleName() + "[", "]")
                .add("trafficId='" + trafficId + "'")
                .add("types=" + types)
                .add("size=" + size)
                .add("description='" + description + "'")
                .add("city='" + city + "'")
                .add("country='" + country + "'")
                .add("lengthInMeter=" + lengthInMeter)
                .add("startPosition=" + startPosition)
                .add("endPosition=" + endPosition)
                .add("locations=" + locations)
                .add("startPositionStreet='" + startPositionStreet + "'")
                .add("endPositionStreet='" + endPositionStreet + "'")
                .add("verified=" + verified)
                .add("provider=" + provider)
                .add("entryTime=" + entryTime)
                .add("endTime=" + endTime)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return size == incident.size && Double.compare(incident.lengthInMeter, lengthInMeter) == 0 && verified == incident.verified && Objects.equals(trafficId, incident.trafficId) && Objects.equals(types, incident.types) && Objects.equals(description, incident.description) && Objects.equals(city, incident.city) && Objects.equals(country, incident.country) && Objects.equals(startPosition, incident.startPosition) && Objects.equals(endPosition, incident.endPosition) && Objects.equals(locations, incident.locations) && Objects.equals(startPositionStreet, incident.startPositionStreet) && Objects.equals(endPositionStreet, incident.endPositionStreet) && provider == incident.provider && Objects.equals(entryTime, incident.entryTime) && Objects.equals(endTime, incident.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trafficId, types, size, description, city, country, lengthInMeter, startPosition, endPosition, locations, startPositionStreet, endPositionStreet, verified, provider, entryTime, endTime);
    }
}
