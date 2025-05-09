package com.jobPortal.jobAppWithReact.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.jobPortal.jobAppWithReact.model.JobPost;
import com.jobPortal.jobAppWithReact.service.JobService;

@RestController
@CrossOrigin("http://localhost:3000")
public class JobController {

	@Autowired
	private JobService service;

	@GetMapping("/jobPosts")
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}

	@GetMapping("/jobPost/{jobId}")
	public JobPost getJob(@PathVariable int jobId){
		return service.getJob(jobId);
	}

	@PostMapping
	public JobPost addPost(@RequestBody JobPost jobPost){
		service.addJobPost(jobPost);
		return service.getJob(jobPost.getPostId());
	}

	@PutMapping("/jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost){
		service.updatePost(jobPost);
		return service.getJob(jobPost.getPostId());
	}

	@DeleteMapping("/jobPost/{jobId}")
	public String deletePost(@PathVariable int jobId){
		service.deletePost(jobId);
		return "deleted";
	}
}
