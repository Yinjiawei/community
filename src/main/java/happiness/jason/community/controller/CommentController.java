package happiness.jason.community.controller;

import com.sun.deploy.net.HttpRequest;
import happiness.jason.community.dto.CommentDTO;
import happiness.jason.community.dto.ResultDTO;
import happiness.jason.community.exception.CustomizeErrorCode;
import happiness.jason.community.mapper.CommentMapper;
import happiness.jason.community.model.Comment;
import happiness.jason.community.model.User;
import happiness.jason.community.service.CommentService;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setCommentator(user.getId());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount((long) 0);
        commentService.insert(comment);

        return ResultDTO.okOf();
    }
}
