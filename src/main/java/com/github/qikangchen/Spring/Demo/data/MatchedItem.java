package com.github.qikangchen.Spring.Demo.data;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
@Table(name = "matched_item_mapping")
public class MatchedItem {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tomtom_db_item_id")
    private Incident tomtomIncident;

    @ManyToOne
    @JoinColumn(name = "here_db_item_id")
    private Incident hereIncident;

    @ManyToOne
    @JoinColumn(name = "request_time_stamp_id")
    private Request request;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Incident getTomtomIncident() {
        return tomtomIncident;
    }

    public void setTomtomIncident(Incident tomtomIncident) {
        this.tomtomIncident = tomtomIncident;
    }

    public Incident getHereIncident() {
        return hereIncident;
    }

    public void setHereIncident(Incident hereIncident) {
        this.hereIncident = hereIncident;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MatchedItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("tomtomIncident=" + tomtomIncident)
                .add("hereIncident=" + hereIncident)
                .toString();
    }
}
