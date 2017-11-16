//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class PanTiltLeftCmd extends Cmd {
    private static final byte[] ptLeftCommandData = new byte[]{1, 6, 1, 0, 0, 1, 3};

    public PanTiltLeftCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptLeftCommandData);
        cmdData[3] = 8;
        cmdData[4] = 1;
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
