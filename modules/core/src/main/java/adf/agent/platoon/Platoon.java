package adf.agent.platoon;

import adf.agent.Agent;
import adf.tactics.Tactics;
import rescuecore2.messages.Command;
import rescuecore2.standard.entities.StandardEntity;
import rescuecore2.worldmodel.ChangeSet;

import java.util.Collection;

/**
 * Created by takamin on 10/12/15.
 */
public abstract class Platoon<E extends StandardEntity> extends Agent<E>
{
	Tactics rootTactics;

	public Platoon(Tactics tactics, boolean isPrecompute, String dataStorageName)
	{
		super(isPrecompute, dataStorageName);
		this.rootTactics = tactics;
	}

	@Override
	protected void postConnect()
	{
		super.postConnect();
		//model.indexClass(StandardEntityURN.ROAD);
		//distance = config.getIntValue(DISTANCE_KEY);

		rootTactics.initialize();

		switch (scenarioInfo.getMode())
		{
			case NON_PRECOMPUTE:
				rootTactics.preparate();
				break;
			case PRECOMPUTATION_PHASE:
				rootTactics.precompute();
				break;
			case PRECOMPUTED:
				rootTactics.resume();
				break;
		}
	}

	@Override
	protected void think(int time, ChangeSet changed, Collection<Command> heard)
	{
		rootTactics.think();
	}
}
