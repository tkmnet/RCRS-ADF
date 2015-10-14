package adf.launcher.connect;

import adf.agent.platoon.PlatoonFire;
import adf.launcher.AbstractTeam;
import adf.launcher.ConfigKey;
import rescuecore2.components.ComponentConnectionException;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.config.Config;
import rescuecore2.connection.ConnectionException;

public class ConnectorFireBrigade implements Connector
{

	@Override
	public void connect(ComponentLauncher launcher, Config config, AbstractTeam team)
	{
		int count = config.getIntValue(ConfigKey.KEY_FIRE_BRIGADE_COUNT, 0);
		int connected = 0;

		String name = team.getTeamName();
		if (name == null)
		{
			name = "no name";
		}

		System.out.println("[START] Connect FireBrigade (teamName:" + name + ")");
		System.out.println("[INFO ] Load FireBrigade (teamName:" + name + ")");

		if (team.getTacticsFire() == null)
		{
			System.out.println("[ERROR] Cannot Load FireBrigade Tactics !!");
		}
		else
		{
			try
			{
				for (int i = 0; i != count; ++i)
				{
					boolean isPrecompute = config.getBooleanValue(ConfigKey.KEY_PRECOMPUTE, false);
					launcher.connect(new PlatoonFire(team.getTacticsFire(), isPrecompute));
					System.out.println(name);
					connected++;
				}
			}
			catch (ComponentConnectionException | InterruptedException | ConnectionException ignored)
			{
			}
		}
		System.out.println("[END  ] Connect FireBrigade (success:" + connected + ")");
	}
}
