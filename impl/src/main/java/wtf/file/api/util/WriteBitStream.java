package wtf.file.api.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WriteBitStream {

    private final List<Byte> resultBytes = new ArrayList<>();

    byte currentByte = 0;
    int currentBitIndex = 0;

    public void write(byte[] bytes, int bits) {
        if (bytes.length * 8 < bits) {
            throw new IllegalStateException("Cannot write more bits than available");
        }

        int bitsRemaining = bits;

        int byteIndex = 0;
        int bitIndex = 0;

        while (bitsRemaining > 0) {
            byte extractFromByte = bytes[byteIndex];
            if (byteIndex == bytes.length - 1 && bits % 8 != 0) {
                extractFromByte <<= 8 - bits % 8;
            }

            int remainingBitsInByte = 8 - bitIndex;
            int remainingBitsForByte = 8 - currentBitIndex;

            int bitsToExtract = Math.min(Math.min(bitsRemaining, remainingBitsInByte), remainingBitsForByte);

            for (int i = 0; i < bitsToExtract; i++) {
                byte bit = (byte) ((extractFromByte >> (remainingBitsInByte - i - 1)) & 1);

                currentByte |= (byte) (bit << (7 - currentBitIndex));
                currentBitIndex++;
            }

            // Update bitIndex and byteIndex after extracting the bits
            bitIndex += bitsToExtract;
            if (bitIndex == 8) {
                bitIndex = 0;
                byteIndex++;
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
    }

    public void write(byte[] bytes) {
        write(bytes, bytes.length * 8);
    }

    public void write(byte aByte, int bits) {
        write(new byte[]{aByte}, bits);
    }

    public void write(byte aByte) {
        write(new byte[]{aByte}, 8);
    }

    public void writeAscii(String ascii) {
        for (char b : ascii.toCharArray()) {
            if (b == 0) {
                throw new IllegalStateException("Can not write null character");
            }

            if (((int) b) > 127) {
                throw new IllegalStateException("Can not write non-ascii character");
            }

            write((byte) b, 7);
        }

        write((byte) 0, 7);
    }

    public void writeUtf8(String utf8) {
        byte[] bytes = utf8.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        for (byte aByte : bytes) {
            if (aByte == 0) {
                throw new IllegalStateException("Can not write null character");
            }
        }

        write(bytes);
        write((byte) 0);
    }

    public void writeZero(int bits) {
        write(new byte[bits / 8 + ((bits % 8 == 0 ? 0 : 1))], bits);
    }

    public void padToByte() {
        writeZero(8 - currentBitIndex);
    }

    public void writeNumber(long number, int bits) {
        if (bits > 63 || bits < 1) {
            throw new IllegalArgumentException("Can only write numbers between 1 and 63 bits long, got " + bits + "");
        }

        int numBytes = (bits + 7) / 8;  // This ensures we round up when bits are not an exact multiple of 8
        byte[] bytes = new byte[numBytes];

        // Write each byte to the array, starting from the most significant byte
        for (int i = 0; i < numBytes; i++) {
            // Extract the byte and shift it into place
            int shift = (numBytes - i - 1) * 8;
            bytes[i] = (byte) ((number >> shift) & 0xFF);
        }

        // Handle the case where the number of bits is not a multiple of 8
        if (bits % 8 != 0) {
            // Mask out the unused bits in the last byte (if any)
            int lastByteBits = bits % 8;
            bytes[numBytes - 1] &= (byte) ((1 << lastByteBits) - 1);
        }

        // Now, write the bytes to wherever you need them
        write(bytes);
    }

    public byte[] getBytes() {
        if (currentBitIndex != 0) {
            resultBytes.add(currentByte);
        }

        return ArrayUtils.toPrimitive(resultBytes.toArray(new Byte[0]));
    }

}
