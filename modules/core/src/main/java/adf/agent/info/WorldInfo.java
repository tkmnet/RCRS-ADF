package adf.agent.info;

import rescuecore2.standard.entities.StandardWorldModel;

public class WorldInfo
{
	public StandardWorldModel world;

	public WorldInfo(StandardWorldModel world)
	{
		this.world = world;
	}

	public void setWorld(StandardWorldModel world)
	{
		this.world = world;
	}
}
