import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class State {
    State parent;
    static float walking_speed = 5.5F;
    static Map<String, float[]> stations; // i think float instead of Float will make some problems
    static int bus_fee = 400;
    static int taxi_fee = 1000;
    static int walking_fee = 0;
    float balance = 10000;
    int HP = 100;
    static int bus_energy_cost = -5;
    static int taxi_energy_cost = 5;
    static int walking_energy_cost = -10;
    static Map<String, Map<String, Edge>> edges;
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
            int new_hp_walking, new_hp_bus, new_hp_taxi;
            float new_balance_walking, new_balance_bus, new_balance_taxi;
            // new hp and new balance in case of walking to next station
            new_hp_walking = this.calc_hp(this.HP, e.distance, "walking");
            new_balance_walking = this.calc_balance(this.balance, e.distance, "walking");
            // new hp and new balance in case of bus to next station
            new_hp_bus = this.calc_hp(this.HP, e.distance, "bus");
            new_balance_bus = this.calc_balance(this.balance, e.distance, "bus");
            // new hp and new balance in case of taxi to next station
            new_hp_taxi = this.calc_hp(this.HP, e.distance, "taxi");
            new_balance_taxi = this.calc_balance(this.balance, e.distance, "taxi");

            if (new_hp_walking > 0 && new_balance_walking > 0) {// you can walk (hp allows it /balance allows it))
                result[i] = new State(this, new_balance_walking, new_hp_walking, entry.getKey());
                i++;
            }

            if (e.bus_route && new_hp_bus > 0 && new_balance_bus > 0) {
                // you can take the bus (there is bus route/
                // hp allows it /balance allows it)
                result[i] = new State(this, new_balance_bus, new_hp_bus, entry.getKey());
                i++;
            }

            if (e.taxi_route && new_hp_taxi > 0 && new_balance_taxi > 0) {
                // you can take a taxi (there is taxi route/
                // hp allows it / balance allows it)
                result[i] = new State(this, new_balance_taxi, new_hp_taxi, entry.getKey());
                i++;
            }
        }
        return result;
    }

    public int calc_hp(int HP, float distance, String operation) {
        if (operation.equals("walking")) {
            int temp = (int) (HP + (State.walking_energy_cost * distance));
            if (temp > 100)
                return 100;
            else
                return temp;
        } else if (operation.equals("bus")) {
            int temp = (int) (HP + (State.bus_energy_cost * distance));
            if (temp > 100)
                return 100;
            else
                return temp;
        } else if (operation.equals("taxi")) {
            int temp = (int) (HP + (State.taxi_energy_cost * distance));
            if (temp > 100)
                return 100;
            else
                return temp;
        } else {
            return 0;
        }
    }

    public float calc_balance(float balance, float distance, String operation) {
        if (operation.equals("walking")) {
            return balance - State.walking_fee;
        } else if (operation.equals("bus")) {
            return balance - State.bus_fee;
        } else if (operation.equals("taxi")) {
            return balance - (State.taxi_fee * distance);
        } else {
            return 0;

        }
    }

}
