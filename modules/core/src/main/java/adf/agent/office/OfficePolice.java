package adf.agent.office;

import adf.agent.info.AgentInfo;
import adf.control.Control;
import adf.control.ControlPolice;
import adf.util.datastorage.DataStorage;
import rescuecore2.messages.Command;
import rescuecore2.standard.entities.Building;
import rescuecore2.standard.entities.StandardEntityURN;
import rescuecore2.worldmodel.ChangeSet;

import java.util.Collection;
import java.util.EnumSet;

/**
 * Created by takamin on 10/15/15.
 */
public class OfficePolice extends Office<Building>
{
	public OfficePolice(ControlPolice control, boolean isPrecompute)
	{
		super(control, isPrecompute, DATASTORAGE_FILE_NAME_POLICE);
	}

	@Override
	protected EnumSet<StandardEntityURN> getRequestedEntityURNsEnum()
	{
		return EnumSet.of(StandardEntityURN.POLICE_OFFICE);
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
