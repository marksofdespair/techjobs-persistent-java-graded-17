package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Job;
import org.springframework.data.repository.CrudRepository; // Create/read/update/delete
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Integer> {
} // Extends CrudRepo for data layering purposes

