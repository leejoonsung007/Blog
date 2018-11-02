package com.springboot.blog.controller;

import com.springboot.blog.service.ArticleService;
import com.springboot.blog.service.CategoryService;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;

//Base Controller
public class BaseController {

    @Autowired
    ArticleService articleService;

    @Autowired
    CommentService commentService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    NoteService noteService;

}
