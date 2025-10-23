package com.dhruv.activityservice.repository;

import com.dhruv.activityservice.Entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends MongoRepository<Activity,String> {
}
