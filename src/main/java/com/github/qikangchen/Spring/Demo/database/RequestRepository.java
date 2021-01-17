package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.Request;
import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
interface RequestRepository extends CrudRepository<Request, Integer> {

//    @Query("FROM request_time_stamp i WHERE i.description = :description")
//    List<Incident> findByCityNameAndTimeStamp(@Param("cityName") String cityName, @Param("timestamp") int timestamp);

    List<Request> findByRequestTimeStamp(int requestTimeStamp);

    List<Request> findByRequestLocalInfo(RequestLocalInfo requestLocalInfo);

    Optional<Request> findByRequestLocalInfoAndRequestTimeStamp(RequestLocalInfo requestLocalInfo, int requestTimeStamp);

    //Use projection with a native query. Use As timestamp to map it to the interface method getTimestamp
    @Query(value = "SELECT request_time_stamp.request_time_stamp AS timestamp FROM request_time_stamp", nativeQuery = true)
    List<Timestamp> findAllTimeStamps();

    //Interface need
    interface Timestamp {
        int getTimestamp();
    }
}
