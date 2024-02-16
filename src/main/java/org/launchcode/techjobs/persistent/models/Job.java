package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity // Will be mapped to a table, persists
public class Job extends AbstractEntity {
    @ManyToOne
    private Employer employer;
    @ManyToMany
    private List<Skill> skills;

    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer anEmployer, List someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Getters and setters.
    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}