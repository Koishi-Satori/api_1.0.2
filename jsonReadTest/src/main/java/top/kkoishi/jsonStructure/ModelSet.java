package top.kkoishi.jsonStructure;

import java.util.Arrays;

public class ModelSet implements JsonStructure {
    private String name;
    private JsonStructure[] list;

    public ModelSet () {
    }

    public ModelSet (String name, JsonStructure[] list) {
        this.name = name;
        this.list = list;
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public String getValueStr () {
        return Arrays.toString(list);
    }

    @Override
    public String toString () {
        return "ModelSet{" +
                "name='" + name + '\'' +
                ", list=" + Arrays.toString(list) +
                '}';
    }
}
