package shiheyu.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shiheyu.community.community.mapper.QuestionMapper;
import shiheyu.community.community.mapper.UserMapper;
import shiheyu.community.community.model.Question;
import shiheyu.community.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shiheyu
 * @Date: 2022/3/25 8:15
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish") //渲染页面
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
         @RequestParam(value = "title",required = false) String title,
         @RequestParam(value = "description",required = false) String description,
         @RequestParam(value = "tag",required = false) String tag,
        HttpServletRequest request,
         Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title == null ||title==""){
                model.addAttribute("error","标题不能为空");
                return "publish";
        }
        if(description == null ||description==""){
            model.addAttribute("error","问题补充不能为空");
            return "publish";
        }
        if(tag == null ||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }

        User user = null;
            Cookie[] cookies = request.getCookies();
            if(cookies != null && cookies.length != 0){
                for (Cookie cookie : cookies) {
                 if(cookie.getName().equals("token")){
                     String token = cookie.getValue();
                     user = userMapper.findByToken(token);
                        if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
