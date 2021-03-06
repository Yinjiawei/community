package happiness.jason.community.controller;

import happiness.jason.community.dto.NotificationDTO;
import happiness.jason.community.dto.PaginationDTO;
import happiness.jason.community.dto.QuestionDTO;
import happiness.jason.community.model.User;
import happiness.jason.community.service.NotificationService;
import happiness.jason.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "5") Integer size,
                          Model model,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("questions".equals(action)) {
            PaginationDTO<QuestionDTO> pagination = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的问题");
        } else if ("replies".equals(action)) {
            PaginationDTO<NotificationDTO> pagination = notificationService.list(user.getId(), page, size);
            model.addAttribute("pagination", pagination);
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        return "profile";
    }
}
