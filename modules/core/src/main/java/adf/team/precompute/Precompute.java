package adf.team.precompute;

import adf.util.compatibility.AgentData;
import adf.util.compatibility.WorldData;
import comlib.manager.MessageManager;
import rescuecore2.config.Config;
import rescuecore2.standard.entities.Human;

public abstract class Precompute<H extends Human, A extends AgentData<H>> {

    public WorldData world;
    public A agent;
    public Config config;

    public long startProcessTime;

    public abstract void preparation(MessageManager messageManager);

    public abstract void compute(int currentTime, MessageManager manager);

}
