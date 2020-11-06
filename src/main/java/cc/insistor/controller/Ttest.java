package cc.insistor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cc")
public class Ttest {
    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "cc";
    }
}
