package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.data.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {

    //Use projection with a native query. Use As timestamp to map it to the interface method getTimestamp
    @Query(value = "SELECT request_time_stamp.request_time_stamp AS timestamp FROM request_time_stamp", nativeQuery = true)
    List<Timestamp> findAllTimeStamps();

    //Interface need
    interface Timestamp {
        int getTimestamp();
    }
}
