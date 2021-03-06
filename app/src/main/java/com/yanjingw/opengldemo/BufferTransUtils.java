package com.yanjingw.opengldemo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

public abstract class BufferTransUtils {

    public static final float PI = 3.14159265358979323846f;


    static ByteBuffer getByteBufferFromByteArray(byte array[]) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
        buffer.put(array);
        buffer.position(0);
        return buffer;
    }
    static ShortBuffer getShortBufferFromShortArray(short array[]) {
        ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length *2 );
        tempBuffer.order(ByteOrder.nativeOrder());
        ShortBuffer buffer = tempBuffer.asShortBuffer();
        buffer.put(array);
        buffer.position(0);
        return buffer;
    }

    static FloatBuffer getFloatBufferFromFloatArray(float array[]) {
        ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
        tempBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = tempBuffer.asFloatBuffer();
        buffer.put(array);
        buffer.position(0);
        return buffer;
    }

    IntBuffer getIntBufferFromIntArray(int array[]) {
        ByteBuffer tempBuffer = ByteBuffer.allocateDirect(array.length * 4);
        tempBuffer.order(ByteOrder.nativeOrder());
        IntBuffer buffer = tempBuffer.asIntBuffer();
        buffer.put(array);
        buffer.position(0);
        return buffer;
    }


}
