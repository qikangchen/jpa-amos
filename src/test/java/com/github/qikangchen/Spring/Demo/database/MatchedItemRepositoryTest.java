package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.annotation.DataMysqlTest;
import com.github.qikangchen.Spring.Demo.data.Incident;
import com.github.qikangchen.Spring.Demo.data.MatchedItem;
import com.github.qikangchen.Spring.Demo.data.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DataMysqlTest
class MatchedItemRepositoryTest {

    @Autowired
    private MatchedItemRepository repo;

    @Test
    void testSave(){

        Incident hereIncident = new Incident();
        hereIncident.setDescription("Here");

        Incident tomtomIncident = new Incident();
        tomtomIncident.setDescription("TomTom");

        MatchedItem matchedItem = new MatchedItem();
        matchedItem.setHereIncident(hereIncident);
        matchedItem.setTomtomIncident(tomtomIncident);
        matchedItem.setConfidenceLevel(4);

        Request request = new Request();
        matchedItem.setRequest(request);

        repo.save(matchedItem);


        MatchedItem matchedItemFromDb = repo.findAll().iterator().next();

        assertThat(repo.count(), equalTo(1L));
        assertThat(matchedItemFromDb, equalTo(matchedItemFromDb));
    }
}