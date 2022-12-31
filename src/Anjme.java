import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Anjme {
    PriorityQueue<State> q;
    // ArrayList<ArrayList<Integer>> dist;
    HashMap<String, Double> dist = new HashMap<>();
    State s;
    String algorithm;


    public Anjme(String algorithm, State s){
        this.q = new PriorityQueue<State>();
        this.algorithm = algorithm;
        this.s = s;
    }

    public State search(){
        this.dist.put( s.currentStation, 0.0);
        this.q.add(s);
        State current_state = s;
        double path = 0.0;
        double edge;

         while(!q.isEmpty()){
            current_state = q.poll();
            path = current_state.total_cost;

            if (current_state.isFinal()){
                return current_state;
            }

            if (path > this.dist.get(s.currentStation))
                continue;
         
                for (State child : current_state.getNextStates()){
                    edge = State.edges.get(current_state.currentStation).get(child.currentStation).distance;
                    if (path + edge  < this.dist.get(child.currentStation)){
                        this.dist.put(child.currentStation, path + edge);
                        // child.cost = this.cost(child, this.algorithm);
                        child.total_cost = dist.get(child.currentStation) + heuristic(child, algorithm);
                        q.add(child);
                    }
                 }
        }
        return null;
    }

    public double heuristic(State state,String algorithm){
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

        }else if(algorithm.equals("maxHp")){

        }else{

        }
        return 0;
    }

}

