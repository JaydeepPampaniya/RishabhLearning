package com.programmer.spring_data_jpa_ex.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.programmer.spring_data_jpa_ex.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.programmer.spring_data_jpa_ex.model.JobPost;
import com.programmer.spring_data_jpa_ex.service.JobService;

@RestController
@CrossOrigin("http://localhost:3000")
public class JobController {

	@Autowired
	private JobService service;

	@Autowired
	private JobRepo repo;

	@GetMapping("/jobPosts")
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}

	@GetMapping("/jobPost/{jobId}")
	public JobPost getJob(@PathVariable int jobId){
		return service.getJob(jobId);
	}

	@PostMapping("/jobPost")
	public String addPost(@RequestBody JobPost jobPost){
		service.addJobPost(jobPost);
		return "success";
	}

	@PostMapping("/addAllPosts")
	public List<JobPost> addAllPosts(@RequestBody List<JobPost> it){
        for (JobPost jobPost : it) {
            service.addJobPost(jobPost);
        }
		return service.returnAllJobPosts();
    }

	@GetMapping("/load")
	public String loadData(){
		service.load();
		return "success";
	}

	@PutMapping("/jobPost")
	public String updateJob(@RequestBody JobPost jobPost){
		service.updatePost(jobPost);
		return "success";
	}

	@DeleteMapping("/jobPost/{jobId}")
	public String deletePost(@PathVariable int jobId){
		service.deletePost(jobId);
		return "deleted";
	}

	@GetMapping("jobPosts/keyword/{keyword}")
	public List<JobPost> searcByKeyword(@PathVariable("keyword") String keyword){
		return service.search(keyword);
	}
}
