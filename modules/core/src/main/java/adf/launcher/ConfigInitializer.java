package adf.launcher;

import adf.launcher.option.*;
import rescuecore2.config.Config;
import rescuecore2.config.ConfigException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigInitializer
{

	public static Config getConfig(File configPath, String[] args)
	{
		Config commandLine = analysis(args);
		//File configDir = new File(System.getProperty("user.dir"), "config");
		if (!configPath.exists())
		{
			if (!configPath.mkdir())
			{
				return commandLine;
			}
		}
		try
		{
			Config config = new Config(configPath);
			config.merge(commandLine);
			return config;
		}
		catch (ConfigException e)
		{
			e.printStackTrace();
		}
		return commandLine;
	}

	public static Config analysis(String[] args)
	{
		Config config = new Config();
		Map<String, Option> options = initOption();
		for (String str : args)
		{
			String[] strArray = str.split(":");
			Option option = options.get(strArray[0]);
			if (option != null)
			{
				option.setValue(config, strArray);
			}
		}
		return config;
	}

	private static Map<String, Option> initOption()
	{
		Map<String, Option> options = new HashMap<>();
		registerOption(options, new OptionServer());
		registerOption(options, new OptionHost());
		registerOption(options, new OptionTeam());
		registerOption(options, new OptionPrecompute());
		registerOption(options, new OptionAmbulanceTeam());
		registerOption(options, new OptionFireBrigade());
		registerOption(options, new OptionPoliceForce());
		registerOption(options, new OptionAmbulanceCentre());
		registerOption(options, new OptionFireStation());
		registerOption(options, new OptionPoliceOffice());
		return options;
	}

	private static void registerOption(Map<String, Option> options, Option option)
	{
		options.put(option.getKey(), option);
	}
}
