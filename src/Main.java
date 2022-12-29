import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        State s = new State(null,10000, 0, 100, "Hamak", 0, "");
        //this.stations.put(station name, [bus waiting time, taxi waiting time])
        Main.func();
        System.out.println(s.getNextStates());

        // ReadJson json = new ReadJson();
        // json.read();

        // State.edges.put
    }

    public static void func(){
        State.stations.put("Hamak", new Double[]{ 4.5, 1.5});
        State.stations.put("Bab Touma", new Double[]{ 7.5, 1.2});
        State.stations.put("Bab sharqui", new Double[]{ 5.1, 9.2});
        State.stations.put("Abbasyian", new Double[]{ 3.5, 6.2});
        State.stations.put("Kassaa", new Double[]{ 3.5, 6.2});
        
        State.walking_speed = 5.5;
        State.bus_fee = 400;
        State.taxi_fee = 1000;
        State.walking_fee = 0;
        State.bus_energy_cost = -5;
        State.taxi_energy_cost = 5;
        State.walking_energy_cost = -10;
        State.finalState = "Kassaa";

        Map<String, Edge>temp = new HashMap<>();

        temp.put("Bab sharqui", new Edge(5, false,false,40,50,"hamak - bab sharqui"));
        temp.put("Bab Touma", new Edge(7,true,false,40,50,"hamak - bab touma"));
        temp.put("Abbasyian", new Edge(9, false, false, 40, 50, "hamak - Abbasyian"));
        temp.put("Kassaa", new Edge(12, false, true, 40, 50, "hamak - Kassaa"));

        State.edges.put("hamak", temp);

        temp.put("Hamak", new Edge(5, false, false, 40, 50, "Bab sharqui - Hamak"));
        temp.put("Bab Touma", new Edge(3, false, false, 40, 50, "Bab sharqui - Bab Touma"));
        temp.put("Abbasyian", new Edge(5, false, true, 40, 50, "Bab sharqui - Abbasyian"));
        temp.put("Kassaa", new Edge(6, false, false, 40, 50, "Bab sharqui - Kassaa"));

        State.edges.put("Bab sharqui", temp);

        temp.put("Hamak", new Edge(7, false, false, 40, 50, "Bab Touma - Hamak"));
        temp.put("Bab sharqui", new Edge(3, true, true, 40, 50, "Bab Touma - bab sharqui"));
        temp.put("Abbasyian", new Edge(2, false, true, 40, 50, "Bab Touma - Abbasyian"));
        temp.put("Kassaa", new Edge(10, false, false, 40, 50, "Bab Touma - Kassaa"));

        State.edges.put("Bab Touma", temp);

        temp.put("Hamak", new Edge(9, false, false, 40, 50, "Abbasyian - Hamak"));
        temp.put("Bab sharqui", new Edge(5, true, false, 40, 50, "Abbasyian - bab sharqui"));
        temp.put("Bab Touma", new Edge(2, false, false, 40, 50, "Abbasyian - Bab Touma"));
        temp.put("Kassaa", new Edge(1, false, false, 40, 50, "Abbasyian - Kassaa"));

        State.edges.put("Abbasyian", temp);

        temp.put("Hamak", new Edge(12, false, false, 40, 50, "Kassaa - Hamak"));
        temp.put("Bab sharqui", new Edge(6, false, false, 40, 50, "Kassaa - bab sharqui"));
        temp.put("Bab Touma", new Edge(10, false, false, 40, 50, "Kassaa - Bab Touma"));
        temp.put("Abbasyian", new Edge(1, false, false, 40, 50, "Kassaa - Abbasyian"));

        State.edges.put("Kassaa", temp);
    }
}