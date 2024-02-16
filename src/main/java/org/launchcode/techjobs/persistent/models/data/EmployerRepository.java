package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository; // Create/read/update/delete
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends CrudRepository<Employer, Integer> {
} // Extends CrudRepo for data layering purposes
// Will reference this to send object information through the EmployerController handlers