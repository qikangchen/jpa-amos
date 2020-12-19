package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "request")
@Table(name = "request_time_stamp")
public class Request {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "request_time_stamp")
    private int requestTimeStamp;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "request_info_mapping",
            joinColumns = @JoinColumn(name = "request_time_stamp_id"),
            inverseJoinColumns = @JoinColumn(name = "incident_item_id")
    )
    private List<Incident> incidents;

//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(name = "request_info_mapping",
//            joinColumns = @JoinColumn(name = "request_time_stamp_id"),
//            inverseJoinColumns = @JoinColumn(name = "request_local_info_id")
//    )
//    private RequestLocalInfo requestLocalInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Request{" +
                "id=" + id +
                ", requestTimeStamp=" + requestTimeStamp +
                ", incidents=" + incidents +
                '}';
    }
}
