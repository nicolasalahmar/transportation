import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class State {
    State parent;
    double balance;
    double HP;
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

    public balance_hp_entry[] checkMoves() {
        Map<String, Edge> moves = State.edges.get(currentStation);

        balance_hp_entry[] result = new balance_hp_entry[moves.size() * 3];
        int i = 0;
        for (Map.Entry<String, Edge> entry : moves.entrySet()) {
            Edge e = entry.getValue();
            double new_hp_walking, new_hp_bus, new_hp_taxi;
            double new_balance_walking, new_balance_bus, new_balance_taxi;
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
                result[i] = new balance_hp_entry(new_balance_walking, new_hp_walking, entry.getKey());
                i++;
            }

            if (e.bus_route && new_hp_bus > 0 && new_balance_bus > 0) {
                // you can take the bus (there is bus route/
                // hp allows it /balance allows it)
                result[i] = new balance_hp_entry(new_balance_bus, new_hp_bus, entry.getKey());
                i++;
            }

            if (e.taxi_route && new_hp_taxi > 0 && new_balance_taxi > 0) {
                // you can take a taxi (there is taxi route/
                // hp allows it / balance allows it)
                result[i] = new balance_hp_entry(new_balance_taxi, new_hp_taxi, entry.getKey());
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