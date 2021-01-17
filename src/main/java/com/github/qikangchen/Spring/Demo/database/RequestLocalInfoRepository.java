package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.data.RequestLocalInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
interface RequestLocalInfoRepository extends CrudRepository<RequestLocalInfo, Integer> {

    Optional<RequestLocalInfo> findByCityName(String cityName);

}
