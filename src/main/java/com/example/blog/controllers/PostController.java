package com.example.blog.controllers;

import com.example.blog.Services.PostService;
import com.example.blog.models.Post;
import com.example.blog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostService postSvc;
    private final PostRepository postDao;


    public PostController(PostService postSvc, PostRepository postDao) {
        this.postSvc = postSvc;
        this.postDao = postDao;
    }

    //    All Posts
    @GetMapping(path = "/posts")
    public String posts(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    //  Individual Post
    @GetMapping (path = "/posts/{id}")
    public String show(Model model, @PathVariable long id){
        model.addAttribute("post", postDao.findById(id));
        return "posts/show";
    }

    //    Create a post
    @GetMapping (path = "/posts/create")
    public String getCreate(Model model){
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping(path = "/posts/create")
    public String createPost(@ModelAttribute Post post){
        postDao.save(post);
        return "redirect:/posts";
    }

    //    Editing a post
    @GetMapping(path = "/posts/{id}/edit")
    public String getEdit(Model model, @PathVariable long id){
        model.addAttribute("post", postDao.findById(id));
        return "/posts/edit";
    }

    @PostMapping(path = "/posts/edit")
    public String handleEdit(@ModelAttribute Post post){
        postDao.findById(post.getId());
        postDao.save(post);
        return "redirect:/posts";
    }

}
