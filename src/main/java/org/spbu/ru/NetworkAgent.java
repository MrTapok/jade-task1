package org.spbu.ru;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class NetworkAgent extends Agent
{
    boolean Visited = false;
    AgentData AgentData;
    String RequesterName;
    int SentMsgs = 0;
    int ReceivedMsgs = 0;
    int Buffer = 0;

    @Override
    protected void setup() {

        addBehaviour(new NetworkCoverBehaviour(this));

        this.AgentData = ((AgentData[])getArguments())[0];

        if (this.AgentData.isStartingAgent)
        {
            System.out.println("Стартовый агент " + getAID().getLocalName());

            this.Visited = true;
            ACLMessage initMessage = new ACLMessage(ACLMessage.REQUEST);
            for (int i = 0; i < this.AgentData.neighbors.length; ++i)
            {
                initMessage.addReceiver(new AID(Integer.toString(this.AgentData.neighbors[i]), AID.ISLOCALNAME));
                this.SentMsgs++;
            }
            this.send(initMessage);
        }
        else
        {
            System.out.println("Обычный агент " + getAID().getLocalName());
        }
    }
}