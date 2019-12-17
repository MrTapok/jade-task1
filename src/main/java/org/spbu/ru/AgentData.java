package org.spbu.ru;

class AgentData {
    Integer value;
    Integer[] neighbors;
    boolean isStartingAgent = false;
    Integer numberOfNodes;

    AgentData(Integer value, Integer[] neighbors, Integer numberOfNodes, boolean isStartingAgent)
    {
        this.value = value;
        this.neighbors = neighbors;
        this.numberOfNodes = numberOfNodes;
        this.isStartingAgent = isStartingAgent;
    }
}
