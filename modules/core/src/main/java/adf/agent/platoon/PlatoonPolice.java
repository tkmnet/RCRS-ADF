package adf.agent.platoon;

import adf.agent.info.AgentInfo;
import adf.tactics.Tactics;
import adf.tactics.TacticsPolice;
import rescuecore2.messages.Command;
import rescuecore2.standard.entities.FireBrigade;
import rescuecore2.standard.entities.PoliceForce;
import rescuecore2.standard.entities.StandardEntityURN;
import rescuecore2.worldmodel.ChangeSet;

import java.util.*;

/**
 * Created by takamin on 10/12/15.
 */
public class PlatoonPolice extends Platoon<PoliceForce>
{
	public PlatoonPolice(TacticsPolice tactics, boolean isPrecompute)
	{
		super(tactics, isPrecompute);
	}

	@Override
	protected EnumSet<StandardEntityURN> getRequestedEntityURNsEnum()
	{
		return EnumSet.of(StandardEntityURN.POLICE_FORCE);
	}

	@Override
	protected void postConnect()
	{
		super.postConnect();
		agentInfo = new AgentInfo();
	}

	@Override
	protected void think(int time, ChangeSet changed, Collection<Command> heard)
	{
		super.think(time, changed, heard);
	}
}
