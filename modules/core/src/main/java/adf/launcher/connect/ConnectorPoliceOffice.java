package adf.launcher.connect;

import adf.agent.office.OfficePolice;
import adf.control.ControlPolice;
import adf.launcher.ConfigKey;
import rescuecore2.components.ComponentConnectionException;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.config.Config;
import rescuecore2.connection.ConnectionException;

import java.net.URLClassLoader;

public class ConnectorPoliceOffice implements Connector
{

	@Override
	public void connect(ComponentLauncher launcher, Config config, URLClassLoader loader) {
		int count = config.getIntValue(ConfigKey.KEY_POLICE_OFFICE_COUNT, 0);
		int connected = 0;

		String classStr = config.getValue(ConfigKey.KEY_POLICE_OFFICE_NAME);
		if(classStr == null) {
			System.out.println("[ERROR] Cannot Load PoliceOffice Tactics !!");
			return;
		}
		System.out.println("[START] Connect PoliceOffice (teamName:" + classStr + ")");
		System.out.println("[INFO ] Load PoliceOffice (teamName:" + classStr + ")");

		try {
			Class c = loader.loadClass(classStr);
			if(c == null) {
				System.out.println("[ERROR] Cannot Load PoliceOffice Tactics !!");
				return;
			}
			for (int i = 0; i != count; ++i) {
				Object obj = c.newInstance();
				if(obj instanceof ControlPolice) {
					boolean isPrecompute = config.getBooleanValue(ConfigKey.KEY_PRECOMPUTE, false);
					launcher.connect(new OfficePolice((ControlPolice)obj, isPrecompute));
					//System.out.println(name);
					connected++;
				}
			}
		}
		catch (ComponentConnectionException | InterruptedException | ConnectionException ignored) {
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("[END  ] Connect PoliceOffice (success:" + connected + ")");
	}
}
