package happiness.jason.community.mapper;

import happiness.jason.community.model.Comment;
import happiness.jason.community.model.CommentExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface CommentExtMapper {
    int increaseCommentCount(Comment comment);
}