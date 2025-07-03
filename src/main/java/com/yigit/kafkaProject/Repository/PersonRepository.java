package com.yigit.kafkaProject.Repository;

import com.yigit.kafkaProject.entity.Person;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CouchbaseRepository<Person, String> {
    // İstersen özel query methodları ekleyebilirsin
}