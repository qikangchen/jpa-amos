package com.github.qikangchen.Spring.Demo.data;


import javax.persistence.*;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Table(name = "request_local_info")
public class RequestLocalInfo extends BaseEntity {

    @Column(name = "centre_latitude")
    private double centreLatitude;

    @Column(name = "centre_longitude")
    private double centreLongitude;

    @Column(name = "search_radius_in_km")
    private double searchRadiusInKm;

    @Column(name = "city_name")
    private String cityName;

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
        return new StringJoiner(", ", RequestLocalInfo.class.getSimpleName() + "[", "]")
                .add("centreLatitude=" + centreLatitude)
                .add("centreLongitude=" + centreLongitude)
                .add("searchRadiusInKm=" + searchRadiusInKm)
                .add("cityName='" + cityName + "'")
                .toString();
    }
}
