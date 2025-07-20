package persistence;

import org.json.JSONObject;

// for objects to piggyback on and based on CPSC210 JSONSerializationDemo
public interface Writable {
    // EFFECTS: returns self as something JSON writable
    JSONObject toJson();
}