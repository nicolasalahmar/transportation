import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        State s = new State(null, 10000, 100, "m1");
        //this.stations.put(station name, [bus waiting time, taxi waiting time])

        State.stations = new HashMap<String, Double[]>();
        State.stations.put("m1", new Double[]{ 4.5, 1.5});//we'll consider m1 is IT Faculty
        State.stations.put("m2", new Double[]{ 4.5, 1.5});//we'll consider m2 is Bab Sharqi Area
        State.stations.put("m3", new Double[]{ 4.5, 1.5});//we'll consider m3 is Bab Touma Square
        State.stations.put("m4", new Double[]{ 4.5, 1.5});//we'll conside m4 is Abbasiyeen Square
        State.stations.put("m5", new Double[]{ 4.5, 1.5});//we'll consider m5 is Abbasiyeen Garages

        // State.edges.put
        State.edges = new HashMap<String, HashMap<String, Edge>>();
        HashMap<String,Edge>temp = new HashMap<String,Edge>();

        //distances are from google maps (km)
        temp.put("m2",new Edge(2.44,false,true,40,50));
        temp.put("m3",new Edge(3.21,true,true,40,50));
        temp.put("m4",new Edge(4.49,true,true,60,50));
        temp.put("m5",new Edge(5.3,true,true,60,50));
        
        State.edges.put("m1",temp);
        temp.clear();

        temp.put("m1",new Edge(2.44,false,true,40,50));
        temp.put("m3",new Edge(0.7,false,true,40,50));
        temp.put("m4",new Edge(1.66,true,true,40,50));
        temp.put("m5",new Edge(2.63,true,true,40,50));
        
        State.edges.put("m2",temp);
        temp.clear();

        temp.put("m1",new Edge(3.21,true,true,40,50));
        temp.put("m2",new Edge(0.7,false,true,40,50));
        temp.put("m4",new Edge(1.60,true,true,40,50));
        temp.put("m5",new Edge(2.57,false,true,40,50));
        
        State.edges.put("m3",temp);
        temp.clear();

        temp.put("m1",new Edge(4.49,true,true,40,50));
        temp.put("m2",new Edge(1.66,true,true,40,50));
        temp.put("m3",new Edge(1.60,true,true,40,50));
        temp.put("m5",new Edge(0.94,true,true,40,50));
        
        State.edges.put("m4",temp);
        temp.clear();

        temp.put("m1",new Edge(5.3,true,true,40,50));
        temp.put("m2",new Edge(2.63,true,true,40,50));
        temp.put("m3",new Edge(2.57,false,true,40,50));
        temp.put("m4",new Edge(0.94,true,true,40,50));
        
        State.edges.put("m5",temp);
        temp.clear();


    }
}