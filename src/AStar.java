import java.util.ArrayList;

public class AStar {

    State state;
    String algorithm;

    public AStar(State state,String algorithm){
        this.state = state;
        this.algorithm = algorithm;
    }


    public State aStar(){

        ArrayList<State> queue = new ArrayList<State>();
        ArrayList<State> visited = new ArrayList<State>();

        state.cost = cost(state,algorithm);
        queue.add(state);

        
        while(true){
            if(queue.isEmpty()){
                System.out.println("No solution found");
                break;
            }
            
            State node = leastCost(queue);

            queue.remove(node);
            
            if(node.isFinal()){
                System.out.println("Final");
                return node;
            }

            visited.add(node);
            ArrayList<State> children = node.getNextStates();

            for (State child : children){
                child.cost=cost(child,algorithm);

                if(!inArrayList(queue, child) && !inArrayList(visited, child)){
                    queue.add(child);
                    child.parent = node;

                }else if(inArrayList(queue, child) && child.cost>node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance){
                    child.cost=node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance;
                    child.total_cost = child.cost+heuristic(child,algorithm);
                    child.parent = node;

                }else if(inArrayList(visited, child) && child.cost>node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance){
                    child.cost=node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance;
                    child.total_cost = child.cost+heuristic(child,algorithm);
                    child.parent = node;

                }
            }
            
        }
        return null;
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

    public State leastCost(ArrayList<State> states){
        State temp = states.get(0);

        for(State s :states){
            if(s.total_cost<temp.total_cost){
                temp=s;
            }
        }
        return temp;
    }

    public boolean inArrayList(ArrayList<State>list,State state){
        if(list.size()<1){
            return false;
        }
        for(State s : list){
            if(s.currentStation.equals(state.currentStation)){
                return true;
            }
        }
        return false;
    }

    public double heuristic(State state,String algorithm){
        if(algorithm.equals("fastestTime")){
            return State.edges.get(state.currentStation).get(State.finalState).distance;

        }else if(algorithm.equals("leastCost")){


        }else if(algorithm.equals("maxHp")){


        }
        else{} return 0;



    }


}