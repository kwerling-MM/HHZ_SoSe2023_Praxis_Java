package com.github.vh.skvs;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteBufferUtils {

    private static ByteBuffer flip(ByteBuffer buf) {
        buf.flip();
        return buf;
    }

    public static ByteBuffer fromBytes(byte[] src) {
        return flip(ByteBuffer.allocateDirect(src.length).put(src));
    }

    public static ByteBuffer fromString(String src) {
        return fromBytes(src.getBytes(StandardCharsets.UTF_8));
    }

    public static ByteBuffer fromInt(int src) {
        return flip(ByteBuffer.allocateDirect(4).putInt(src));
    }

    public static ByteBuffer fromLong(long src) {
        return flip(ByteBuffer.allocateDirect(8).putLong(src));
    }

    public static ByteBuffer fromFloat(float src) {
        return flip(ByteBuffer.allocateDirect(4).putFloat(src));
    }

    public static ByteBuffer fromDouble(double src) {
        return flip(ByteBuffer.allocateDirect(8).putDouble(src));
    }

    public static ByteBuffer fromBoolean(boolean src) {
        return flip(ByteBuffer.allocateDirect(1).put((byte)(src ? 1 : 0)));
    }

    public static ByteBuffer from(Serializable src) {
        if (src instanceof Integer) {
            return fromInt((int)src);
        } else if (src instanceof Long) {
            return fromLong((long)src);
        } else if (src instanceof Float) {
            return fromFloat((float)src);
        } else if (src instanceof Double) {
            return fromDouble((double)src);
        } else if (src instanceof Boolean) {
            return fromBoolean((boolean)src);
        } else if (src instanceof String) {
            return fromString((String)src);
        } else if (src instanceof byte[]) {
            return fromBytes((byte[])src);
        } else {
            return fromBytes(SerializationUtils.serialize(src));
        }
    }

    public static byte[] toBytes(ByteBuffer buf) {
        byte[] bytes = new byte[buf.capacity()];
        buf.get(bytes);
        return bytes;
    }

    public static String toString(ByteBuffer buf) {
        return StandardCharsets.UTF_8.decode(buf).toString();
    }

    public static int toInt(ByteBuffer buf) {
        if (buf.capacity() == 4) {
            return buf.getInt();
        } else {
            throw new ClassCastException("ByteBuffer capacity != 4");
        }
    }

    public static long toLong(ByteBuffer buf) {
        if (buf.capacity() == 8) {
            return buf.getLong();
        } else {
            throw new ClassCastException("ByteBuffer capacity != 8");
        }
    }

    public static float toFloat(ByteBuffer buf) {
        if (buf.capacity() == 4) {
            return buf.getFloat();
        } else {
            throw new ClassCastException("ByteBuffer capacity != 4");
        }
    }

    public static double toDouble(ByteBuffer buf) {
        if (buf.capacity() == 8) {
            return buf.getDouble();
        } else {
            throw new ClassCastException("ByteBuffer capacity != 8");
        }
    }

    public static boolean toBoolean(ByteBuffer buf) {
        if (buf.capacity() == 1) {
            return buf.get() == 1;
        } else {
            throw new ClassCastException("ByteBuffer capacity != 1");
        }
    }

    public static <T extends Serializable> T to(ByteBuffer buf, Class<T> type) {
        if (type == Integer.class) {
            return type.cast(toInt(buf));
        } else if (type == Long.class) {
            return type.cast(toLong(buf));
        } else if (type == Float.class) {
            return type.cast(toFloat(buf));
        } else if (type == Double.class) {
            return type.cast(toDouble(buf));
        } else if (type == Boolean.class) {
            return type.cast(toBoolean(buf));
        } else if (type == String.class) {
            return type.cast(toString(buf));
        } else if (type == byte[].class) {
            return type.cast(toBytes(buf));
        } else {
            return type.cast(SerializationUtils.deserialize(toBytes(buf)));
        }
    }
}
