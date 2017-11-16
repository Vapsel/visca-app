//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class PanTiltRightCmd extends Cmd {
    private static final byte[] ptRightCommandData = new byte[]{1, 6, 1, 0, 0, 2, 3};

    public PanTiltRightCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptRightCommandData);
        cmdData[3] = 4;
        cmdData[4] = 1;
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
