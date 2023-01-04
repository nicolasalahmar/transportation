import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ReadJson json = new ReadJson();
        json.read();
        State s = json.s;

        leastCost(s);
    }

    public static void leastCost(State s){
        Astar a = new Astar("leastCost", s);

        State t = a.t;

        System.out.println("path: ");
        t.printPath();
        System.out.println("number of nodes processed: "+Astar.m);
        System.out.println("number of nodes instantiated: "+Astar.n);
        System.out.println("time of execution: "+((Astar.finish - Astar.start)/1000000)+" ms");
    }

    public static void fastestTime(State s){
        Astar a = new Astar("fastestTime", s);

        State t = a.t;

        System.out.println("path: ");
        t.printPath();
        System.out.println("number of nodes processed: "+Astar.m);
        System.out.println("number of nodes instantiated: "+Astar.n);
        System.out.println("time of execution: "+((Astar.finish - Astar.start)/1000000)+" ms");
    }
    
    public static void maxHp(State s){
        Astar a = new Astar("maxHp", s);

        State t = a.t;

        System.out.println("path: ");
        t.printPath();
        System.out.println("number of nodes processed: "+Astar.m);
        System.out.println("number of nodes instantiated: "+Astar.n);
        System.out.println("time of execution: "+((Astar.finish - Astar.start)/1000000)+" ms");
    }

    public static void allCosts(State s){
        Astar a = new Astar("allCosts", s);

        State t = a.t;

        System.out.println("path: ");
        t.printPath();
        System.out.println("number of nodes processed: "+Astar.m);
        System.out.println("number of nodes instantiated: "+Astar.n);
        System.out.println("time of execution: "+((Astar.finish - Astar.start)/1000000)+" ms");
    }

    public static void func() {
       State.stations.put("Hamak", new Double[]{ 0.2, 0.2});
       State.stations.put("Bab Touma", new Double[]{ 0.2, 0.2});
       State.stations.put("Bab sharqui", new Double[]{ 0.2,0.2});
       State.stations.put("Abbasyian", new Double[]{ 0.0, 0.0});
       State.stations.put("Kassaa", new Double[]{ 0.2, 0.02});

       State.walking_speed = 5.5;
       State.bus_fee = 400;
       State.taxi_fee = 1000;
       State.walking_fee = 0;
       State.bus_energy_cost = -5;
       State.taxi_energy_cost = 5;
       State.walking_energy_cost = -10;
       State.finalState = "Kassaa";

       Map<String, Edge>temp = new HashMap<>();

       temp.put("Bab sharqui", new Edge(5, true, false,40,50,"hamak - bab sharqui"));
       temp.put("Bab Touma", new Edge(7,true,true,40,50,"hamak - bab touma"));
       temp.put("Abbasyian", new Edge(9, false, false, 40, 50, "hamak - Abbasyian"));
       temp.put("Kassaa", new Edge(12, false, true, 40, 50, "hamak - Kassaa"));

       State.edges.put("Hamak", temp);




       Map<String, Edge>temp2 = new HashMap<>();

       temp2.put("Hamak", new Edge(5, false, false, 40, 50, "Bab sharqui - Hamak"));
       temp2.put("Bab Touma", new Edge(3, false, false, 40, 50, "Bab sharqui - Bab Touma"));
       temp2.put("Abbasyian", new Edge(5, false, true, 40, 50, "Bab sharqui - Abbasyian"));
       temp2.put("Kassaa", new Edge(12, false, false, 40, 50, "Bab sharqui - Kassaa"));

       State.edges.put("Bab sharqui", temp2);



       Map<String, Edge>temp3 = new HashMap<>();

       temp3.put("Hamak", new Edge(7, false, false, 40, 50, "Bab Touma - Hamak"));
       temp3.put("Bab sharqui", new Edge(3, true, true, 40, 50, "Bab Touma - bab sharqui"));
       temp3.put("Abbasyian", new Edge(2, false, true, 40, 50, "Bab Touma - Abbasyian"));
       temp3.put("Kassaa", new Edge(10, false, false, 40, 50, "Bab Touma - Kassaa"));

       State.edges.put("Bab Touma", temp3);




       Map<String, Edge>temp4 = new HashMap<>();

       temp4.put("Hamak", new Edge(9, false, false, 40, 50, "Abbasyian - Hamak"));
       temp4.put("Bab sharqui", new Edge(5, true, false, 40, 50, "Abbasyian - bab sharqui"));
       temp4.put("Bab Touma", new Edge(2, false, false, 40, 50, "Abbasyian - Bab Touma"));
       temp4.put("Kassaa", new Edge(1, false, true, 40, 50, "Abbasyian - Kassaa"));

       State.edges.put("Abbasyian", temp4);


       Map<String, Edge>temp5 = new HashMap<>();

       temp5.put("Hamak", new Edge(12, false, false, 40, 50, "Kassaa - Hamak"));
       temp5.put("Bab sharqui", new Edge(12, false, false, 40, 50, "Kassaa - bab sharqui"));
       temp5.put("Bab Touma", new Edge(10, false, false, 40, 50, "Kassaa - Bab Touma"));
       temp5.put("Abbasyian", new Edge(1, false, false, 40, 50, "Kassaa - Abbasyian"));

       State.edges.put("Kassaa", temp5);
    }
}