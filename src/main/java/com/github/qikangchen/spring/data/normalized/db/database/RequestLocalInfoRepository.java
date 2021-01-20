package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.data.RequestLocalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestLocalInfoRepository extends CrudRepository<RequestLocalInfo, Integer> {

    Optional<RequestLocalInfo> findByCityName(String cityName);

}
