package happiness.jason.community.service;

import happiness.jason.community.dto.PaginationDTO;
import happiness.jason.community.dto.QuestionDTO;
import happiness.jason.community.mapper.QuestionMapper;
import happiness.jason.community.mapper.UserMapper;
import happiness.jason.community.model.Question;
import happiness.jason.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        if (page < 1) {
            page = 1;
        }
        int totalCount = questionMapper.count();
        int totalPage = (int) Math.ceil((double) totalCount / size);
        if (page > totalPage) {
            page = totalPage;
        }
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);

        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage, page, size);

        return paginationDTO;
    }
}
