package pl.edu.agh.kis.visca.service.commands;

public final class PanTiltHomeCmd extends Cmd {
    private static final byte[] ptHomeCommandData = new byte[]{1, 6, 4};

    public PanTiltHomeCmd() {
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(ptHomeCommandData);
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
