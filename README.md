# TRANSPORTATION

This project is the solution to the homework of Intelligent Searching Algorithms in the faculty of Information Technology Engineering - Damascus University.

### PROBLEM:

Given a graph of nodes  with multiple costs of travelling from each node to the other (balance-HP-time) find the least cost path (provided balance and HP doesn't reach 0) in each type of cost given and then find the least cost path in all types of costs combined.

e.g: given the graph G(nodes, vertices) find:
1. the path with the least (time spent) from initial node to final node.
2. the path with the least (money spent) from initial node to final node.
3. the path with the least (HP spent) from initial node to final node.
4. the path with the least (time, money, HP spent) from initial node to final node.

### ITERATION FUNCTION:

- Taxi charges a fee by every km covered (1000 pounds).
- Taxi recharges your HP by 5 for every km covered.
- Bus charges a fee the second it is boarded (400 pounds).
- Bus costs 5 HP for every km covered.
- walking doesn't charge a fee.
- walking costs 10 HP for every km covered.

this gives us the following constraints:
1. In case of walking:
   * there is no constraint on existence of route because you can walk anywhere
   * distance * 10 < HP
   * there is no constraint on balance as there is no cost for walking

2. In case of Bus:
   * there exists a Bus line in this edge
   * distance * 5 < HP
   * balance >= 400
3. In case of Taxi:
    * there exists a Taxi line in this edge
    * there is no constraint on HP because Taxi recharges HP (P.S: HP can't go over 100)
    * balance >= 400

### CONTRIBUTORS:

1. Ehsan Abourshaed
2. George Nigoghossian
3. Johny Kodsy
4. Rita Rezeq
5. Nicolas Al Ahmar