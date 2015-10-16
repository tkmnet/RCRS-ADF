package adf.agent.office;

import adf.agent.Agent;
import adf.control.Control;
import adf.util.datastorage.DataStorage;
import rescuecore2.messages.Command;
import rescuecore2.standard.entities.StandardEntity;
import rescuecore2.worldmodel.ChangeSet;

import java.util.Collection;

/**
 * Created by takamin on 10/12/15.
 */
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

		rootControl.initialize();

		switch (scenarioInfo.getMode())
		{
			case NON_PRECOMPUTE:
				rootControl.preparate();
				break;
			case PRECOMPUTED:
				rootControl.resume();
				break;
		}
	}

	@Override
	protected void think(int time, ChangeSet changed, Collection<Command> heard)
	{
		rootControl.think();
	}
}
