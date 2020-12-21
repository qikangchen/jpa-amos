package com.github.qikangchen.Spring.Demo.data;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "request_local_info")
public class RequestLocalInfo extends BaseEntity {

    @Column(name = "centre_latitude")
    private double centreLatitude;

    @Column(name = "centre_longitude")
    private double centreLongitude;

    @Column(name = "search_radius_in_km")
    private double searchRadiusInKm;

    @OneToMany(mappedBy = "requestLocalInfo", fetch = FetchType.LAZY)
    private List<Request> requests;

    public double getCentreLatitude() {
        return centreLatitude;
    }

    public void setCentreLatitude(double centreLatitude) {
        this.centreLatitude = centreLatitude;
    }

    public double getCentreLongitude() {
        return centreLongitude;
    }

    public void setCentreLongitude(double centreLongitude) {
        this.centreLongitude = centreLongitude;
    }

    public double getSearchRadiusInKm() {
        return searchRadiusInKm;
    }

    public void setSearchRadiusInKm(double searchRadiusInKm) {
        this.searchRadiusInKm = searchRadiusInKm;
    }

    @Override
    public String toString() {
        return "RequestLocalInfo{" +
                "id=" + getId() +
                ", centreLatitude=" + centreLatitude +
                ", centreLongitude=" + centreLongitude +
                ", searchRadiusInKm=" + searchRadiusInKm +
                '}';
    }
}
