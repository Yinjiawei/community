package happiness.jason.community.mapper;

import happiness.jason.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int increaseViewCount(Question record);
    int increaseCommentCount(Question record);
    List<Question> selectRelated(Question record);
}
