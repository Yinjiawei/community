package happiness.jason.community.controller;

import happiness.jason.community.dto.CommentCreateDTO;
import happiness.jason.community.dto.CommentDTO;
import happiness.jason.community.dto.QuestionDTO;
import happiness.jason.community.model.Comment;
import happiness.jason.community.service.CommentService;
import happiness.jason.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String Question(@PathVariable(name = "id") long id,
                           Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        List<CommentDTO> comments = commentService.listByQuestionId(id);

        questionService.increaseViewCount(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comments", comments);

        return "question";
    }

}
