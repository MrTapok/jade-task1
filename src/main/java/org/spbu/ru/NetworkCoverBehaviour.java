package org.spbu.ru;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.AID;

public class NetworkCoverBehaviour extends CyclicBehaviour
{

    private NetworkAgent agent;

    NetworkCoverBehaviour(NetworkAgent agent)
    {
        super(agent);
        this.agent = agent;
    }

    @Override
    public void action()
    {
        ACLMessage msg = this.agent.blockingReceive();

        if (msg != null)
        {

            switch (msg.getPerformative())
            {
                case ACLMessage.REQUEST:
                    {

                    if (!this.agent.Visited)
                    {
                        this.agent.Visited = true;
                        this.agent.RequesterName = msg.getSender().getLocalName();

                        int willSendMessageCount = 0;
                        ACLMessage requestMessage = new ACLMessage(ACLMessage.REQUEST);
                        System.out.println(this.agent.getLocalName() +
                                " отправляет сообщение соседям: ");
                        for (int i = 0; i < this.agent.AgentData.neighbors.length; ++i)
                        {
                            if (!this.agent.AgentData.neighbors[i].toString().equals(this.agent.RequesterName))
                            {
                                requestMessage.addReceiver(new AID(this.agent.AgentData.neighbors[i].toString(), AID.ISLOCALNAME));
                                System.out.println(this.agent.getLocalName() +
                                        " отправляет " +
                                        this.agent.AgentData.neighbors[i]);
                                willSendMessageCount++;
                            }
                        }

                        if (willSendMessageCount > 0)
                        {
                            this.agent.SentMsgs = willSendMessageCount;
                            this.agent.send(requestMessage);
                        }
                        else
                        {
                            ACLMessage response = msg.createReply();
                            response.setPerformative(ACLMessage.INFORM);
                            response.setContent(this.agent.AgentData.value.toString());
                            this.agent.send(response);
                        }
                    }
                    else
                    {
                        ACLMessage response = msg.createReply();
                        response.setPerformative(ACLMessage.INFORM);
                        response.setContent("0");
                        this.agent.send(response);

                        System.out.println("Уже посещено, (Отправитель: " + msg.getSender().getLocalName() + ") " + this.agent.getLocalName());
                    }
                    break;
                }
                case ACLMessage.INFORM:
                    {
                    this.agent.ReceivedMsgs++;
                    this.agent.Buffer += Integer.parseInt(msg.getContent());
                    System.out.println("Сообщение от " + msg.getSender().getLocalName() +
                            " для " + this.agent.getLocalName() +
                            ", отправлено сообщений: " + this.agent.SentMsgs +
                            ", получено сообщений: " + this.agent.ReceivedMsgs +
                            ", текущее значение: " + msg.getContent());
                    if (this.agent.ReceivedMsgs == this.agent.SentMsgs)
                    {

                        int sum = this.agent.Buffer + this.agent.AgentData.value;

                        if (this.agent.AgentData.isStartingAgent)
                        {
                            double mean = (double)sum / (double)this.agent.AgentData.numberOfNodes;
                            System.out.println("---------------ОТЧЕТ-----------------");
                            System.out.println("Сумма по всем узлам: " + sum);
                            System.out.println("Количество узлов: " + this.agent.AgentData.numberOfNodes);
                            System.out.println("Среднее арифметическое: " + mean);
                        }
                        else
                        {
                            ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                            System.out.println("Агенту " + this.agent.getLocalName() +
                                    " возвратились все сообщения. Запрашивал: " +
                                    this.agent.RequesterName + "; результат: " +
                                    Integer.toString(sum));
                            response.setContent(Integer.toString(sum));
                            response.addReceiver(new AID(this.agent.RequesterName, AID.ISLOCALNAME));
                            this.agent.send(response);
                        }
                    }
                    break;
                }
            }
        }
    }
}
