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
import pl.edu.agh.kis.visca.models.Zoom;
import pl.edu.agh.kis.visca.service.ViscaCommandSender;
import pl.edu.agh.kis.visca.service.ViscaResponseReader;

@RestController
@RequestMapping("/api/zoom")
public class ZoomController {

    private final Logger logger = LoggerFactory.getLogger(ZoomController.class);

    private final ViscaCommandSender viscaCommandSender;

    @Autowired
    public ZoomController(ViscaCommandSender viscaCommandSender) {
        this.viscaCommandSender = viscaCommandSender;
    }

    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity changeZoom(@RequestParam String zoom, @RequestParam byte speed)
        throws SerialPortException, ViscaResponseReader.TimeoutException {

        Zoom zoom1 = Zoom.valueOf(zoom);
        String response = null;
        switch (zoom1) {
            case WIDE: response = viscaCommandSender.sendZoomWideStd(speed); break;
            case TELE: response = viscaCommandSender.sendZoomTeleStd(speed); break;
        }
        logger.debug("Response: " + response);
        return ResponseEntity.ok(response);
    }
}
