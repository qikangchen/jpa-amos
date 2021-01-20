package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.data.Request;
import com.github.qikangchen.spring.data.normalized.db.data.RequestLocalInfo;
import org.springframework.data.repository.CrudRepository;
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
