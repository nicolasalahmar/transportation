import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;


public class ReadJson {

    public void read() {
        JSONParser parser = new JSONParser();
        ArrayList<Map<String, Edge>> maps = new ArrayList<>();
        try {
            Object obj = parser.parse(new FileReader("text.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject stations = (JSONObject) jsonObject.get("stations");
            JSONObject edges = (JSONObject) jsonObject.get("edges");
            Set keySet1 = edges.keySet();
            Object[] start_points = keySet1.toArray();

            for (Object key : stations.keySet()) {
                String key_name = key.toString();
                JSONArray value = (JSONArray) stations.get(key);
                Double[] array = new Double[value.size()];
                for (int i = 0; i < value.size(); ++i) {
                    array[i] = ((Number) value.get(i)).doubleValue();
                }
                State.stations.put(key_name, array);
            }
            for (Object value : edges.values()) {
                JSONObject json_value = (JSONObject) value;
                Set keySet = json_value.keySet();
                Object[] end_points = keySet.toArray();
                Collection edges_values = json_value.values();
                Object[] edges_array = new Object[edges_values.size()];
                int j = 0;
                for (Object edge : edges_values) {

                    JSONArray edge_array = (JSONArray) edge;
                    Edge edge_obj = new Edge(
                            ((Number) edge_array.get(0)).floatValue(),
                            (Boolean) edge_array.get(1),
                            (Boolean) edge_array.get(2),
                            ((Number) edge_array.get(3)).floatValue(),
                            ((Number) edge_array.get(4)).floatValue(),
                            (String) edge_array.get(5)
                    );
                    edges_array[j] = edge_obj;
                    j++;
                }
                Map<String, Edge> inside_map = new HashMap<>();
                for (int i = 0; i < edges_array.length; i++) {
                    inside_map.put((String) end_points[i], (Edge) edges_array[i]);

                }
                maps.add(inside_map);
            }

            for (int i = 0; i < start_points.length; i++) {
                State.edges.put((String) start_points[i], maps.get(i));
            }
//            for (Object s:State.edges.entrySet()) {
//                System.out.println(((JSONObject)s).keySet());
//                System.out.println(((JSONObject)s).keySet());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}