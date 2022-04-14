package org.acme;

import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataObject {
    private String id;
    private String data;

    private static final Random rnd = new Random();

    public static DataObject generate(int size){
        String data = IntStream.range(0, size).mapToObj(i -> String.valueOf((char) ('A' + rnd.nextInt(26)))).collect(Collectors.joining());
        return new DataObject(UUID.randomUUID().toString(), data);
    }

    public DataObject(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
