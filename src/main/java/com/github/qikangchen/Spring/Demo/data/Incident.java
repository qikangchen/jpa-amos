package com.github.qikangchen.Spring.Demo.data;

import com.github.qikangchen.Spring.Demo.data.converter.LocationConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity(name = "incident")
@Table(name = "incident_item")
public class Incident extends BaseEntity {

    public enum Provider{
        TOMTOM, HERE
    }

    public enum Types{
        ACCIDENT, CONSTRUCTION
    }

//    private String trafficId;
//    private List<Types> type;
//    private int size;
    @Column(name = "description")
    private String description;
//    private String city;
//    private String country;
//    private double lengthInMeter;
//
    @Convert(converter = LocationConverter.class)
    @Column(name = "start_position")
    private Location startPosition;
//    private Location endPosition;
    @Transient
    private List<Location> locations;
//    private String startPositionStreet;
//    private String endPositionStreet;
//
//    private boolean verified;
//
//    private Provider provider;
//    private LocalDateTime entryTime;
//    private LocalDateTime endTime;

//    @OneToMany(mappedBy = "incident",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY,
//            orphanRemoval = true
//    )
//    private List<Location> locations;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "request_info_mapping",
            joinColumns = @JoinColumn(name = "incident_item_id"),
            inverseJoinColumns = @JoinColumn(name = "request_time_stamp_id")
    )
    private Request request;

    protected List<Location> getLocationsInternal(){
        if(locations == null){
            locations = new ArrayList<>();
        }
        return locations;
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    public void addLocation(Location location){
        getLocationsInternal().add(location);
    }

    public void removeLocation(Location location){
        throw new IllegalStateException("Not yet implemented");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(Location startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", startPosition=" + startPosition +
                '}';
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
