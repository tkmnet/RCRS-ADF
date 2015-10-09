package adf.agent.action;

import adf.team.tactics.TacticsPolice;
import rescuecore2.messages.Message;
import rescuecore2.misc.geometry.Vector2D;
import rescuecore2.standard.entities.Blockade;
import rescuecore2.standard.messages.AKClear;
import rescuecore2.standard.messages.AKClearArea;
import rescuecore2.worldmodel.EntityID;

public class ActionClear extends ActionTarget {

    private boolean useOldFunction;
    private int posX;
    private int posY;

    public ActionClear(TacticsPolice tactics, EntityID targetID) {
        super(tactics, targetID);
        this.useOldFunction = true;
    }

    public ActionClear(TacticsPolice tactics, Blockade blockade) {
        this(tactics, blockade.getID());
    }

    public ActionClear(TacticsPolice tactics, Vector2D vector) {
        this(tactics, (int) (tactics.agent.getX() + vector.getX()), (int) (tactics.agent.getY() + vector.getY()));
    }

    public ActionClear(TacticsPolice tactics, int destX, int destY) {
        super(tactics, null);
        this.useOldFunction = false;
        this.posX = destX;
        this.posY = destY;
    }

    public boolean getUseOldFunction() {
        return this.useOldFunction;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    @Override
    public Message getCommand(EntityID agentID, int time)
		{
			if (this.useOldFunction)
			{ return new AKClear(agentID, time, this.target); }
			else
			{ return new AKClearArea(agentID, time, this.posX, this.posY); }
    }
}
