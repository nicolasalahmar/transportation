class Edge {
    float distance;
    boolean bus_route;
    boolean taxi_route;
    float bus_speed;
    float taxi_speed;

    public Edge(){
        distance = 0;
        bus_route = false;
        taxi_route = false;
        bus_speed = 0;
        taxi_speed = 0;
    }
}