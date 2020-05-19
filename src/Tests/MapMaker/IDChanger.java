package Tests.MapMaker;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map.Entry;

public class IDChanger {
    public HashMap<String, Short> idToShort = new HashMap<>();
    public HashMap<Short,  String> shortToId = new HashMap<>();

    public IDChanger() {
        idToShort.put("test.null", (short)0);
        idToShort.put("test.rock", (short)1);
        idToShort.put("test.grass", (short)2);
        idToShort.put("test.boulder", (short)3);
        idToShort.put("test.brick", (short)4);
        idToShort.put("test.wood", (short)5);

        for (Entry<String, Short> pair : idToShort.entrySet()) {
            shortToId.put(pair.getValue(), pair.getKey());
        }
    }
}
