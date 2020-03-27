package happiness.jason.community.dto;

import happiness.jason.community.model.User;
import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long parent;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Long commentCount;
    private User user;
}
