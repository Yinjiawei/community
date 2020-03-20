package happiness.jason.community.dto;

import happiness.jason.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long Creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private User user;
}
