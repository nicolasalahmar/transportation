import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class State {
    State parent;
    static float walking_speed= 5.5F;
    static Map<String,float[]> stations;
    static int bus_fee=400;
    static int taxi_fee=1000;
    static int walking_fee=0;
    float balance;
    int HP;
    static int bus_energy_cost=-5;
    static int taxi_energy_cost=5;
    static int walking_energy_cost=-10;
    static Map<String,Map<String,Edge>> edges;
    String currentStation;

    public State(State parent, float balance, int HP,String currentStation) {
        this.parent = parent;
        this.balance = balance;
        this.HP = HP;
        this.currentStation=currentStation;
    }

    public Map<String,Edge> checkMoves(Map<String,Map<String,Edge>> edges) {
        return  edges.get(currentStation);
    }



}


