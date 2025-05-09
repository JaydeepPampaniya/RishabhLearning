package com.jobPortal.jobAppWithReact.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jobPortal.jobAppWithReact.model.JobPost;
import com.jobPortal.jobAppWithReact.repo.JobRepo;

@Service
public class JobService {
	@Autowired
	public JobRepo repo;

	public List<JobPost> returnAllJobPosts() {
		return repo.returnAllJobPosts();
	}

	public void addJobPost(JobPost jobPost) {
		 repo.addJobPost(jobPost);
	}

	public JobPost getJob(int i) {
		return repo.getJob(i);
	}

	public void updatePost(JobPost jobPost) {
		repo.updateJob(jobPost);
	}

	public void deletePost(int id) {
		repo.deletePost(id);
	}
}
