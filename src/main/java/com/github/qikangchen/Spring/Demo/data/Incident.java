package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Location> locations;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "request_info_mapping",
            joinColumns = @JoinColumn(name = "incident_item_id"),
            inverseJoinColumns = @JoinColumn(name = "request_time_stamp_id")
    )
    private Request request;

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
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
}
