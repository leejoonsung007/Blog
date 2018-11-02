package com.springboot.blog.controller;

import com.springboot.blog.dto.*;
import com.springboot.blog.entity.*;
import com.springboot.blog.entity.CategoryInfo;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.util.Markdown2HtmlUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ForeController extends BaseController{

    //------------------------------------------article page----------------------------------------------
    // get all articles
    @ApiOperation("get all the articles")
    @GetMapping("article/list")
    public List<ArticleWithPictureDto> listAllArticleInfo() {
        return articleService.listAll();
    }

    // get all articles in a category
    @ApiOperation("get all articles in a category")
    @ApiImplicitParam(name = "id", value = "Category ID", required = true, dataType = "Long")
    @GetMapping("article/list/sort/{id}")
    public List<ArticleWithPictureDto> listArticleInfo(@PathVariable Long id) {
        return articleService.listByCategoryId(id);
    }

    // get the latest article
    @ApiOperation("get the latest article")
    @GetMapping("article/list/latest")
    public List<ArticleWithPictureDto> listLastestArticle() {
        return articleService.listLastest();
    }

    // get article from the article id
    @ApiOperation("get article from the article id")
    @GetMapping("article/{id}")
    public ArticleDto getArticleById(@PathVariable Long id) {
        ArticleDto articleDto = articleService.getOneById(id);
        articleDto.setContent(Markdown2HtmlUtil.markdown2html(articleDto.getContent()));
        return articleDto;
    }

    // get all categories
    @ApiOperation("get all categories")
    @GetMapping("category/list")
    public List<CategoryInfo> listAllCategoryInfo() {
        return categoryService.listAllCategory();
    }


    // get all comments
    @ApiOperation("get all comments")
    @GetMapping("comment/list")
    public List<Comment> listAllComment() {
        return commentService.listAllComment();
    }

    // get all comments for a article
    @ApiOperation("get all comments for a article")
    @ApiImplicitParam(name = "id", value = "Article ID", required = true, dataType = "Long")
    @GetMapping("comment/article/{id}")
    public List<ArticleCommentDto> listMessageByArticleId(@PathVariable Long id) {
        return commentService.listAllArticleCommentById(id);
    }

    // add a comment
    @ApiOperation("add a comment")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "Article ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "Comment", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "Name", required = true, dataType = "String")
    })
    @PostMapping("comment/article/{id}")
    public String addArticleComment(@PathVariable Long id, @RequestBody ArticleCommentDto articleCommentDto, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        articleCommentDto.setIp(ip);
        articleCommentDto.setArticleId(id);
        commentService.addArticleComment(articleCommentDto);

        return null;
    }

    // add a message
    @ApiOperation("add a message")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "Content", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "Email", required = false, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "String")
    })
    @PostMapping("comment")
    public String addMessage(@RequestBody Comment comment, HttpServletRequest request) {

        String ip = request.getRemoteAddr();
        comment.setIp(ip);
        commentService.addComment(comment);

        return null;
    }

    //------------------------------------------Note page----------------------------------------------
    // get all notes
    @ApiOperation("get all the notes")
    @GetMapping("note/list")
    public List<NoteDto> listAllNote() {

        return noteService.listAllNotes();
    }

    // get the latest note
    @ApiOperation("get the latest note")
    @GetMapping("note/list/latest")
    public List<NoteWithPictureDto> listLatestNote() {
        return noteService.listLatest();
    }

    // get the latest note
    @ApiOperation("get the latest note with content")
    @GetMapping("note/latest")
    public NoteDto listLatestNoteWithContent() {
        NoteDto noteDto = new NoteDto();
        if(!noteService.listAllNotes().isEmpty()){
            noteDto.setContent(Markdown2HtmlUtil.markdown2html(noteService.listAllNotes().get(0).getContent()));
            return noteDto;
        }
        return null;
    }

    // get article from the note id
    @ApiOperation("get note from the note id")
    @GetMapping("note/{id}")
    public NoteDto getNoteById(@PathVariable Long id) {
        NoteDto noteDto = noteService.getOneById(id);
        noteDto.setContent(Markdown2HtmlUtil.markdown2html(noteDto.getContent()));
        return noteDto;
    }

}
