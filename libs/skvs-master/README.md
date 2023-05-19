# SKVS

![](https://github.com/vh/skvs/workflows/Build/badge.svg)

Simple Key-Value Store. Built on top of LMDB.

## Getting Started

Using maven add the following in your `pom.xml`
```
    <dependencies>
        <dependency>
            <groupId>com.github.vh</groupId>
            <artifactId>skvs</artifactId>
            <version>1.0.2</version>
        </dependency>
    </dependencies>
```

If you are using gradle add the following in your `gradle.build`
```
    compile 'com.github.vh:skvs:1.0.2'
```

To create a store use `StoreFactory`:
```
    Store store = StoreFactory.createStore("mydb");
```

### Read value from store

```
    store.getString("key");
    store.getInt("key");
    store.getLong("key");
    store.getFloat("key");
    store.getDouble("key");
    store.getBoolean("key");
    store.getBytes("key");

    // or
    store.get("key", SerializableType.class);
```

### Save value to store

```
    store.put("key", serializableValue);
```

### Enumerations

```
    store.put("prefix:key1", 1);
    store.put("prefix:key2", 2);
    
    List<Map.Entry<String, Integer>> col = store.enumerate("prefix:", Integer.class);
```


