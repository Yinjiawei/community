package happiness.jason.community.controller;

import happiness.jason.community.dto.NotificationDTO;
import happiness.jason.community.enums.NotificationTypeEnum;
import happiness.jason.community.model.User;
import happiness.jason.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);

        if(NotificationTypeEnum.REPLY_QUESTION.equals(notificationDTO.getType())
        || NotificationTypeEnum.REPLY_COMMENT.equals(notificationDTO.getType())){
            return "redirect:/question/" + notificationDTO.getOuterid();
        }else{
            return "redirect:/";
        }

    }
}
