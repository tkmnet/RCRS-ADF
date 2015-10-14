package adf.agent;

import adf.agent.info.AgentInfo;
import adf.agent.info.ScenarioInfo;
import adf.agent.info.WorldInfo;
import adf.util.datastorage.DataStorage;
import rescuecore2.components.AbstractAgent;
import rescuecore2.standard.entities.StandardEntity;
import rescuecore2.standard.entities.StandardEntityURN;
import rescuecore2.standard.entities.StandardWorldModel;

import java.util.*;

/**
 * Created by takamin on 10/12/15.
 */
public abstract class Agent<E extends StandardEntity> extends AbstractAgent<StandardWorldModel, E>
{
	public AgentInfo agentInfo;
	public WorldInfo worldInfo;
	public ScenarioInfo scenarioInfo;
	protected DataStorage dataStorage;

	public Agent(boolean isPrecompute)
	{
	}

	@Override
	public final String[] getRequestedEntityURNs()
	{
		EnumSet<StandardEntityURN> set = getRequestedEntityURNsEnum();
		String[] result = new String[set.size()];
		int i = 0;
		for (StandardEntityURN next : set)
		{
			result[i++] = next.toString();
		}
		return result;
	}

	protected abstract EnumSet<StandardEntityURN> getRequestedEntityURNsEnum();

	@Override
	protected StandardWorldModel createWorldModel()
	{
		return new StandardWorldModel();
	}

	@Override
	protected void postConnect()
	{
		super.postConnect();
		if (shouldIndex())
		{
			model.index();
		}

		worldInfo = new WorldInfo(model);
		scenarioInfo = new ScenarioInfo(config);
	}

	protected boolean shouldIndex()
	{
		return true;
	}

	public double getX()
	{
		return me().getLocation(model).first();
	}

	public double getY()
	{
		return me().getLocation(model).second();
	}
}
