package org.spbu.ru;

public class App {
    public static void main(String[] args) {
        Integer[][] edges = {
                {},
                {2, 3}, // <- 1
                {1, 3}, // <- 2
                {1, 2, 4, 7}, // <- 3
                {3, 5},    // <- 4
                {4, 7, 6}, // <- 5
                {5, 7},    // <- 6
                {3, 5, 6}      // <- 7
        };
        Integer[] nodes = {0, 5, 4, 1, 3, 2, 3, 8};

        NetworkGraph networkGraph = new NetworkGraph(7,9,  edges, nodes);

        NetworkController networkController = new NetworkController(networkGraph);
        networkController.initAgents();
    }
}
