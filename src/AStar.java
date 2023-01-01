import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Astar {
    PriorityQueue<State> q;
    HashMap<String, Double> dist = new HashMap<>();
    State s;
    String algorithm;
    int n,m;


    public Astar(String algorithm, State s){
        this.algorithm = algorithm;

        if (this.algorithm.equals("maxHp")){
            this.q = new PriorityQueue<State>(Collections.reverseOrder());
        }
        else{
            this.q = new PriorityQueue<State>();
        }

        this.s = s;
        this.m=0;
        this.n=0;

        for (Map.Entry<String, Double[]> entry : State.stations.entrySet()){    //initialize the distance array
            this.dist.put(entry.getKey(), 99999999.9);
        }
    }

    public State search(){
        s.total_cost = 0.0;
        s.cost = 0.0;
        this.dist.put( s.currentStation, 0.0);
        this.q.add(s);
        State current_state;
        double w;

         while(!q.isEmpty()){
            current_state = q.poll();

            this.m++;

            if (current_state.isFinal()){
                return current_state;
            }

            if (current_state.cost > this.dist.get(current_state.currentStation))
                continue;
         
                for (State child : current_state.getNextStates()){
                    child.cost = cost(child, algorithm);
                    child.total_cost = child.cost + heuristic(child, algorithm);
                    this.n++;

                    w = child.cost - child.parent.cost;
                    if (current_state.cost + w  < this.dist.get(child.currentStation)){
                        this.dist.put(child.currentStation, current_state.total_cost + w);
                        q.add(child);
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


        }else if(algorithm.equals("maxHp")){


        }
        else{} return 0;
    }

    public double cost(State state,String algorithm){

        if(algorithm.equals("fastestTime")){
            return state.time;
        }else if(algorithm.equals("leastCost")){
            return state.spent_money;
        }else if(algorithm.equals("maxHp")){
            return state.HP;
        }
        return 0.0;
    }
}

