import java.util.*;


public class Astar {
    PriorityQueue<State> q;
    HashMap<String, Double> balance_dist = new HashMap<>();
    HashMap<String, Double> hp_dist = new HashMap<>();
    HashMap<String, Double> time_dist = new HashMap<>();
    Set<State> visited = new HashSet<State>();
    State s;
    String algorithm;
    int n,m;


    public Astar(String algorithm,String order, State s){
        this.algorithm = algorithm;
        this.q = new PriorityQueue<>();

        if (order.equals("descending")){
            this.q = new PriorityQueue<>(Collections.reverseOrder());
        }
        else{
            this.q = new PriorityQueue<>();
        }

        this.s = s;
        this.m=0;
        this.n=0;

        for (Map.Entry<String, Double[]> entry : State.stations.entrySet()){    //initialize the distance array
            this.balance_dist.put(entry.getKey(), 99999999.9);
            this.hp_dist.put(entry.getKey(), 99999999.9);
            this.time_dist.put(entry.getKey(), 99999999.9);
        }
    }

    public State search(){
        s.total_cost = 0.0;
        s.cost = 0.0;
        this.balance_dist.put( s.currentStation, 0.0);
        this.hp_dist.put( s.currentStation, 0.0);
        this.time_dist.put( s.currentStation, 0.0);
        this.q.add(s);
        this.visited.add(s);
        State current_state;

         while(!q.isEmpty()){
            current_state = q.poll();

            this.m++;

            if (current_state.isFinal()){
                return current_state;
            }

             if (current_state.cost > this.balance_dist.get(current_state.currentStation))
                 continue;
             ArrayList<State> temp = current_state.getNextStates();
             for (State child : temp) {
                 child.cost = cost(child, algorithm);
                 child.total_cost = child.cost + heuristic(child, algorithm);
                 this.n++;

                 if ((child.spent_HP < this.hp_dist.get(child.currentStation)
                  || child.spent_money < this.balance_dist.get(child.currentStation)
                  || child.time < this.time_dist.get(child.currentStation))
                  && !visited.contains(child)
                  ) {

//                     if (child.spent_HP < this.hp_dist.get(child.currentStation))//todo figure out why these ifs are not working
                         this.hp_dist.put(child.currentStation, child.spent_HP);
//                     if (child.spent_money < this.balance_dist.get(child.currentStation))
                         this.balance_dist.put(child.currentStation, child.spent_money);
//                     if (child.time < this.time_dist.get(child.currentStation))
                        this.time_dist.put(child.currentStation, child.time);
                     q.add(child);
                     visited.add(child);
                 }
             }
         }
        return null;
    }

    public double heuristic(State state,String algorithm){
        if (state.isFinal()){
            return 0.0;
        }
        if(algorithm.equals("fastestTime")){
            return State.edges.get(state.currentStation).get(State.finalState).distance;
        }else if(algorithm.equals("leastCost")){
            return State.edges.get(state.currentStation).get(State.finalState).distance;
        }else if(algorithm.equals("maxHp")){
            return State.edges.get(state.currentStation).get(State.finalState).distance;
        }
            return 0.0;
    }

    public double cost(State state,String algorithm){
        if(algorithm.equals("fastestTime")){
            return state.time;
        }else if(algorithm.equals("leastCost")){
            return state.spent_money;
        }else if(algorithm.equals("maxHp")){
            return state.spent_HP;
        }
        return 0.0;
    }
}

