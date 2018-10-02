package com.duan.blogos.api.blog;

import com.duan.blogos.annonation.TokenNotRequired;
import com.duan.blogos.api.BaseCheckController;
import com.duan.blogos.service.dto.blog.BlogCommentDTO;
import com.duan.blogos.service.restful.PageResult;
import com.duan.blogos.service.restful.ResultModel;
import com.duan.blogos.service.service.blog.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created on 2018/2/4.
 *
 * @author DuanJiaNing
 */
@TokenNotRequired
@RestController
@RequestMapping("/blog/{blogId}/comment")
public class CommentController extends BaseCheckController {

    @Autowired
    private CommentService commentService;

    /**
     * 获得博文评论列表
     */
    @GetMapping
    public ResultModel<PageResult<BlogCommentDTO>> get(@PathVariable Long blogId,
                                                       @RequestParam(required = false) Integer pageSize,
                                                       @RequestParam(required = false) Integer pageNum) {
        handleBlogExistCheck(blogId);

        ResultModel<PageResult<BlogCommentDTO>> resultModel = commentService.listBlogComment(blogId, pageSize, pageNum);
        if (resultModel == null) handlerEmptyResult();

        return resultModel;
    }

}