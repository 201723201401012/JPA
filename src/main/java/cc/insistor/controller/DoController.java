package cc.insistor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/v1/cc")
public class DoController {
    @RequestMapping("/test0")
    @ResponseBody
    public String cc(){
        return "cc";
    }
}
