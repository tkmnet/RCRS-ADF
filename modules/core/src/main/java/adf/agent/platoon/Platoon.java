package adf.agent.platoon;

import adf.agent.Agent;
import adf.tactics.Tactics;
import rescuecore2.messages.Command;
import rescuecore2.standard.entities.StandardEntity;
import rescuecore2.worldmodel.ChangeSet;

import java.util.Collection;

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

		rootTactics.initialize(agentInfo, worldInfo, scenarioInfo);

		switch (scenarioInfo.getMode())
		{
			case NON_PRECOMPUTE:
				rootTactics.preparate(agentInfo, worldInfo, scenarioInfo);
				break;
			case PRECOMPUTATION_PHASE:
				rootTactics.precompute(agentInfo, worldInfo, scenarioInfo, dataStorage);
				dataStorage.setReady(true);
				dataStorage.write();
				shutdown();
				break;
			case PRECOMPUTED:
				rootTactics.resume(agentInfo, worldInfo, scenarioInfo, dataStorage);
				break;
			default:
				shutdown();
		}
	}

	protected void think()
	{
		rootTactics.think(agentInfo, worldInfo, scenarioInfo);
	}
}
