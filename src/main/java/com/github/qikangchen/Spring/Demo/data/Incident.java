package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity(name = "incident")
@Table(name = "incident_item")
public class Incident extends BaseEntity {


    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "incident",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<Location> locations;

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
        if(location.isNew()){
            getLocationsInternal().add(location);
        }

        location.setIncident(this);
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

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + getId() +
                ", description='" + description + '\'' +
                ", locations=" + locations +
                '}';
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
