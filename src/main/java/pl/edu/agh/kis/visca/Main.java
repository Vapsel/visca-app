//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.kis.visca.ViscaResponseReader.TimeoutException;
import pl.edu.agh.kis.visca.cmd.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public Main() {
    }

    public static void runUserCommands(String userInput, SerialPort serialPort) {
        String[] tokens = userInput.split(";");
        byte[] response;

        try {
            for (String token : tokens) {
                if (token.startsWith("Wait ")) {
                    String firstNumber = token.replaceAll("^\\D*(\\d+).*", "$1");
                    int sleepFor = Integer.parseInt(firstNumber);
                    Thread.sleep((long) (sleepFor));
                    continue;
                } else if (token.startsWith("ClearAll")) {
                     sendClearAll(serialPort);
//                    System.out.print("sendClearAll(serialPort);");
                } else if (token.startsWith("PanTiltHome")) {
                     sendPanTiltHome(serialPort);
//                    System.out.print("sendPanTiltHome(serialPort);");
                } else if (token.startsWith("PanTiltLeft")) {
                     sendPanTiltLeft(serialPort);
//                    System.out.print("sendPanTiltLeft(serialPort);");
                } else if (token.startsWith("PanTiltLeft2")) {
                     sendPanTiltLeft2(serialPort);
//                    System.out.print("sendPanTiltLeft2(serialPort);");
                } else if (token.startsWith("PanTiltRight")) {
                     sendPanTiltRight(serialPort);
//                    System.out.print("sendPanTiltRight(serialPort);");
                } else if (token.startsWith("PanTiltRight2")) {
                     sendPanTiltRight2(serialPort);
//                    System.out.print("sendPanTiltRight2(serialPort);");
                } else if (token.startsWith("PanTiltUp")) {
                     sendPanTiltUp(serialPort);
//                    System.out.print("sendPanTiltUp(serialPort);");
                } else if (token.startsWith("PanTiltDown")) {
                     sendPanTiltDown(serialPort);
//                    System.out.print("sendPanTiltDown(serialPort);");
                } else if (token.startsWith("PanTiltAbsolutePos")) {
                     sendPanTiltAbsolutePos(serialPort);
//                    System.out.print("sendPanTiltAbsolutePos(serialPort);");
                } else if (token.startsWith("Address")) {
                    sendAddress(serialPort);
                    System.out.print("sendAddress(serialPort);");
                } else if (token.startsWith("GetPanTiltMaxSpeed")) {
                    sendGetPanTiltMaxSpeed(serialPort);
                } else if (token.startsWith("ZoomTeleStd")) {
                    sendZoomTeleStd(serialPort);
                } else if (token.startsWith("ZoomWideStd")) {
                    sendZoomWideStd(serialPort);
                }

                try {
                    response = ViscaResponseReader.readResponse(serialPort);
                    System.out.println("> " + byteArrayToString(response));
                } catch (TimeoutException var11) {
                    System.out.println("! TIMEOUT exception");
                }
            }
        } catch (SerialPortException var18) {
            System.out.println(var18);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> macros = new HashMap<>();


    public static void main(String[] args) throws SerialPortException {
        String commName = args[0];
        SerialPort serialPort = new SerialPort(commName);

        serialPort.openPort();
        serialPort.setParams(9600, 8, 1, 0);

        while (true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter commands: ");

            try {
                String commands = reader.readLine();
                String optionalMacro = macros.get(commands);

                if (commands.startsWith("macro:")) {
                    String macroTail = commands.substring(6);
                    String macroName = macroTail.substring(macroTail.indexOf("(") + 1, macroTail.indexOf(")"));
                    String macro = macroTail.substring(macroTail.indexOf(")") + 1, macroTail.length());

                    macros.put(macroName, macro);
                } else if (optionalMacro != null) {
                    runUserCommands(optionalMacro, serialPort);
                } else {
                    runUserCommands(commands, serialPort);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void sleep(int timeSec) {
        try {
            Thread.sleep((long) (timeSec * 1000));
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

    private static void sendPanTiltUp(SerialPort serialPort) throws SerialPortException {
        byte[] cmdData = (new PanTiltUpCmd()).createCommandData();
        ViscaCommand vCmd = new ViscaCommand();
        vCmd.commandData = cmdData;
        vCmd.sourceAdr = 0;
        vCmd.destinationAdr = 1;
        cmdData = vCmd.getCommandData();
        System.out.println("@ " + byteArrayToString(cmdData));
        serialPort.writeBytes(cmdData);
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

        for (int var3 = 0; var3 < var4; ++var3) {
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
}






