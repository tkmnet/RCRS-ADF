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
	final protected static String DATASTORAGE_FILE_NAME_AMBULANCE = "ambulance.bin";
	final protected static String DATASTORAGE_FILE_NAME_FIRE = "fire.bin";
	final protected static String DATASTORAGE_FILE_NAME_POLICE = "police.bin";

	ScenarioInfo.Mode mode;
	public AgentInfo agentInfo;
	public WorldInfo worldInfo;
	public ScenarioInfo scenarioInfo;
	protected DataStorage dataStorage;
	protected  boolean isPrecompute;

	public Agent(boolean isPrecompute, String dataStorageName)
	{
		this.isPrecompute = isPrecompute;

		if (isPrecompute)
		{
			DataStorage.removeData(dataStorageName);
			this.mode = ScenarioInfo.Mode.PRECOMPUTATION_PHASE;
		}

		dataStorage = new DataStorage(dataStorageName);

		if (!isPrecompute)
		{
			if (dataStorage.isReady())
			{
				this.mode = ScenarioInfo.Mode.PRECOMPUTED;
			}
			else
			{
				this.mode = ScenarioInfo.Mode.NON_PRECOMPUTE;
			}
		}
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

		this.agentInfo = new AgentInfo();
		this.worldInfo = new WorldInfo(model);
		this.scenarioInfo = new ScenarioInfo(config, mode);
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
