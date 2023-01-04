import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ReadJson json = new ReadJson();
        json.read();
        State s = json.s;

        maxHp(s);
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
}