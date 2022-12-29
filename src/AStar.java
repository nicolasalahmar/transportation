import java.util.ArrayList;

public class AStar {

    public AStar(State state,String algorithm){
        aStar(state,algorithm);
    }


    public void aStar(State state,String algorithm){

        ArrayList<State> open = new ArrayList<State>();
        ArrayList<State> close = new ArrayList<State>();

        state.cost = cost(state,algorithm);
        open.add(state);


        while(true){
            if(open.isEmpty()){
                System.out.println("No solution found");
                break;
            }

            State node = leastCost(open);

            open.remove(node);

            if(node.isFinal()){
                System.out.println("Final");
            }

            close.add(node);
            ArrayList<State> children = node.getNextStates();

            for (State child : children){
                child.cost=cost(child,algorithm);

                if(!inArrayList(open, child) && !inArrayList(close, child)){
                    open.add(child);
                    child.parent = node;

                }else if(inArrayList(open, child) && child.cost>node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance){
                    child.cost=node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance;
                    child.total_cost = child.cost+heuristic(child,algorithm);
                    child.parent = node;

                }else if(inArrayList(close, child) && child.cost>node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance){
                    child.cost=node.cost+State.edges.get(node.currentStation).get(child.currentStation).distance;
                    child.total_cost = child.cost+heuristic(child,algorithm);
                    child.parent = node;

                }
            }
        }

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