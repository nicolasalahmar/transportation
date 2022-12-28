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

    static double walking_speed = 5.5F;
    static double bus_fee = 400;
    static double taxi_fee = 1000;
    static double walking_fee = 0;
    static double bus_energy_cost = -5;
    static double taxi_energy_cost = 5;
    static double walking_energy_cost = -10;
    static Map<String, Map<String, Edge>> edges;
    static Map<String, Double[]> stations = new HashMap<String, Double[]>(); // i think float instead of Float will make some problems
    static String finalState;

    public State(State parent, double balance, double HP, String currentStation) {
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

    public ArrayList<State> getNextStates() {
        balance_hp_entry[] moves = checkMoves();
        ArrayList<State> result = new ArrayList<>();
        for (balance_hp_entry move : moves){
            result.add(new State(this, move.balance, move.hp, move.entry));
        }
        return result;
    }

    public double calc_hp(double HP, double distance, String operation) {
        if (operation.equals("walking")) {
            double temp = HP + (State.walking_energy_cost * distance);
            if (temp > 100)
                return 100;
            else
                return temp;
        } else if (operation.equals("bus")) {
            double temp = HP + (State.bus_energy_cost * distance);
            if (temp > 100)
                return 100;
            else
                return temp;
        } else if (operation.equals("taxi")) {
            double temp = HP + (State.taxi_energy_cost * distance);
            if (temp > 100)
                return 100;
            else
                return temp;
        } else {
            return 0;
        }
    }

    public double calc_balance(double balance, double distance, String operation) {
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

    public class balance_hp_entry{
        String entry;
        double hp;
        double balance;

        public balance_hp_entry(double balance,double hp,String entry){
            this.entry = entry;
            this.balance = balance;
            this.hp = hp;
        }
    }

    public boolean equals(State o2){
        boolean[] arr = {this.balance == o2.balance, this.HP == o2.HP, this.currentStation == o2.currentStation, this.parent == o2.parent};
        for(boolean b : arr) if(!b) return false;
        return true;
    }

    public boolean isFinal(){
        return currentStation == finalState;
    }

    // public function State move(){

    // }
}
