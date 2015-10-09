package adf.agent.action;

import adf.team.tactics.Tactics;
import rescuecore2.messages.Message;
import rescuecore2.worldmodel.EntityID;

public abstract class Action {

    private EntityID userID;

    public Action(Tactics tactics) {
        this.userID = tactics.agent.agentID;
    }

    public EntityID getUserID() {
        return this.userID;
    }

    public abstract Message getCommand(EntityID agentID, int time);
}