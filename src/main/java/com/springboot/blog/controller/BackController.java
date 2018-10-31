package com.springboot.blog.controller;

import com.springboot.blog.dto.ArticleDto;
import com.springboot.blog.entity.*;
import com.springboot.blog.entity.CategoryInfo;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Backstage System
@RestController
@RequestMapping("/admin")
public class BackController extends BaseController {


    private static String username = "leejoonsung";
    private static String password = "123456";


    // Backstage System Login
    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // validate the account
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/admin/index.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/toLogin");
        }
        return null;
    }

    //Add a article
    @ApiOperation("Add a article")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "Title", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "Summary", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "Topping", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "Category ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "MD code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "Picture URL", required = true, dataType = "String")
    })

    @PostMapping("article/")
    public String addArticle(@RequestBody ArticleDto articleDto) {
        articleService.addArticle(articleDto);
        return null;
    }

    //Delete a article
    @ApiOperation("Delete a article")
    @ApiImplicitParam(name = "id", value = "Article ID", required = true, dataType = "Long")
    @DeleteMapping("/article/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteArticleById(id);
        return null;
    }

    //Update a article
    @ApiOperation("update a article")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "Title", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "Summary", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isTop", value = "Topping", required = true, dataType = "Boolean"),
            @ApiImplicitParam(name = "categoryId", value = "Category ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "MD code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pictureUrl", value = "Picture URL", required = true, dataType = "String")
    })
    @PutMapping("/article/{id}")
    public String updateArticle(@PathVariable Long id, @RequestBody ArticleDto articleDto) {
        articleDto.setId(id);
        articleService.updateArticle(articleDto);
        return null;
    }

    // Change the category
    @ApiOperation("Change the category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Article ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "categoryId", value = "Category ID", required = true, dataType = "Long"),
    })
    @PutMapping("/article/sort/{id}")
    public String changeArticleCategory(@PathVariable Long id, Long categoryId) {
        articleService.updateArticleCategory(id, categoryId);
        return null;
    }

    //Change picture
    @ApiOperation("Change the picture of article")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Article ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "pictureUrl", value = "Picture URL", required = true, dataType = "String")
    })
    @PutMapping("/article/picture/{id}")
    public String updateArticlePicture(@PathVariable Long id, String pictureUrl) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setPictureUrl(pictureUrl);
        articleService.updateArticle(articleDto);
        return null;
    }

    // Add a category
    @ApiOperation("Add a category")
    @ApiImplicitParam(name = "name", value = "Category Name", required = true, dataType = "String")
    @PostMapping("/category")
    public String addCategoryInfo(@RequestBody CategoryInfo categoryInfo) {
        categoryService.addCategory(categoryInfo);
        return null;
    }


    // Update category
    @ApiOperation("Update/Edit the category")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Article Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "Category Name", required = true, dataType = "String")
    })
    @PutMapping("/category/{id}")
    public String updateCategoryInfo(@PathVariable Long id, @RequestBody CategoryInfo categoryInfo) {
        categoryService.updateCategory(categoryInfo);
        return null;
    }

    // Delete a category
    @ApiOperation("Delete a category")
    @ApiImplicitParam(name = "id", value = "Category ID", required = true, dataType = "Long")
    @DeleteMapping("/category/{id}")
    public String deleteCategoryInfo(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return null;
    }

    // Get the category from ID
    @ApiOperation("Get the category from ID")
    @ApiImplicitParam(name = "id", value = "Category ID", required = true, dataType = "Long")
    @GetMapping("/category/{id}")
    public CategoryInfo getCategoryInfo(@PathVariable Long id) {
        return categoryService.getOneById(id);
    }

    // Delete the comment from ID
    @ApiOperation("Delete the comment")
    @ApiImplicitParam(name = "id", value = "Comment ID", required = true, dataType = "Long")
    @DeleteMapping("/comment/article/{id}")
    public String deleteArticleComment(@PathVariable Long id) {
        commentService.deleteArticleCommentById(id);
        return null;
    }

    //Delete the message
    @ApiOperation("Delete the message")
    @ApiImplicitParam(name = "id", value = "Message ID", required = true, dataType = "Long")
    @DeleteMapping("/comment/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteCommentById(id);
        return null;
    }

    // Reply to comment or message
    @ApiOperation("Reply to comment or message")
    @ApiImplicitParam(name = "id", value = "Comment/Message ID", required = true, dataType = "Long")
    @GetMapping("/comment/reply/{id}")
    public String replyComment(@PathVariable Long id) {
        return null;
    }

    // Get the comment/message
    @ApiOperation("Get the comment/message")
    @ApiImplicitParam(name = "id", value = "Comment/Message ID", required = true, dataType = "Long")
    @GetMapping("/comment/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return null;
    }


    // Get a article(md format)
    @ApiOperation("Get a article(md format)")
    @ApiImplicitParam(name = "id", value = "Article ID", required = true, dataType = "Long")
    @GetMapping("/article/{id}")
    public ArticleDto getArticleDtoById(@PathVariable Long id) {
        return articleService.getOneById(id);
    }
}
