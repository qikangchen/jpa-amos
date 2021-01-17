package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
import java.util.*;

@Entity(name = "request")
@Table(name = "request_time_stamp")
public class Request extends BaseEntity {

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

    protected List<Incident> getIncidentsInternal(){
        if(incidents == null){
            incidents = new ArrayList<>();
        }
        return incidents;
    }

    protected void setIncidentInternal(List<Incident> incidents){
        this.incidents = incidents;
    }

    public List<Incident> getIncidents() {
        return Collections.unmodifiableList(incidents);
    }

    public void addIncident(Incident incident) {
        if(incident.isNew()){
            getIncidentsInternal().add(incident);
        }
        incident.setRequest(this);//Set foreign key
    }

    public void removeIncident(Incident incident){
        throw new IllegalStateException("Not yet implemented");
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
                .add("id=" + getId())
                .add("requestTimeStamp=" + requestTimeStamp)
                .add("incidents=" + incidents)
                .add("requestLocalInfo=" + requestLocalInfo)
                .add("matchedItems=" + matchedItems)
                .toString();
    }
}
