package com.github.qikangchen.spring.data.normalized.db.data;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ImmutableSet;
import lombok.*;

import javax.persistence.*;
import java.util.*;

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
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Set<Request> requests;

    @JsonIgnore
    public Set<Request> getRequests(){
        return ImmutableSet.copyOf(requests);
    }

    protected Set<Request> getRequestsInternal(){
        if(requests == null){
            requests = new HashSet<>();
        }
        return requests;
    }

    public void addRequest(Request request) {
        if(request.isNew()){
            getRequestsInternal().add(request);
        }
    }

    public void removeRequest(Request request){
        throw new IllegalStateException("Not yet implemented");
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
