package adf.communication;


import adf.agent.Agent;
import adf.agent.info.AgentInfo;

/**
 * Created by takamin on 11/16/15.
 */
abstract public class CommunicationModule
{
    abstract void receive(AgentInfo agentInfo);
    abstract void send(Agent agent);
}
