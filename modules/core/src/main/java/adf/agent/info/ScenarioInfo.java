package adf.agent.info;

import rescuecore2.config.Config;

/**
 * Created by takamin on 10/12/15.
 */
public class ScenarioInfo
{
	public enum Mode
	{
		NON_PRECOMPUTE,
		PRECOMPUTED,
		PRECOMPUTATION_PHASE
	}

	Config config;
	Mode mode;

	public ScenarioInfo(Config config, Mode mode)
	{
		this.config = config;
		this.mode = mode;
	}

	public ScenarioInfo(Config config)
	{
		this(config, Mode.NON_PRECOMPUTE);
	}

	public void setConfig(Config config)
	{
		this.config = config;
	}

	public Mode getMode()
	{
		return mode;
	}
}
