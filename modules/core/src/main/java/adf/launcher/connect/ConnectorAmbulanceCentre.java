package adf.launcher.connect;

import adf.agent.office.OfficeAmbulance;
import adf.control.ControlAmbulance;
import adf.launcher.ConfigKey;
import rescuecore2.components.ComponentConnectionException;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.config.Config;
import rescuecore2.connection.ConnectionException;

import java.net.URLClassLoader;

public class ConnectorAmbulanceCentre implements Connector
{
	@Override
	public void connect(ComponentLauncher launcher, Config config, URLClassLoader loader) {
		int count = config.getIntValue(ConfigKey.KEY_AMBULANCE_CENTRE_COUNT, 0);
		int connected = 0;

		String classStr = config.getValue(ConfigKey.KEY_AMBULANCE_CENTRE_NAME);
		if(classStr == null) {
			System.out.println("[ERROR] Cannot Load AmbulanceCentre Control !!");
			return;
		}
		System.out.println("[START] Connect AmbulanceCentre (teamName:" + classStr + ")");
		System.out.println("[INFO ] Load AmbulanceCentre (teamName:" + classStr + ")");

		try {
			Class c = loader.loadClass(classStr);
			if(c == null) {
				System.out.println("[ERROR] Cannot Load AmbulanceCentre Control !!");
				return;
			}
			for (int i = 0; i != count; ++i) {
				Object obj = c.newInstance();
				if(obj instanceof ControlAmbulance) {
					boolean isPrecompute = config.getBooleanValue(ConfigKey.KEY_PRECOMPUTE, false);
					launcher.connect(new OfficeAmbulance((ControlAmbulance)obj, isPrecompute));
					//System.out.println(name);
					connected++;
				}
			}
		}
		catch (ComponentConnectionException | InterruptedException | ConnectionException ignored) {
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println("[END  ] Connect AmbulanceCentre (success:" + connected + ")");
	}
}
