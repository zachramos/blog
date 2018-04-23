package com.example.blog.controllers;

import com.example.blog.Services.PostService;
import com.example.blog.models.Post;
import com.example.blog.repositories.PostRepository;
import com.example.blog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostService postSvc;
    private final PostRepository postDao;
    private final UserRepository userDao;


    public PostController(PostService postSvc, PostRepository postDao, UserRepository userDao) {
        this.postSvc = postSvc;
        this.postDao = postDao;
        this.userDao = userDao;
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
        post.setUser(userDao.findById(1));
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
        Post changedPost = postDao.findById(post.getId());
        changedPost.setTitle(post.getTitle());
        changedPost.setBody(post.getBody());
        changedPost.setUser(userDao.findById(1));
        postDao.save(changedPost);
        return "redirect:/posts";
    }

    @PostMapping(path = "/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.delete(id);
        return "redirect:/posts";
    }

}
