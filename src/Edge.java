class Edge {
    double distance;
    boolean bus_route;
    boolean taxi_route;
    double bus_speed;
    double taxi_speed;

    public Edge(){
        distance = 0;
        bus_route = false;
        taxi_route = false;
        bus_speed = 0;
        taxi_speed = 0;
    }
    public Edge(double distance, boolean bus_route, boolean taxi_route, double bus_speed, double taxi_speed){
        this.distance = distance;
        this.bus_route = bus_route;
        this.taxi_route = taxi_route;
        this.bus_speed = bus_speed;
        this.taxi_speed = taxi_speed;
    }
}