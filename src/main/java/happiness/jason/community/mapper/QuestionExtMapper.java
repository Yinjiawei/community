package happiness.jason.community.mapper;

import happiness.jason.community.dto.QuestionQueryDTO;
import happiness.jason.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int increaseViewCount(Question record);
    int increaseCommentCount(Question record);
    List<Question> selectRelated(Question record);
    int countBySearch(QuestionQueryDTO questionQueryDTO);
    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
