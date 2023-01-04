import java.util.*;


public class Astar {
    static PriorityQueue<State> q;
    static HashMap<String, Double> balance_dist;
    static HashMap<String, Double> hp_dist;
    static HashMap<String, Double> time_dist;
    static Set<State> visited;
    static State s;
    State t;
    String algorithm;
    static int n,m;
    static double start, finish;

    public Astar(String algorithm, State s){
        balance_dist = new HashMap<>();
        hp_dist = new HashMap<>();
        time_dist = new HashMap<>();
        visited = new HashSet<State>();
        q = new PriorityQueue<>();

        this.algorithm = algorithm;

        Astar.s = s;
        Astar.m=0;
        Astar.n=0;

        q = new PriorityQueue<>();
        for (Map.Entry<String, Double[]> entry : State.stations.entrySet()){    //initialize the distance array
            balance_dist.put(entry.getKey(), 99999999.9);
            hp_dist.put(entry.getKey(), 99999999.9);
            time_dist.put(entry.getKey(), 99999999.9);
        }
        t = Astar.search(algorithm);

    }

    public static State search(String algorithm){
        start= System.nanoTime();
        s.total_cost = 0.0;
        s.cost = 0.0;
        Astar.balance_dist.put( s.currentStation, 0.0);
        Astar.hp_dist.put( s.currentStation, 0.0);
        Astar.time_dist.put( s.currentStation, 0.0);
        Astar.q.add(s);
        Astar.visited.add(s);
        State current_state;

         while(!q.isEmpty()){
            current_state = q.poll();

            Astar.m++;

            if (current_state.isFinal()){
                finish = System.nanoTime();
                return current_state;
            }

            if ((current_state.spent_money > Astar.balance_dist.get(current_state.currentStation)) &&
            (current_state.spent_HP > Astar.hp_dist.get(current_state.currentStation)) &&
            (current_state.time > Astar.time_dist.get(current_state.currentStation))
            )
                continue;

             ArrayList<State> temp = current_state.getNextStates();
             for (State child : temp) {
                 child.cost = cost(child, algorithm);
                 child.total_cost = child.cost + heuristic(child);
                 Astar.n++;

                 if (((child.spent_HP < Astar.hp_dist.get(child.currentStation))
                  || (child.spent_money < Astar.balance_dist.get(child.currentStation))
                  || (child.time < Astar.time_dist.get(child.currentStation)))
                  && !visited.contains(child)
                  ) {
                    Astar.hp_dist.put(child.currentStation, child.spent_HP);
                    Astar.balance_dist.put(child.currentStation, child.spent_money);
                    Astar.time_dist.put(child.currentStation, child.time);
                    q.add(child);
                    visited.add(child);
                 }
             }
         }
        return null;
    }

    public static double heuristic(State state){
        if (state.isFinal()){
            return 0.0;
        }
        Map<String, Edge> moves = State.edges.get(state.currentStation);
        ArrayList<Double> arr = new ArrayList<>();

        for (Map.Entry<String, Edge> entry : moves.entrySet()){
            arr.add(entry.getValue().distance);
        }
        Collections.sort(arr);

        double i=1;
        double final_dist = State.edges.get(state.currentStation).get(State.finalState).distance;
        for(Double d : arr){
            if (final_dist == d){
                return i/arr.size() * 0.1;
            }
            i++;
        }
        return 0.0;
    }

    public static double cost(State state,String algorithm){
        if(algorithm.equals("fastestTime")){
            return state.time;
        }else if(algorithm.equals("leastCost")){
            return state.spent_money;
        }else if(algorithm.equals("maxHp")){
            return state.spent_HP;
        }else if(algorithm.equals("allCosts")){
            return state.normalize();
        }
        return 0.0;
    }
}

