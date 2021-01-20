package com.github.qikangchen.spring.data.normalized.db.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "matched_item_mapping")
@Setter
@Getter
public class MatchedItem extends BaseEntity {

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tomtom_db_item_id")
    private Incident tomtomIncident;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "here_db_item_id")
    private Incident hereIncident;

    @Column(name = "confidence_level")
    private int confidenceLevel;

    @ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "request_time_stamp_id")
    private Request request;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchedItem that = (MatchedItem) o;
        return confidenceLevel == that.confidenceLevel && Objects.equals(tomtomIncident, that.tomtomIncident) && Objects.equals(hereIncident, that.hereIncident);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tomtomIncident, hereIncident, confidenceLevel);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MatchedItem.class.getSimpleName() + "[", "]")
                .add("tomtomIncident=" + tomtomIncident)
                .add("hereIncident=" + hereIncident)
                .add("confidenceLevel=" + confidenceLevel)
                .toString();
    }
}
