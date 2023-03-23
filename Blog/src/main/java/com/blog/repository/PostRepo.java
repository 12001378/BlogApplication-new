package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.blog.entity.Post;

public interface PostRepo extends CrudRepository<Post, Integer> {

	

}
