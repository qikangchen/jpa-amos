package com.github.qikangchen.Spring.Demo.data;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "request_local_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public String toString() {
        return new StringJoiner(", ", RequestLocalInfo.class.getSimpleName() + "[", "]")
                .add("centreLatitude=" + centreLatitude)
                .add("centreLongitude=" + centreLongitude)
                .add("searchRadiusInKm=" + searchRadiusInKm)
                .add("cityName='" + cityName + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLocalInfo that = (RequestLocalInfo) o;
        return Double.compare(that.centreLatitude, centreLatitude) == 0 && Double.compare(that.centreLongitude, centreLongitude) == 0 && Double.compare(that.searchRadiusInKm, searchRadiusInKm) == 0 && Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(centreLatitude, centreLongitude, searchRadiusInKm, cityName);
    }
}
