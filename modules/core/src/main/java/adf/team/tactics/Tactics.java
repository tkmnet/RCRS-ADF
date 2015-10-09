package adf.team.tactics;

import adf.agent.action.Action;
import adf.util.compatibility.AgentData;
import adf.util.compatibility.WorldData;
import comlib.manager.MessageManager;
import rescuecore2.config.Config;
import rescuecore2.standard.entities.Human;

public abstract class Tactics<H extends Human, A extends AgentData<H>> {

    public WorldData world;
    public A agent;
    public Config config;

    public long startProcessTime;

    public abstract void preparation(MessageManager messageManager);

    public abstract Action think(int currentTime, MessageManager manager);

    public void ignoreTimeThink(int currentTime, MessageManager manager) {
    }

    public void registerProvider(MessageManager manager) {
    }
}
