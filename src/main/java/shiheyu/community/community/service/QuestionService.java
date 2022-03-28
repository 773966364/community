package shiheyu.community.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shiheyu.community.community.dto.QuestionDTO;
import shiheyu.community.community.mapper.QuestionMapper;
import shiheyu.community.community.mapper.UserMapper;
import shiheyu.community.community.model.Question;
import shiheyu.community.community.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: shiheyu
 * @Date: 2022/3/27 23:10
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> list() {
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;

    }
}
