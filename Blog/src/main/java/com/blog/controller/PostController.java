package com.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.entity.Post;
import com.blog.repository.PostRepo;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostRepo postRepo;

	@GetMapping("/posthome")
	public String home()
	{
		
		
		return "normal/user_dashboard";
	}
	
	@GetMapping("/profile")
	public String profile()
	{
		return "normal/user_profile";
	}
	@GetMapping("/add_post")
	public String addPost(Model model)
	{
		model.addAttribute("post", new Post());
		return "normal/add_post";
	}
	@GetMapping("/manage_feed")
	public String manage(Model m)
	{
		Iterable<Post> post = this.postRepo.findAll();
		m.addAttribute("post", post);
		return "normal/manage_feed";
	}
	
	@PostMapping("/do_post")
	public String posts(@ModelAttribute("post") Post post)
	{
		Post p = postRepo.save(post);
		System.out.println(p);
		return "normal/add_post";
	}
	
	@GetMapping("/delete/{id}")
	public String DeletePost(@PathVariable("id") int id)
	{
		postRepo.deleteById(id);
		return "redirect:/post/manage_feed";
	}
	
//	@GetMapping("/edit/{id}")
//	public String editPost(@PathVariable("id") int id, Model model)
//	{
//		Post post1 = this.postRepo.findById(id).get();
//		model.addAttribute("post", post1);
//		
//		return "normal/edit_post";
//	}
//
//	@PostMapping("/update")
//	public String updatePost(@PathVariable("id") int id, @ModelAttribute Post post, Model model)
//	{
//		Post post1 = this.postRepo.findById(id).get();
//		model.addAttribute("post", post1);
//
//		postRepo.save(post);
//		
//		return "redirect:/post/manage_feed";
//	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") int id, Model model) {
	    Post post = postRepo.findById(id)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
	    
	    model.addAttribute("post", post);
	    return "normal/edit_post";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") int id, Model model, @ModelAttribute Post post) {
	    postRepo.save(post);
	    return "redirect:/post/manage_feed";
	}
}
