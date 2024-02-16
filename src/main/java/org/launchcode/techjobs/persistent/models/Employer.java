package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity // Will be mapped to a table, persists
public class Employer extends AbstractEntity {
    @NotNull(message = "Location is required.") // Validation no empty
    @NotBlank(message = "Location is required.") // Validation NO EMPTY
    @Size(max = 150) // More lomg
    private String location;

    // Will represent the list of all items in a given job after Job Class setup
    @OneToMany // One employer, many (or not) jobs
    @JoinColumn(name = "employer_id") // Employer should = employer ID
    private List<Job> jobs = new ArrayList<>();

    public Employer(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}