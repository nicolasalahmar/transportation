class Edge {
    float distance;
    boolean bus_route;
    boolean taxi_route;
    float bus_speed;
    float taxi_speed;
    String bus_route_name;

    public Edge(){
        distance = 0;
        bus_route = false;
        taxi_route = false;
        bus_speed = 0;
        taxi_speed = 0;
        bus_route_name = "";
    }
    public Edge(int distance, boolean bus_route, boolean taxi_route, float bus_speed, float taxi_speed, String bus_route_name){
        this.distance = distance;
        this.bus_route = bus_route;
        this.taxi_route = taxi_route;
        this.bus_speed = bus_speed;
        this.taxi_speed = taxi_speed;
        this.bus_route_name = bus_route_name;
    }
}