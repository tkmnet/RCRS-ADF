package adf.tactics;

import adf.agent.platoon.action.Action;

/**
 * Created by takamin on 10/13/15.
 */
public abstract class Tactics
{
	private Tactics parentTactics;

	public Tactics(Tactics parent)
	{
		this.parentTactics = parent;
	}

	public Tactics()
	{
		this(null);
	}

	abstract public void initialize();
	abstract public void precompute();
	abstract public void resume();
	abstract public void preparate();
	abstract public Action think();

	public Tactics getParentTactics()
	{
		return parentTactics;
	}
}
