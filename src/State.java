import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class State {
    State parent;
    static float walking_speed = 5.5F;
    static HashMap<String, Double[]> stations;   // i think float instead of Float will make some problems
    static int bus_fee = 400;
    static int taxi_fee = 1000;
    static int walking_fee = 0;
    float balance;
    int HP;
    static int bus_energy_cost = -5;
    static int taxi_energy_cost = 5;
    static int walking_energy_cost = -10;
    static HashMap<String, HashMap<String, Edge>> edges;
    String currentStation;

    public State(State parent, float balance, int HP, String currentStation) {
        this.parent = parent;
        this.balance = balance;
        this.HP = HP;
        this.currentStation = currentStation;
    }

    public Map<String, Edge> checkMoves() {
        return State.edges.get(currentStation);
    }

    public State[] getNextStates() {
        Map<String, Edge> moves = checkMoves();
        State[] result = new State[moves.size() * 3];
        int i = 0;
        for (Map.Entry<String, Edge> entry : moves.entrySet()) {
            Edge e = entry.getValue();
            if (HP + (State.walking_energy_cost * e.distance) > 0
                    && balance - State.walking_fee > 0) {// you can walk
                result[i] = new State(this, balance,
                        (int) (HP + (State.walking_energy_cost * e.distance)), entry.getKey());
                i++;
            }

            if (e.bus_route && HP + (State.bus_energy_cost * e.distance) > 0
                    && balance - State.bus_fee > 0) {// you can take the bus (there is bus route/
                                                     // hp allows it /balance allows it)
                result[i] = new State(this, balance,
                        (int) (HP + (State.bus_energy_cost * e.distance)), entry.getKey());
                i++;
            }

            if (e.taxi_route && HP + (State.taxi_energy_cost * e.distance) > 0
                    && balance - (State.taxi_fee * e.distance) > 0) {// you can take a taxi (there
                                                                     // is taxi route/ hp allows it
                                                                     // /balance allows it)
                result[i] = new State(this, balance,
                        (int) (HP + (State.taxi_energy_cost * e.distance)), entry.getKey());
                i++;
            }
        }
        return result;
    }

}
