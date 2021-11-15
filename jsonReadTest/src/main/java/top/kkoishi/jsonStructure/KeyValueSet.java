package top.kkoishi.jsonStructure;

/**
 * @author KKoishi
 */
public class KeyValueSet implements JsonStructure{
    private String name;
    private String value;

    public KeyValueSet () {
    }

    public KeyValueSet (String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName () {
        return name;
    }

    @Override
    public String getValueStr () {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }

    @Override
    public String toString () {
        return "KeyValueSet{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
