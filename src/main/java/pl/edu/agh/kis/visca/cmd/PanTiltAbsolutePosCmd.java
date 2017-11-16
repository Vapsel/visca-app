//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class PanTiltAbsolutePosCmd extends Cmd {
    private static final byte[] ptAbsolutPosCommandData = new byte[]{1, 6, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public PanTiltAbsolutePosCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptAbsolutPosCommandData);
        cmdData[3] = 1;
        cmdData[5] = 0;
        cmdData[6] = 3;
        cmdData[7] = 7;
        cmdData[8] = 5;
        cmdData[9] = 0;
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
