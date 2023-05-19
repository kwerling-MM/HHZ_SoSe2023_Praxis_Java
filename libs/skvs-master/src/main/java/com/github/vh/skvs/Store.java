package com.github.vh.skvs;

import org.lmdbjava.*;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Store {

    private static final Long MAP_SIZE = 10L * 1024 * 1024 * 1024;

    private Env<ByteBuffer> env;

    private Dbi<ByteBuffer> db;

    Store(Path path, String name) {
        env = Env.create()
                .setMapSize(MAP_SIZE)
                .setMaxDbs(1)
                .setMaxReaders(64)
                .open(path.toFile());

        db = env.openDbi(name, DbiFlags.MDB_CREATE);
    }

    public boolean remove(String key) {
        return db.delete(ByteBufferUtils.fromString(key));
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public void put(String key, ByteBuffer value) {
        db.put(ByteBufferUtils.fromString(key), value);
    }

    public <T extends Serializable> void put(String key, T value) {
        db.put(ByteBufferUtils.fromString(key), ByteBufferUtils.from(value));
    }

    public ByteBuffer get(String key) {
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            return db.get(txn, ByteBufferUtils.fromString(key));
        }
    }

    public Integer getInt(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toInt).orElse(null);
    }

    public Long getLong(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toLong).orElse(null);
    }

    public Float getFloat(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toFloat).orElse(null);
    }

    public Double getDouble(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toDouble).orElse(null);
    }

    public Boolean getBoolean(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toBoolean).orElse(null);
    }

    public byte[] getBytes(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toBytes).orElse(null);
    }

    public String getString(String key) {
        return Optional.ofNullable(get(key)).map(ByteBufferUtils::toString).orElse(null);
    }

    public <T extends Serializable> T get(String key, Class<T> type) {
        return Optional.ofNullable(get(key)).map(val -> ByteBufferUtils.to(val, type)).orElse(null);
    }

    private static ByteBuffer prefixEnd(String prefix) {
        return ByteBufferUtils.fromString(prefix.substring(0, prefix.length() - 1) + (char)(prefix.charAt(prefix.length() - 1) + 1));
    }

    public List<Map.Entry<String, ByteBuffer>> enumerate(String prefix) {
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            return StreamSupport.stream(db.iterate(txn,
                    KeyRange.open(ByteBufferUtils.fromString(prefix), prefixEnd(prefix))).spliterator(), false)
                    .map(kv -> new AbstractMap.SimpleImmutableEntry<>(ByteBufferUtils.toString(kv.key()), kv.val().duplicate()))
                    .collect(Collectors.toList());
        }
    }

    public List<String> enumerateKeys(String prefix) {
        try (Txn<ByteBuffer> txn = env.txnRead()) {
            return StreamSupport.stream(db.iterate(txn,
                    KeyRange.open(ByteBufferUtils.fromString(prefix), prefixEnd(prefix))).spliterator(), false)
                    .map(kv -> ByteBufferUtils.toString(kv.key()))
                    .collect(Collectors.toList());
        }
    }

    public <T extends Serializable> List<Map.Entry<String, T>> enumerate(String prefix, Class<T> type) {
         return enumerate(prefix).stream().map(pair -> new AbstractMap.SimpleImmutableEntry<>(
                pair.getKey(), ByteBufferUtils.to(pair.getValue(), type))).collect(Collectors.toList());
    }
}
