package shiheyu.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shiheyu.community.community.dto.QuestionDTO;
import shiheyu.community.community.model.Question;
import shiheyu.community.community.model.User;
import shiheyu.community.community.service.QuestionService;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: shiheyu
 * @Date: 2022/3/25 8:15
 */
@Controller
public class PublishController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){

        QuestionDTO question = questionService.getById(id);

        model.addAttribute("title", question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @GetMapping("/publish") //渲染页面
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
         @RequestParam(value = "title",required = false) String title,
         @RequestParam(value = "description",required = false) String description,
         @RequestParam(value = "tag",required = false) String tag,
        @RequestParam(value = "id",required = false) Integer id,
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

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());

        question.setId(id);
        questionService.createOrUpdate(question);

        return "redirect:/";
    }
}
