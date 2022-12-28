import java.util.Map;

public class Main {

    public static void main(String[] args) {
        State s = new State(null, 10000, 100, "m1", 0, "");
        //this.stations.put(station name, [bus waiting time, taxi waiting time])
//        State.stations.put("m1", new Double[]{ 4.5, 1.5});
//        State.stations.put("m2", new Double[]{ 4.5, 1.5});
//        State.stations.put("m3", new Double[]{ 4.5, 1.5});
//        State.stations.put("m4", new Double[]{ 4.5, 1.5});
//        State.stations.put("m5", new Double[]{ 4.5, 1.5});
        ReadJson json = new ReadJson();
        json.read();
        // State.edges.put
    }
}