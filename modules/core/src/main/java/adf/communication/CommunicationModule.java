package adf.communication;


import adf.agent.Agent;
import adf.agent.info.AgentInfo;

/**
 * Created by takamin on 11/16/15.
 */
abstract public class CommunicationModule
{
    abstract public void receive(Agent agent);
    abstract public void send(Agent agent);
}
