package adf.communication.standard.bundle.information;

import adf.communication.standard.bundle.StandardMessage;
import adf.communication.util.BitStreamReader;
import comlib.message.MessageMap;
import comlib.message.MessageID;
import rescuecore2.standard.entities.Road;
import rescuecore2.standard.entities.Blockade;
import rescuecore2.worldmodel.EntityID;

public class MessageRoad extends StandardMessage
{
	protected int rawRoadID;
	protected int rawBlockadeID;
	protected EntityID roadID;
	protected EntityID roadBlockadeID;
	protected int blockadeRepairCost;
	protected boolean roadPassable;

	public MessageRoad(boolean isRadio, Road road, Blockade blockade, boolean isPassable)
	{
		super(isRadio);
		this.roadID = road.getID();
		if (blockade == null)
		{
			this.roadBlockadeID = new EntityID(0);
			this.blockadeRepairCost = 0;
		}
		else
		{
			this.roadBlockadeID = blockade.getID();
			this.blockadeRepairCost = blockade.getRepairCost();
		}
		this.roadPassable = isPassable;
	}

	public MessageRoad(boolean isRadio, int from, int ttl, BitStreamReader bitStreamReader)
	{
		super(isRadio, from, ttl, bitStreamReader);
		this.roadID = road.getID();
		if (blockade == null)
		{
			this.roadBlockadeID = new EntityID(0);
			this.blockadeRepairCost = 0;
		}
		else
		{
			this.roadBlockadeID = blockade.getID();
			this.blockadeRepairCost = blockade.getRepairCost();
		}
		this.roadPassable = isPassable;
	}

	public EntityID getRoadID()
	{
		if (this.roadID == null)
		{ this.roadID = new EntityID(this.rawRoadID); }

		return this.roadID;
	}

	public EntityID getBlockadeID()
	{
		if (this.roadBlockadeID == null)
		{ this.roadBlockadeID = new EntityID(this.rawBlockadeID); }

		return this.roadBlockadeID;
	}

	public int getRepairCost()
	{
		return blockadeRepairCost;
	}

	public boolean isPassable()
	{
		return roadPassable;
	}
}

