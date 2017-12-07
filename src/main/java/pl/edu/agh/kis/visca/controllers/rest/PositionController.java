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
import pl.edu.agh.kis.visca.models.PositionDirection;
import pl.edu.agh.kis.visca.service.ViscaCommandSender;
import pl.edu.agh.kis.visca.service.ViscaResponseReader;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    private final Logger logger = LoggerFactory.getLogger(PositionController.class);

    private final ViscaCommandSender viscaCommandSender;

    @Autowired
    public PositionController(ViscaCommandSender viscaCommandSender) {
        this.viscaCommandSender = viscaCommandSender;
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changePosition(@RequestParam String direction, @RequestParam byte speed)
        throws SerialPortException, ViscaResponseReader.TimeoutException {

        PositionDirection positionDirection = PositionDirection.valueOf(direction);
        String response = null;
        switch (positionDirection) {
            case UP: response = viscaCommandSender.sendPanTiltUp(speed); break;
            case DOWN: response = viscaCommandSender.sendPanTiltDown(speed); break;
            case LEFT: response = viscaCommandSender.sendPanTiltLeft(speed); break;
            case RIGHT: response = viscaCommandSender.sendPanTiltRight(speed); break;
        }

        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
