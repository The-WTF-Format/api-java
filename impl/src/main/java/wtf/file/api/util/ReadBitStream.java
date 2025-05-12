package wtf.file.api.util;

import org.apache.commons.lang3.ArrayUtils;
import wtf.file.api.exception.WtfException;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ReadBitStream {

    private final byte[] bytes;
    private int byteIndex = 0;
    private int bitIndex = 0;

    public ReadBitStream(byte[] bytes) {
        this.bytes = bytes;
    }

    private byte[] accessBits(
            int bits,
            Supplier<Integer> byteIndexSupplier, Supplier<Integer> bitIndexSupplier,
            Consumer<Integer> byteIndexSetter, Consumer<Integer> bitIndexSetter
    ) throws WtfException {
        if (bits > getRemainingBits()) {
            throw new WtfException(String.format("Bit stream has only %d bits reaming, tried to read %d bits", getRemainingBits(), bits));
        }

        List<Byte> resultBytes = new ArrayList<>(bits / 8 + (bits % 8 == 0 ? 0 : 1)); // Initialize the list with an estimated size
        int bitsRemaining = bits;

        byte currentByte = 0;
        int currentBitIndex = 0;

        while (bitsRemaining > 0) {
            // Extract the byte at the current position
            byte extractFromByte = bytes[byteIndexSupplier.get()];

            // Calculate how many bits are left in the current byte and remaining bits in the byte
            int remainingBitsInByte = 8 - bitIndexSupplier.get();
            int remainingBitsForByte = 8 - currentBitIndex;

            // Determine how many bits to extract from this byte
            int bitsToExtract = Math.min(Math.min(bitsRemaining, remainingBitsInByte), remainingBitsForByte);

            // Extract the bits from the current byte
            for (int i = 0; i < bitsToExtract; i++) {
                byte bit = (byte) ((extractFromByte >> (remainingBitsInByte - i - 1)) & 1);

                // Pack the extracted bit into the current byte at the correct position
                currentByte |= (byte) (bit << (7 - currentBitIndex));
                currentBitIndex++;
            }

            // Update bitIndex and byteIndex after extracting the bits
            bitIndexSetter.accept(bitIndex + bitsToExtract);
            if (bitIndex == 8) {
                bitIndex = 0;
                byteIndexSetter.accept(byteIndex + 1);
            }

            // If we have packed 8 bits, add the byte to the result list
            if (currentBitIndex == 8) {
                resultBytes.add(currentByte);
                currentByte = 0; // Reset current byte for the next byte
                currentBitIndex = 0; // Reset the index for the next byte
            }

            // Decrease the remaining bits to extract
            bitsRemaining -= bitsToExtract;
        }

        if (bits % 8 != 0) {
            currentByte >>= 1;
            currentByte &= 0b01111111;
            currentByte >>= 8 - (bits % 8) - 1;

            resultBytes.add(currentByte);
        }

        return ArrayUtils.toPrimitive(resultBytes.toArray(new Byte[0]));

    }

    public byte[] readBits(int bits) throws WtfException {
        return accessBits(
                bits,
                () -> byteIndex, () -> bitIndex,
                i -> byteIndex = i, i -> bitIndex = i
        );
    }

    public byte[] readBytes(int bytes) throws WtfException {
        return readBits(bytes * 8);
    }

    public byte readByte() throws WtfException {
        return readBytes(1)[0];
    }

    public boolean readFlag() throws WtfException {
        return readBits(1)[0] != 0;
    }

    public byte[] peekBits(int bits) throws WtfException {
        AtomicInteger byteIndex = new AtomicInteger(this.byteIndex);
        AtomicInteger bitIndex = new AtomicInteger(this.bitIndex);

        return accessBits(
                bits,
                byteIndex::get, bitIndex::get,
                byteIndex::set, bitIndex::set
        );
    }

    public byte[] peekBytes(int bytes) throws WtfException {
        return peekBits(bytes * 8);
    }

    public String readAscii() throws WtfException {
        StringBuilder builder = new StringBuilder();
        char read;
        while ((read = (char) readBits(7)[0]) != 0) {
            builder.append(read);
        }

        return builder.toString();
    }

    public String readUtf8() throws WtfException {
        byte read;
        List<Byte> bytes = new ArrayList<>();
        while ((read = readBits(1)[0]) != 0) {
            bytes.add(read);
        }

        return new String(ArrayUtils.toPrimitive(bytes.toArray(new Byte[0])), StandardCharsets.UTF_8);
    }

    public void skip(int amount) {
        bitIndex += amount;
        byteIndex += amount / 8;
        bitIndex %= 8;
    }

    public void skipBytePadding() {
        skip(8 - bitIndex);
    }

    public boolean hasRemaining(int bits) {
        return getRemainingBits() >= bits;
    }

    public long getRemainingBits() {
        return (long) (bytes.length - byteIndex) * 8 - bitIndex;
    }

    public long readNumber(int bits) throws WtfException {
        if (bits > 63 || bits < 1) {
            throw new IllegalArgumentException("Can only read numbers between 1 and 63 bits long, got " + bits + "");
        }

        byte[] bytes = readBits(bits);
        long result = 0;

        // First, process full bytes (8 bits per byte)
        int fullBytes = bits / 8;
        for (int i = 0; i < fullBytes; i++) {
            result |= (long) (bytes[i] & 0xFF) << (8 * (fullBytes - i - 1));
        }

        // If there are leftover bits in the last byte
        int remainingBits = bits % 8;
        if (remainingBits > 0) {
            byte lastByte = bytes[fullBytes];
            for (int i = 0; i < remainingBits; i++) {
                result |= (long) ((lastByte >> i) & 1) << (bits - remainingBits + i);
            }
        }

        return result;
    }

}
