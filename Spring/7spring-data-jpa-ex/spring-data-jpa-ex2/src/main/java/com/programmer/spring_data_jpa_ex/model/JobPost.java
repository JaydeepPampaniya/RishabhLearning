package com.programmer.spring_data_jpa_ex.model;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity

public class JobPost {
	@Id
	private int postId;
	private String postProfile; 
	private String postDesc;
	private Integer reqExperience;

	public JobPost() {
	}

	public JobPost(int postId,String postProfile, String postDesc,Integer reqExperience) {
		this.postId = postId;
		this.reqExperience = reqExperience;
		this.postDesc = postDesc;
		this.postProfile = postProfile;
	}
}
