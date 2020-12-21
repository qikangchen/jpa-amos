package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
import java.util.*;

@Entity(name = "request")
@Table(name = "request_time_stamp")
public class Request {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "request_time_stamp")
    private int requestTimeStamp;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Incident> incidents;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "request_local_mapping",
            joinColumns = @JoinColumn(name = "request_time_stamp_id"),
            inverseJoinColumns = @JoinColumn(name = "request_local_info_id")
    )
    private RequestLocalInfo requestLocalInfo;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MatchedItem> matchedItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addIncident(Incident incident) {
        incident.setRequest(this);

        if(incidents == null){
            incidents = new ArrayList<>();
        }
        incidents.add(incident);
    }

    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public int getRequestTimeStamp() {
        return requestTimeStamp;
    }

    public void setRequestTimeStamp(int requestTimeStamp) {
        this.requestTimeStamp = requestTimeStamp;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Request.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("requestTimeStamp=" + requestTimeStamp)
                .add("incidents=" + incidents)
                .add("requestLocalInfo=" + requestLocalInfo)
                .add("matchedItems=" + matchedItems)
                .toString();
    }
}
