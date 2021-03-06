package adf.launcher;

import adf.launcher.connect.*;
import com.google.common.collect.Lists;
import rescuecore2.Constants;
import rescuecore2.components.ComponentLauncher;
import rescuecore2.components.TCPComponentLauncher;
import rescuecore2.config.Config;
import rescuecore2.registry.Registry;
import rescuecore2.standard.entities.StandardEntityFactory;
import rescuecore2.standard.entities.StandardPropertyFactory;
import rescuecore2.standard.messages.StandardMessageFactory;

import java.io.File;
import java.net.URLClassLoader;
import java.util.List;

public class AgentConnector
{
	public static final File DIRECTORY_CONFIG = new File(System.getProperty("user.dir"), "config");
	public static final File DIRECTORY_TACTICS = new File(System.getProperty("user.dir"), "tactics");

	private Config config;

	private AbstractLoader loader;

	private List<Connector> connectorList;

	public AgentConnector(String[] args)
	{
		this(DIRECTORY_CONFIG, DIRECTORY_TACTICS, args);
	}

	public AgentConnector(File configPath, File tacticsPath, String[] args)
	{
		this.init(configPath, tacticsPath, args);
	}

	private void init(File configPath, File tacticsPath, String[] args)
	{
		//register rescue system
		Registry.SYSTEM_REGISTRY.registerEntityFactory(StandardEntityFactory.INSTANCE);
		Registry.SYSTEM_REGISTRY.registerMessageFactory(StandardMessageFactory.INSTANCE);
		Registry.SYSTEM_REGISTRY.registerPropertyFactory(StandardPropertyFactory.INSTANCE);
		//init config
		this.config = ConfigInitializer.getConfig(configPath, args);
		URLClassLoader classLoader = (URLClassLoader)this.getClass().getClassLoader();
		try
		{
			Class c = classLoader.loadClass(this.config.getValue(ConfigKey.KEY_LOADER_CLASS));
			Object classObj = c.newInstance();
			if (classObj instanceof AbstractLoader)
			{
				loader = (AbstractLoader)classObj;
			}
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{
			System.out.println("[ERROR ] Loader not found.");
		}
		//load team jar
		//this.loader = new TeamLoader(tacticsPath);
		this.connectorList = Lists.newArrayList(
						new ConnectorAmbulanceTeam(),
						new ConnectorFireBrigade(),
						new ConnectorPoliceForce(),
						new ConnectorAmbulanceCentre(),
						new ConnectorFireStation(),
						new ConnectorPoliceOffice()
		);
		//this.config.getArrayValue("test").forEach(System.out::println);
	}

	public void start()
	{
		String host = this.config.getValue(Constants.KERNEL_HOST_NAME_KEY, Constants.DEFAULT_KERNEL_HOST_NAME);
		int port = this.config.getIntValue(Constants.KERNEL_PORT_NUMBER_KEY, Constants.DEFAULT_KERNEL_PORT_NUMBER);
		ComponentLauncher launcher = new TCPComponentLauncher(host, port, this.config);
		System.out.println("[START ] Connect Server (host:" + host + ", port:" + port + ")");
		//URLClassLoader loader = new URLClassLoader(this.config.getValue(ConfigKey.KEY_LOADER_CLASS)); //(URLClassLoader) this.getClass().getClassLoader();
		for (Connector connector : this.connectorList)
		{
			connector.connect(launcher, this.config, loader);
		}
		System.out.println("[END   ] Success Connect Server");

		if (this.config.getBooleanValue(ConfigKey.KEY_PRECOMPUTE, false))
		{
			System.exit(0);
		}
	}
}
