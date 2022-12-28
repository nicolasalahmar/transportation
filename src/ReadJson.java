import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class ReadJson {

    public void read() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("text.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject stations = (JSONObject) jsonObject.get("stations");
            for (Object key : stations.keySet()) {
                String key_name= key.toString();
                JSONArray value= (JSONArray) stations.get(key);
                Double[] array = new Double[value.size()];
                for (int i = 0; i < value.size(); ++i) {
                    array[i] = (double) value.get(i);
                }
                State.stations.put(key_name,array);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}