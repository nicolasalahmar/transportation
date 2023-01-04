import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class State implements Comparable<State>{
    State parent;
    double balance;
    double spent_money;
    double spent_HP;
    double HP;
    String currentStation;
    double time;
    String operation;
    double cost;
    double total_cost;
    double normal;


    static double walking_speed;
    static double bus_fee;
    static double taxi_fee;
    static double walking_fee;
    static double bus_energy_cost;
    static double taxi_energy_cost;
    static double walking_energy_cost;
    static Map<String, Map<String, Edge>> edges = new HashMap<>();
    static Map<String, Double[]> stations = new HashMap<>();
    static String finalState;
    static Map<String, Double> best_values = new HashMap<>();
    static Map<String, Double> ranges = new HashMap<>();


    public State(State parent, double balance, double spent_money, double spent_HP, double HP, String currentStation, double time, String operation) {
        this.parent = parent;
        this.balance = balance;
        this.HP = HP;
        this.currentStation = currentStation;
        this.time = time;
        this.operation = operation;
        this.spent_money = spent_money;
        this.spent_HP = spent_HP;
        this.cost = 0;
        this.total_cost = 0;
    }

    // public static void fill_best_values_and_ranges(State s){
    //     State temp1 = new State(s.parent, s.balance, s.spent_money, s.spent_HP, s.HP, s.currentStation, s.time, s.operation);
    //     State temp2 = new State(s.parent, s.balance, s.spent_money, s.spent_HP, s.HP, s.currentStation, s.time, s.operation);
    //     State temp3 = new State(s.parent, s.balance, s.spent_money, s.spent_HP, s.HP, s.currentStation, s.time, s.operation);
    //     State temp4 = new State(s.parent, s.balance, s.spent_money, s.spent_HP, s.HP, s.currentStation, s.time, s.operation);
    //     State temp5 = new State(s.parent, s.balance, s.spent_money, s.spent_HP, s.HP, s.currentStation, s.time, s.operation);
    //     State temp6 = new State(s.parent, s.balance, s.spent_money, s.spent_HP, s.HP, s.currentStation, s.time, s.operation);
    //     Double best, worst;


    //     Astar a = new Astar("fastestTime", "ascending", temp1);
    //     best = a.search().time;
    //     State.best_values.put("best_time", best);

    //     a = new Astar("fastestTime", "descending", temp2);
    //     worst = a.search().time;
    //     State.best_values.put("worst_time", worst);
    //     State.ranges.put("time_range", worst - best);





    //     a = new Astar("leastCost", "ascending", temp3);
    //     best = a.search().balance;
    //     State.best_values.put("best_cost", best);

    //     a = new Astar("leastCost", "descending", temp4);
    //     worst = a.search().balance;
    //     State.best_values.put("worst_cost", worst);
    //     State.ranges.put("cost_range", best - worst);






    //     a = new Astar("maxHp", "ascending", temp5);
    //     best = a.search().HP;
    //     State.best_values.put("best_HP", best);

    //     a = new Astar("maxHp", "descending", temp6);
    //     worst = a.search().HP;
    //     State.best_values.put("worst_HP", worst);
    //     State.ranges.put("HP_range", best - worst);
    // }

    public ArrayList<balance_hp_entry_time_operation> checkMoves() {
        Map<String, Edge> moves = State.edges.get(currentStation);
        ArrayList<balance_hp_entry_time_operation> result = new ArrayList<>();
        for (Map.Entry<String, Edge> entry : moves.entrySet()) {
            Edge e = entry.getValue();
            double new_hp_walking, new_hp_bus, new_hp_taxi;
            double new_balance_walking, new_balance_bus, new_balance_taxi;
            double new_time_walking, new_time_bus, new_time_taxi;
            // new hp, new balance and new time in case of walking to next station
            new_hp_walking = this.calc_hp(this.HP, e.distance, "walking");
            new_balance_walking = this.calc_balance(this.balance, e.distance, "walking");
            new_time_walking = this.calc_time(e.distance, State.walking_speed, "walking");
            // new hp, new balance and new time in case of bus to next station
            new_hp_bus = this.calc_hp(this.HP, e.distance, "bus");
            new_balance_bus = this.calc_balance(this.balance, e.distance, "bus");
            new_time_bus = this.calc_time(e.distance, e.bus_speed, "bus");
            // new hp, new balance and new time in case of taxi to next station
            new_hp_taxi = this.calc_hp(this.HP, e.distance, "taxi");
            new_balance_taxi = this.calc_balance(this.balance, e.distance, "taxi");
            new_time_taxi = this.calc_time(e.distance, e.taxi_speed, "taxi");

            if (new_hp_walking > 0 && new_balance_walking >= 0) {// you can walk (hp allows it /balance allows it))
                result.add(new balance_hp_entry_time_operation(new_balance_walking, this.spent_money + (this.balance - new_balance_walking), this.spent_HP + (this.HP - new_hp_walking), new_hp_walking, entry.getKey(), new_time_walking, "walking"));
            }

            if (e.bus_route && new_hp_bus > 0 && new_balance_bus >= 0) {
                // you can take the bus (there is bus route/
                // hp allows it /balance allows it)
                result.add(new balance_hp_entry_time_operation(new_balance_bus, this.spent_money + (this.balance - new_balance_bus), this.spent_HP + (this.HP - new_hp_bus), new_hp_bus, entry.getKey(), new_time_bus, "bus"));
            }

            if (e.taxi_route && new_hp_taxi > 0 && new_balance_taxi >= 0) {
                // you can take a taxi (there is taxi route/
                // hp allows it / balance allows it)
                result.add(new balance_hp_entry_time_operation(new_balance_taxi, this.spent_money + (this.balance - new_balance_taxi), this.spent_HP + (this.HP - new_hp_taxi), new_hp_taxi, entry.getKey(), new_time_taxi, "taxi"));
            }
        }
        return result;
    }

    public ArrayList<State> getNextStates() {
        ArrayList<balance_hp_entry_time_operation> moves = checkMoves();
        ArrayList<State> result = new ArrayList<>();
        for (balance_hp_entry_time_operation move : moves){
            result.add(this.move(move.balance, move.spent_money, move.spent_HP, move.hp, move.entry, move.time, move.operation));
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

    public double calc_time(double distance, double speed, String operation) {
        if (operation.equals("walking")) {
            return this.time + (distance/speed);
        } else if (operation.equals("bus")) {
            // the time already gone + time of waiting + time of travelling
            return this.time + State.stations.get(this.currentStation)[0] + (distance/speed);
        } else if (operation.equals("taxi")) {
            // the time already gone + time of waiting + time of travelling
            return this.time + State.stations.get(this.currentStation)[1] + (distance/speed);
        } else {
            return 0;
        }
    }

    public class balance_hp_entry_time_operation{
        String entry;
        double hp;
        double balance;
        double time;
        String operation;
        double spent_money;
        double spent_HP;

        public balance_hp_entry_time_operation(double balance, double spent_money, double spent_HP, double hp,String entry, double time, String operation){
            this.entry = entry;
            this.balance = balance;
            this.hp = hp;
            this.time = time;
            this.operation = operation;
            this.spent_money = spent_money;
            this.spent_HP = spent_HP;
        }

        public String toString(){
            return "station: "+entry+"\nhp: "+hp+"\n"+balance+"\ntime: "+time+"\noperation: "+operation+"\nspent money: "+spent_money+"\n";
        }
    }

    public boolean equals(State o2){
        boolean[] arr = {this.balance == o2.balance, this.HP == o2.HP, this.currentStation.equals(o2.currentStation), this.parent == o2.parent, this.time == o2.time};
        for(boolean b : arr) if(!b) return false;
        return true;
    }

    public boolean isFinal(){
        return currentStation.equals(finalState);
    }

    public State move(double balance, double spent_money, double spent_HP, double HP, String station, double time, String operation){
        return new State(this, balance, spent_money, spent_HP, HP, station, time, operation);
    }

    public String toString(){
        return "station: "+this.currentStation + "\nHP: "+this.HP+"\nBalance: "+this.balance+"\ntime: "+this.time+"\nOperation: "+this.operation +"\nspent HP: "+this.spent_HP+"\n";
    }

    public void printState(){
        System.out.println(this);
    }

    public void printPath(){
        ArrayList<State> arr = new ArrayList<>();
        State temp = this;
        while (temp!=null){
            // System.out.println(temp);
            arr.add(0, temp);
            temp = temp.parent;
        }
        for (State s : arr){
            s.printState();
        }
    }

    @Override public int compareTo(State o)
    {
        if (this.total_cost > o.total_cost) {
            return 1;
        }
        else if (this.total_cost < o.total_cost) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
