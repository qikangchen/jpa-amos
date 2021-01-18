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
public interface RequestRepository extends CrudRepository<Request, Integer> {

    List<Request> findByRequestTimeStamp(int requestTimeStamp);

    List<Request> findByRequestLocalInfo(RequestLocalInfo requestLocalInfo);

    <T> List<T> findByRequestLocalInfo(RequestLocalInfo requestLocalInfo, Class<T> type);

    Optional<Request> findByRequestLocalInfoAndRequestTimeStamp(RequestLocalInfo requestLocalInfo, int requestTimeStamp);

    interface Timestamp {
        int getRequestTimeStamp();
    }
}
