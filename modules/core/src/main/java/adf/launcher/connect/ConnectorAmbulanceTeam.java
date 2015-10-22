package adf.launcher.connect;

import adf.agent.platoon.PlatoonAmbulance;
import adf.launcher.ConfigKey;
import adf.tactics.TacticsAmbulance;
import rescuecore2.components.ComponentConnectionException;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.config.Config;
import rescuecore2.connection.ConnectionException;

import java.net.URLClassLoader;

public class ConnectorAmbulanceTeam implements Connector
{

	@Override
	public void connect(ComponentLauncher launcher, Config config, URLClassLoader loader) {
		int count = config.getIntValue(ConfigKey.KEY_AMBULANCE_TEAM_COUNT, 0);
		int connected = 0;

		String classStr = config.getValue(ConfigKey.KEY_AMBULANCE_TEAM_NAME);
		if(classStr == null) {
			System.out.println("[ERROR] Cannot Load AmbulanceTeam Tactics !!");
			return;
		}
		System.out.println("[START] Connect AmbulanceTeam (teamName:" + classStr + ")");
		System.out.println("[INFO ] Load AmbulanceTeam (teamName:" + classStr + ")");

		try {
			Class c = loader.loadClass(classStr);
			if(c == null) {
				System.out.println("[ERROR] Cannot Load AmbulanceTeam Tactics !!");
				return;
			}
			for (int i = 0; i != count; ++i) {
				Object obj = c.newInstance();
				if(obj instanceof TacticsAmbulance) {
					boolean isPrecompute = config.getBooleanValue(ConfigKey.KEY_PRECOMPUTE, false);
					launcher.connect(new PlatoonAmbulance((TacticsAmbulance)obj, isPrecompute));
					//System.out.println(name);
					connected++;
				}
			}
		}
		catch (ComponentConnectionException | InterruptedException | ConnectionException ignored) {
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("[END  ] Connect AmbulanceTeam (success:" + connected + ")");
	}
}
