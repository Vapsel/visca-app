//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class GetPanTiltMaxSpeedCmd extends Cmd {
    private static final byte[] maxSpeedCommandData = new byte[]{9, 6, 17};

    public GetPanTiltMaxSpeedCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(maxSpeedCommandData);
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
