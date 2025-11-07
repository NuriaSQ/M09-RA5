package iticbcn.xifratge;

import java.util.Arrays;

public class TextXifrat {

    private byte[] bytes;

    public TextXifrat(byte[] bytes) {
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }
}
