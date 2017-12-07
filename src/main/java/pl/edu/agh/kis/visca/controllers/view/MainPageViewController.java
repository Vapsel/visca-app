package pl.edu.agh.kis.visca.controllers.view;

import jssc.SerialPortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.kis.visca.service.ViscaCommandSender;
import pl.edu.agh.kis.visca.service.ViscaResponseReader;

@Controller
@RequestMapping("/")
public class MainPageViewController {

    private final ViscaCommandSender viscaCommandSender;

    @Autowired
    public MainPageViewController(ViscaCommandSender viscaCommandSender) {
        this.viscaCommandSender = viscaCommandSender;
    }

    @RequestMapping(method = RequestMethod.GET)
    private String loadMainPage(Model model) throws ViscaResponseReader.TimeoutException, SerialPortException {
//        Integer maxSpeed = viscaCommandSender.sendGetPanTiltMaxSpeed();
        Integer maxSpeed = 10;
        model.addAttribute("maxSpeed", maxSpeed);
        return "mainPage";
    }
}
