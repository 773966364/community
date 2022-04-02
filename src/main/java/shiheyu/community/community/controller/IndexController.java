package shiheyu.community.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shiheyu.community.community.dto.PaginationDTO;
import shiheyu.community.community.service.QuestionService;

/**
 * @Author: shiheyu
 * @Date: 2022/3/19 22:36
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "5") Integer size){


        PaginationDTO pagination = questionService.list(page,size);
        model.addAttribute("pagination",pagination);
        return "index";
    }
}
