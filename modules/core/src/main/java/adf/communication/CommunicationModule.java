package adf.communication;


import adf.agent.Agent;
import adf.agent.info.AgentInfo;

abstract public class CommunicationModule
{
    abstract public void receive(Agent agent, MessageManager messageManager) throws NoSuchMethodException;
    abstract public void send(Agent agent, MessageManager messageManager);
}
