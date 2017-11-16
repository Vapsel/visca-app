//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class ClearAllCmd extends Cmd {
    private static final byte[] clearAllCommandData = new byte[]{1, 0, 1};

    public ClearAllCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(clearAllCommandData);
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
