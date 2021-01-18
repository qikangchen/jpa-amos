package com.github.qikangchen.Spring.Demo.database;

import com.github.qikangchen.Spring.Demo.data.MatchedItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface MatchedItemRepository extends CrudRepository<MatchedItem, Integer> {
}
