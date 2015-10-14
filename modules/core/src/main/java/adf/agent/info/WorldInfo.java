package adf.agent.info;

import rescuecore2.standard.entities.StandardWorldModel;

/**
 * Created by takamin on 10/12/15.
 */
public class WorldInfo
{
	StandardWorldModel world;

	public WorldInfo(StandardWorldModel world)
	{
		this.world = world;
	}

	public void setWorld(StandardWorldModel world)
	{
		this.world = world;
	}
}
