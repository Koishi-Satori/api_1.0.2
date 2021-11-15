package top.kkoishi.tool;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Provide a simple json reader,based on read char by char.
 * @author KKoishi
 * @since java7
 */
public class JsonReader{
    /**
     * Formally read a json file.
     * @param path the path of the json file.
     * @return the formatted result,an object
     * @throws IOException when read failed.
     */
    @SuppressWarnings("unchecked")
    public Object readJson (String path) throws IOException {
        String jsonString = read(path);
        StringBuilder builder = new StringBuilder();
        char[] cs = jsonString.toCharArray();
        Stack<Map> maps = new Stack<>();
        Stack<List> lists = new Stack<>();
        Stack<Boolean> isLists = new Stack<>();
        Stack<String> keys = new Stack<>();

        String keyTemp = null;
        Object valueTemp = null;

        for (char c : cs) {
            switch (c) {
                case ('{') : {
                    maps.push(new HashMap());
                    isLists.push(false);
                    break;
                }
                case (':') : {
                    keys.push(builder.toString());
                    builder = new StringBuilder();
                    break;
                }
                case ('[') : {
                    lists.push(new ArrayList());
                    isLists.push(true);
                    break;
                }
                case ',': {
                    if (builder.length() > 0) {
                        valueTemp = builder.toString();
                    }
                    builder = new StringBuilder();
                    boolean isList = isLists.peek();
                    if (!isList) {
                        keyTemp = keys.pop();
                        maps.peek().put(keyTemp, valueTemp);
                    } else {
                        lists.peek().add(valueTemp);
                    }
                    break;
                }
                case (']') : {

                }
                case '}':
                    isLists.pop();
                    keyTemp = keys.pop();

                    if (builder.length() > 0) {
                        valueTemp = builder.toString();
                    }

                    builder = new StringBuilder();
                    maps.peek().put(keyTemp, valueTemp);
                    valueTemp = maps.pop();
                    break;
                default : {
                    builder.append(c);
                    break;
                }
            }
        }
        return valueTemp;
    }

    /**
     * Directly get the content of a json file.
     * @param path the json file path
     * @return a long string
     * @throws FileNotFoundException if the json file does not exist
     */
    public String getContent (String path) throws FileNotFoundException {
        try {
            return read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new FileNotFoundException();
    }

    private @NotNull String read(String path) throws IOException {
        if (isExist(path)) {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            char[] chars = new char[1024];
            int len;
            while((len = reader.read(chars)) != -1) {
                builder.append(new String(chars,0,len));
            }
            return builder.toString();
        }
        throw new FileNotFoundException();
    }

    private boolean isExist (String path) {
        return new File(path).exists();
    }
}
