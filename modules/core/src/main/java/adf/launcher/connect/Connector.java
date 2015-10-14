package adf.launcher.connect;

import adf.launcher.AbstractTeam;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.config.Config;

public interface Connector
{
	public abstract void connect(ComponentLauncher launcher, Config config, AbstractTeam team);
}
