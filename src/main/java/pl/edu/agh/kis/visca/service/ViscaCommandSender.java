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

        String com = "COM11";
        try {
            serialPort = new SerialPort(com);
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
        } catch (SerialPortException e) {
            logger.error("Problem to open serial port " + com, e);
        }

    }

    public static void main2(String[] args) {
        String commName = args[0];
        SerialPort serialPort = new SerialPort(commName);

        try {

            System.out.println("Address");
            sendAddress(serialPort);

            byte[] response;
            try {
                response = ViscaResponseReader.readResponse(serialPort);
                System.out.println("> " + byteArrayToString(response));
            } catch (TimeoutException var17) {
                System.out.println("! TIMEOUT exception");
            }

            sleep(5);
            System.out.println("Home");
            sendPanTiltHome(serialPort);

            try {
                response = ViscaResponseReader.readResponse(serialPort);
                System.out.println("> " + byteArrayToString(response));
            } catch (TimeoutException var16) {
                System.out.println("! TIMEOUT exception");
            }

            sleep(5);
            System.out.println("Wide");
            sendZoomWideStd(serialPort);

            try {
                response = ViscaResponseReader.readResponse(serialPort);
                System.out.println("> " + byteArrayToString(response));
            } catch (TimeoutException var15) {
                System.out.println("! TIMEOUT exception");
            }

            sleep(10);
            System.out.println("Tele");
            sendZoomTeleStd(serialPort);

            try {
                response = ViscaResponseReader.readResponse(serialPort);
                System.out.println("> " + byteArrayToString(response));
            } catch (TimeoutException var14) {
                System.out.println("! TIMEOUT exception");
            }

            sleep(10);
            System.out.println("Get Pan TiltMax Speed");
            sendGetPanTiltMaxSpeed(serialPort);

            try {
                response = ViscaResponseReader.readResponse(serialPort);
                System.out.println("> " + byteArrayToString(response));
            } catch (TimeoutException var13) {
                System.out.println("! TIMEOUT exception");
            }

            sleep(10);
            System.out.println("Absolute Pos");
            sendPanTiltAbsolutePos(serialPort);

            try {
                response = ViscaResponseReader.readResponse(serialPort);
                System.out.println("> " + byteArrayToString(response));
            } catch (TimeoutException var12) {
                System.out.println("! TIMEOUT exception");
            }

            sleep(5);

            while(true) {
                System.out.println("Right");
                sendPanTiltRight(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var11) {
                    System.out.println("! TIMEOUT exception");
                }

                sendPanTiltRight2(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var10) {
                    System.out.println("! TIMEOUT exception");
                }

                sleep(24);
                System.out.println("Up");
//                sendPanTiltUp(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var9) {
                    System.out.println("! TIMEOUT exception");
                }

                sleep(8);
                System.out.println("Tele");
                sendZoomTeleStd(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var8) {
                    System.out.println("! TIMEOUT exception");
                }

                sleep(14);
                System.out.println("Left");
                sendPanTiltLeft(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var7) {
                    System.out.println("! TIMEOUT exception");
                }

                sendPanTiltLeft2(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var6) {
                    System.out.println("! TIMEOUT exception");
                }

                sleep(16);
                System.out.println("Down");
                sendPanTiltDown(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var5) {
                    System.out.println("! TIMEOUT exception");
                }

                sleep(12);
                System.out.println("Wide");
                sendZoomWideStd(serialPort);

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var4) {
                    System.out.println("! TIMEOUT exception");
                }

                sleep(10);
            }
        } catch (SerialPortException var18) {
            System.out.println(var18);
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

    private static void sendPanTiltHome(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltHomeCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    private static void sendPanTiltLeft(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltLeftCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
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

    private static void sendPanTiltRight(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltRightCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
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

    public String sendPanTiltUp() throws SerialPortException, TimeoutException {
        byte[] cmdData = (new PanTiltUpCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
        return retrieveResponse();
    }

    private static void sendPanTiltDown(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltDownCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
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

    private static void sendGetPanTiltMaxSpeed(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new GetPanTiltMaxSpeedCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
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

    private static void sendZoomTeleStd(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new ZoomTeleStdCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    private static void sendZoomWideStd(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new ZoomWideStdCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
    }

    private String retrieveResponse() throws TimeoutException, SerialPortException {
        byte[] response = ViscaResponseReader.readResponse(serialPort);
        return byteArrayToString(response);
    }
}
