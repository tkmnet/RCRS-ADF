package adf.agent.office;

import adf.agent.info.AgentInfo;
import adf.control.Control;
import adf.control.ControlAmbulance;
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
public class OfficeAmbulance extends Office<Building>
{
	public OfficeAmbulance(ControlAmbulance control, boolean isPrecompute)
	{
		super(control, isPrecompute, DATASTORAGE_FILE_NAME_AMBULANCE);
	}

	@Override
	protected EnumSet<StandardEntityURN> getRequestedEntityURNsEnum()
	{
		return EnumSet.of(StandardEntityURN.AMBULANCE_CENTRE);
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
