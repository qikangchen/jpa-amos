package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.data.MatchedItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchedItemRepository extends CrudRepository<MatchedItem, Integer> {
}
