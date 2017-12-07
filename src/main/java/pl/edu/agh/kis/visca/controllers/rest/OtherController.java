package pl.edu.agh.kis.visca.controllers.rest;

import jssc.SerialPortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.kis.visca.models.OtherCommands;
import pl.edu.agh.kis.visca.service.ViscaCommandSender;
import pl.edu.agh.kis.visca.service.ViscaResponseReader;

@RestController
@RequestMapping("/api/other")
public class OtherController {

    private final Logger logger = LoggerFactory.getLogger(OtherController.class);

    private final ViscaCommandSender viscaCommandSender;

    @Autowired
    public OtherController(ViscaCommandSender viscaCommandSender) {
        this.viscaCommandSender = viscaCommandSender;
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String command)
        throws SerialPortException, ViscaResponseReader.TimeoutException {

        String response = null;
        switch (OtherCommands.valueOf(command)) {
            case HOME: response = viscaCommandSender.sendPanTiltHome(); break;
        }

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
