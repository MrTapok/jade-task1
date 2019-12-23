package org.spbu.ru;

public class App {
    public static void main(String[] args)
    {
        Integer[][] edges = {
                {},
                {2, 4},
                {3, 1},
                {2, 4},
                {1, 3}
        };
        Integer[] nodes = {0, 1, 4, 3, 2};

        NetworkGraph networkGraph = new NetworkGraph(4,4,  edges, nodes);

        NetworkConfiguration networkConfiguration = new NetworkConfiguration(0.2, 0.3, 100, 10);

        NetworkController networkController = new NetworkController(networkGraph, networkConfiguration);
        networkController.initAgents();
    }
}
