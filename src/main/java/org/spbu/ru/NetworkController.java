package org.spbu.ru;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.ContainerController;

class NetworkController
{

    private NetworkGraph networkGraph;
    private final String host = "localhost";
    private final String port = "9000";

    NetworkController(NetworkGraph networkGraph)
    {
        this.networkGraph = networkGraph;
    }

    void initAgents()
    {
        Runtime jadeRuntime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, host);
        profile.setParameter(Profile.MAIN_PORT, port);
        ContainerController containerController = jadeRuntime.createMainContainer(profile);

        try
        {
            for (int i = 1; i <= this.networkGraph.getNodeNumber(); ++i)
            {
                System.out.println(i);
                AgentData[] payloadToAgent = {
                        new AgentData(this.networkGraph.getNodeWeight()[i],
                                this.networkGraph.getEdges()[i],
                                this.networkGraph.getNodeNumber(),
                                (i == this.networkGraph.getNodeNumber()))
                };
                jade.wrapper.AgentController agent = containerController.createNewAgent(Integer.toString(i),
                        "org.spbu.ru.NetworkAgent",
                        payloadToAgent);

                agent.start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}