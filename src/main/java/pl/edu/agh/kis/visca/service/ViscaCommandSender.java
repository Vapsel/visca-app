package pl.edu.agh.kis.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.edu.agh.kis.visca.service.ViscaResponseReader.TimeoutException;
import pl.edu.agh.kis.visca.service.commands.*;

import javax.annotation.PostConstruct;

@Service
public class ViscaCommandSender {

    private final Logger logger = LoggerFactory.getLogger(ViscaCommandSender.class);

    @Getter
    private SerialPort serialPort;

    @PostConstruct
    private void postConstruct(){

        String com = "COM3";
        try {
            serialPort = new SerialPort(com);
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (SerialPortException e) {
            logger.error("Problem to open serial port " + com, e);
        }

    }

    private static void sleep(int timeSec) {
        try {
            Thread.sleep((long)(timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

    private static void sendClearAll(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new ClearAllCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 8;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    public String sendPanTiltHome() throws SerialPortException, TimeoutException {
        byte[] cmdData = (new PanTiltHomeCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    public String sendPanTiltLeft(byte speed) throws SerialPortException, TimeoutException {
        byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
        cmdData[3] = speed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    private static void sendPanTiltLeft2(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 2;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    public String sendPanTiltRight(byte speed) throws SerialPortException, TimeoutException {
        byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
        cmdData[3] = speed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    private static void sendPanTiltRight2(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 2;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    public String sendPanTiltUp(byte speed) throws SerialPortException, TimeoutException {
        byte[] cmdData = (new PanTiltUpCmd()).createCommandData();
        cmdData[4] = speed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    public String sendPanTiltDown(byte speed) throws SerialPortException, TimeoutException {
        byte[] cmdData = (new PanTiltDownCmd()).createCommandData();
        cmdData[4] = speed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    private static void sendPanTiltAbsolutePos(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltAbsolutePosCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    private static void sendAddress(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new AddressCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 8;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    public Integer sendGetPanTiltMaxSpeed() throws SerialPortException, TimeoutException {
        byte[] cmdData = (new GetPanTiltMaxSpeedCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return Integer.valueOf(retrieveResponse());
    }

    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        byte[] var5 = bytes;
        int var4 = bytes.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            byte b = var5[var3];
            sb.append(String.format("%02X ", b));
        }

        return sb.toString();
    }

    public String sendZoomTeleStd(byte speed) throws SerialPortException, TimeoutException {
        byte[] cmdData = (new ZoomTeleStdCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        cmdData[3] = speed;
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    public String sendZoomWideStd(byte speed) throws SerialPortException, TimeoutException {
        byte[] cmdData = (new ZoomWideStdCmd()).createCommandData();
        cmdData[3] = speed;
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    private String retrieveResponse() throws TimeoutException, SerialPortException {
        sleep(1);
        byte[] response = ViscaResponseReader.readResponse(serialPort);
        if (response[1] == 81){
            return "Error";
        } else {
            return "Ok";
        }
    }
}
