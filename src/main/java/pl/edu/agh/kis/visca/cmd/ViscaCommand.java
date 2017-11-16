//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public class ViscaCommand {
    public byte sourceAdr;
    public byte destinationAdr;
    public byte[] commandData;

    public ViscaCommand() {
    }

    public byte[] getCommandData() {
        int cmdLen = this.commandData.length + 1 + 1;
        byte[] cmdData = new byte[cmdLen];
        byte head = (byte)(128 | (this.sourceAdr & 7) << 4 | this.destinationAdr & 15);
        byte tail = -1;
        System.arraycopy(this.commandData, 0, cmdData, 1, this.commandData.length);
        cmdData[0] = head;
        cmdData[cmdData.length - 1] = tail;
        return cmdData;
    }
}
