package com.carspring.blog.controllers;

import com.carspring.blog.models.Post;
import com.carspring.blog.repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blogMain(Model model) {
        model.addAttribute("title", "Blog");
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }
    @GetMapping("/blog/add-article")
    public String blogAddArticle(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        return "add-article";
    }

    @PostMapping("/blog/add-article")
    public String blogAddArticle (@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        Post postArticle = new Post(title, anons, full_text);
        postRepository.save(postArticle);
        return "redirect:/blog";
    }

}
