package com.github.qikangchen.Spring.Demo.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity(name = "request")
@Table(name = "request_time_stamp")
public class Request extends BaseEntity {

    @Getter
    @Setter
    @Column(name = "request_time_stamp")
    private int requestTimeStamp;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "request_local_mapping",
            joinColumns = @JoinColumn(name = "request_time_stamp_id"),
            inverseJoinColumns = @JoinColumn(name = "request_local_info_id")
    )
    private RequestLocalInfo requestLocalInfo;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Incident> incidents;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MatchedItem> matchedItems;

    public List<Incident> getIncidents() {
        return Collections.unmodifiableList(incidents);
    }

    protected List<Incident> getIncidentsInternal(){
        if(incidents == null){
            incidents = new ArrayList<>();
        }
        return incidents;
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

    public List<MatchedItem> getMatchedItems(){
        return Collections.unmodifiableList(matchedItems);
    }

    protected List<MatchedItem> getMatchedItemsInternal(){
        if(matchedItems == null){
            matchedItems = new ArrayList<>();
        }

        return matchedItems;
    }

    public void addMatchedItem(MatchedItem matchedItem){
        if(matchedItem.isNew()){
            getMatchedItemsInternal().add(matchedItem);
        }
        matchedItem.setRequest(this);
    }

    public void removeMatchedItem(MatchedItem matchedItem){
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestTimeStamp == request.requestTimeStamp && Objects.equals(requestLocalInfo, request.requestLocalInfo) && Objects.equals(incidents, request.incidents) && Objects.equals(matchedItems, request.matchedItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestTimeStamp, requestLocalInfo, incidents, matchedItems);
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
