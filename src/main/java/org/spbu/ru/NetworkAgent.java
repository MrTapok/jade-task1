package org.spbu.ru;

import jade.core.Agent;

public class NetworkAgent extends Agent {
    AgentData AgentData;
    NetworkConfiguration networkConfiguration;

    @Override
    protected void setup()
    {
        addBehaviour(new NetworkCoverBehaviour(this));
        this.AgentData = ((AgentData[])getArguments())[0];
        this.networkConfiguration = this.AgentData.networkConfiguration;
    }
}