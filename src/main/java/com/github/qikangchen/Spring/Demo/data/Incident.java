package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "incident")
@Table(name = "incident_item")
public class Incident {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

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

    public void addLocation(Location location){
        location.setIncident(this);

        if(locations == null){
            locations = new ArrayList<>();
        }
        locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                "id=" + id +
                ", description='" + description + '\'' +
                ", locations=" + locations +
                '}';
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
