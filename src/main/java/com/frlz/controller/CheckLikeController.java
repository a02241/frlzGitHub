package com.frlz.controller;

import com.frlz.service.BlogService;
import com.frlz.service.CheckLikeService;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * @author cz
 */
@RestControllerAdvice
@RestController
@Transactional
@Api(value="点赞controller",tags={"点赞操作接口"})
public class CheckLikeController {

    private final CheckLikeService checkLikeService;
    private final BlogService blogService;

    @Autowired
    public CheckLikeController(CheckLikeService checkLikeService,BlogService blogService){
        this.checkLikeService = checkLikeService;
        this.blogService = blogService;
    }

    
    /**
     * 点赞
     * @title clickLike
     * @create by: cz
     * @Description TODO 必填参数blogId,uid，返回未点击喜欢返回+1，已经点击过了再次点击返回-1
     * @create time: 2019/3/13 16:14
     * @param blogId
     * @param uid
     * 
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("/clickLike")
    @ApiOperation(value="点赞", notes="根据url的信息来点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogId", value = "博客识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> clickLike(String blogId, String uid){
        if (null == checkLikeService.selectFromCheckLike(blogId,uid)) {
            blogService.updateLikesCount(blogId);
            checkLikeService.insertIntoCheckLike(blogId,uid);
            return R.isOk().msg("+1");
        }else {
            checkLikeService.deleteFromCheckLike(checkLikeService.selectFromCheckLike(blogId,uid).getLikeId());
            blogService.updateReduceLikesCount(blogId);
            return R.isOk().msg("-1");
        }
    }
}
