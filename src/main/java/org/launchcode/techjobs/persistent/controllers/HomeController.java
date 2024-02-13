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
    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
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
                                    @RequestParam List<Integer> skills) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            return "add";
        } else {
            Optional<Employer> optEmployer = employerRepository.findById(employerId);
            if(optEmployer.isPresent()){
                Employer employer = optEmployer.get();
                newJob.setEmployer(employer);
            }else{
                newJob.setEmployer(new Employer());
            }
            List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
            newJob.setSkills(skillObjs);
            jobRepository.save(newJob);
            return "redirect:";
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