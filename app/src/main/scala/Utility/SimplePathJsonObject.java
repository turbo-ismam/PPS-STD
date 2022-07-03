package Utility;

/**
 * This class is used by Gson library to serialize and deserialize json strings.
 */
public class SimplePathJsonObject {

    public String[][] map;

    public SimplePathJsonObject() {

    }

    public SimplePathJsonObject(String[][] map) {
        this.map = map;
    }
}
