package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired // Allows Spring to resolve and inject collaborating beans (objects) into our bean (object)
    private EmployerRepository employerRepository;

    @Autowired // // Allows Spring to resolve and inject collaborating beans (objects) into our bean (object)
    private SkillRepository skillRepository;

    @Autowired // Allows Spring to resolve and inject collaborating beans (objects) into our bean (object)
    private JobRepository jobRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");
        model.addAttribute("jobs", jobRepository.findAll());

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                    Errors errors, Model model, @RequestParam int employerId,
                                    @RequestParam List<Integer> skills) { // Retrieves skills via integer
        if (errors.hasErrors()) { // Method checks if there are any validation errors in newJob object
            model.addAttribute("title", "Add Job"); // If there are validation errors, adds an attribute named "title" with the value "Add Job" to the model
            return "add";
        } else {
            Optional<Employer> optEmployer = employerRepository.findById(employerId);
            if(optEmployer.isPresent()){ // If the employer exists, retrieves it from the `Optional`
                Employer employer = optEmployer.get();
                newJob.setEmployer(employer); // Set the retrieved employer to the new job object
            }else{
                newJob.setEmployer(new Employer()); // If the employer does not exist, sets a new empty employer to the new job object
            }
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills); // Retrieves a list of Skill objects from the database via provided skill IDs
            newJob.setSkills(skillObjs); // Sets the retrieved skills to the new job object
            jobRepository.save(newJob); // Saves the new job object w/ associated skill/employer info to the database
            return "redirect:"; // If there are no validation errors and the job is saved successfully, it redirects to the default URL
        }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {
        Optional <Job> optJob = jobRepository.findById(jobId);
        if (optJob.isEmpty()){
            return "redirect:";
        }else{
            model.addAttribute("job", optJob.get());
            return "view";
        }
    }


}