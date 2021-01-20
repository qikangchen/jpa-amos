package com.github.qikangchen.spring.data.normalized.db.database;

import com.github.qikangchen.spring.data.normalized.db.data.Incident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends CrudRepository<Incident, Integer> {

    // Write query by name convention https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html or https://docs.spring.io/spring-data/jpa/docs/1.5.0.RELEASE/reference/html/jpa.repositories.html
    List<Incident> findByDescription(String description);

    // Write query. Incident refers to @Entity(name = "incident") and :description is the named parameter
    @Query("FROM incident i WHERE i.description = :description")
    List<Incident> findByDescriptionQuery(@Param("description") String description);
}
