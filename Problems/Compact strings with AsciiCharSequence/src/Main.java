import java.util.*;

class AsciiCharSequence implements CharSequence {
    private byte[] bytes;

    public AsciiCharSequence(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        /*
        byte[] subByteSequence = new byte[end - start];
        for (int i = start, j = 0; i < end; i++, j++) {
            subByteSequence[j] = bytes[i];
        }*/
        byte[] subByteSequence = Arrays.copyOfRange(bytes, start, end);
        return new AsciiCharSequence(subByteSequence);
    }

    @Override
    public String toString() {
        return new String(bytes);
    }
}