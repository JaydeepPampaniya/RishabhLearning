package com.programmer._Spring.Boot.Rest.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.springdatarestdemo.model.JobPost;


@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {

	//List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);


}
