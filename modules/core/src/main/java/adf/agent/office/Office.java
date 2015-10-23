package adf.agent.office;

import adf.agent.Agent;
import adf.control.Control;
import adf.util.datastorage.DataStorage;
import rescuecore2.messages.Command;
import rescuecore2.standard.entities.StandardEntity;
import rescuecore2.worldmodel.ChangeSet;

import java.util.Collection;

public abstract class Office<E extends StandardEntity> extends Agent<E>
{
	Control rootControl;

	public Office(Control control, boolean isPrecompute, String datastorageName)
	{
		super(isPrecompute, datastorageName);
		this.rootControl = control;
	}

	@Override
	protected void postConnect()
	{
		super.postConnect();
		//model.indexClass(StandardEntityURN.ROAD);
		//distance = config.getIntValue(DISTANCE_KEY);

		rootControl.initialize(agentInfo, worldInfo, scenarioInfo);

		switch (scenarioInfo.getMode())
		{
			case NON_PRECOMPUTE:
				rootControl.preparate(agentInfo, worldInfo, scenarioInfo);
				break;
			case PRECOMPUTED:
				rootControl.resume(agentInfo, worldInfo, scenarioInfo, dataStorage);
				break;
			default:
				shutdown();
		}
	}

	protected void think()
	{
		rootControl.think(agentInfo, worldInfo, scenarioInfo);
	}
}
