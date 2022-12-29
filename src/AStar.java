import java.util.ArrayList;

public class AStar {

    public AStar(State state){
        aStar(state);
    }


    public void aStar(State state){

        ArrayList<State> open = new ArrayList<State>();
        ArrayList<State> close = new ArrayList<State>();

        open.add(state);
        while(true){
            if(open.isEmpty()){
                System.out.println("No solution found");
                break;
            }
            State node =leastCost(open);
            open.remove(node);

            if(node.isFinal()){
                System.out.println("Final");
            }

            close.add(node);
            ArrayList<State> children = node.getNextStates();

            for (State child : children){


                if(!inArrayList(open, child) && !inArrayList(close, child)){
                    open.add(child);
                    child.parent = node;

                }else if(inArrayList(open, child) && cost(child)>cost(node)+State.edges.get(node.name).get(child.name).distance){
                    double child_cost=cost(node)+State.edges.get(node.name).get(child.name).distance;
                    child.total_cost = child_cost+heuristic(child);
                    child.parent = node;

                }else if(inArrayList(close, child) && cost(child)>cost(node)+State.edges.get(node.name).get(child.name).distance){
                    double child_cost=cost(node)+State.edges.get(node.name).get(child.name).distance;
                    child.total_cost = child_cost+heuristic(child);
                    child.parent = node;

                }
            }
        }

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
            if(s.name.equals(state.name)){
                return true;
            }
        }
        return false;
    }

    public double heuristic(State state){

        return 0;
    }

    public double cost(State state){

        return 0;
    }



}