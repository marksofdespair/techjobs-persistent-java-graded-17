package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Skill;
import org.springframework.data.repository.CrudRepository; // Create/read/update/delete
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends CrudRepository<Skill, Integer> {
} // Extends CrudRepo for data layering purposes
// Will reference this to send object information through the SkillController handlers