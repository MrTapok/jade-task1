package org.spbu.ru;

class AgentData {
    double value;
    Integer[] neighbors;
    Integer numberOfNodes;
    NetworkConfiguration networkConfiguration;

    AgentData(double value, Integer[] neighbors, Integer numberOfNodes, NetworkConfiguration networkConfiguration)
    {
        this.value = value;
        this.neighbors = neighbors;
        this.numberOfNodes = numberOfNodes;
        this.networkConfiguration = networkConfiguration;
    }
}
