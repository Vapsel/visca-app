package pl.edu.agh.kis.visca.controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class MainPageViewController {

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(){
        return "mainPage";
    }
}
