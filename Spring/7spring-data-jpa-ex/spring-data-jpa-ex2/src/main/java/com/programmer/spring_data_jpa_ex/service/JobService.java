package com.programmer.spring_data_jpa_ex.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.programmer.spring_data_jpa_ex.model.JobPost;
import com.programmer.spring_data_jpa_ex.repo.JobRepo;

@Service
public class JobService {
	@Autowired
	public JobRepo repo;

	public List<JobPost> returnAllJobPosts() {
		return repo.findAll();
	}

	public void addJobPost(JobPost jobPost) {
		 repo.save(jobPost);
	}

	public JobPost getJob(int i) {
		return repo.findById(i).orElse(new JobPost());
	}

//	public JobPost getJob(int i) {
//		return repo.findById(i).orElse(new JobPost());
//	}


	public void addAllPost(List<JobPost> it) {
		repo.saveAll(it);
	}

	public void updatePost(JobPost jobPost) {
		repo.save(jobPost);
	}

	public void deletePost(int id) {
		repo.deleteById(id);
	}

	public void load() {
		List<JobPost> jobs = new ArrayList<>(List.of(
						new JobPost(1, "Software Engineer", "Exciting", 3),
						new JobPost(2, "Data Scientist", "Join our", 5),
						new JobPost(3, "Frontend Developer", "Create", 2),
						new JobPost(4, "Network Engineer", "Design",4),
						new JobPost(5, "UX Designer", "Shape.", 3)
		));

		repo.saveAll(jobs);
	}

	public List<JobPost> search(String keyword){
		return repo.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
	}
}
