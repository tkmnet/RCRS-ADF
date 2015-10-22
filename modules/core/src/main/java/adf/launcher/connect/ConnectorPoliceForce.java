package adf.launcher.connect;

import adf.agent.platoon.PlatoonPolice;
import adf.launcher.ConfigKey;
import adf.tactics.TacticsPolice;
import rescuecore2.components.ComponentConnectionException;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.config.Config;
import rescuecore2.connection.ConnectionException;

import java.net.URLClassLoader;

public class ConnectorPoliceForce implements Connector
{

	@Override
	public void connect(ComponentLauncher launcher, Config config, URLClassLoader loader) {
		int count = config.getIntValue(ConfigKey.KEY_POLICE_FORCE_COUNT, 0);
		int connected = 0;

		String classStr = config.getValue(ConfigKey.KEY_POLICE_FORCE_NAME);
		if(classStr == null) {
			System.out.println("[ERROR] Cannot Load PoliceForce Tactics !!");
			return;
		}
		System.out.println("[START] Connect PoliceForce (teamName:" + classStr + ")");
		System.out.println("[INFO ] Load PoliceForce (teamName:" + classStr + ")");

		try {
			Class c = loader.loadClass(classStr);
			if(c == null) {
				System.out.println("[ERROR] Cannot Load PoliceForce Tactics !!");
				return;
			}
			for (int i = 0; i != count; ++i) {
				Object obj = c.newInstance();
				if(obj instanceof TacticsPolice) {
					boolean isPrecompute = config.getBooleanValue(ConfigKey.KEY_PRECOMPUTE, false);
					launcher.connect(new PlatoonPolice((TacticsPolice)obj, isPrecompute));
					//System.out.println(name);
					connected++;
				}
			}
		}
		catch (ComponentConnectionException | InterruptedException | ConnectionException ignored) {
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("[END  ] Connect PoliceForce (success:" + connected + ")");
	}
}
