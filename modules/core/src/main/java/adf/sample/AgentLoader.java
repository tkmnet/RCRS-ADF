package adf.sample;

import adf.launcher.AbstractLoader;
import adf.sample.ambulance.tactics.MyTacticsAmbulance;
import adf.sample.fire.tactics.MyTacticsFire;
import adf.sample.police.tactics.MyTacticsPolice;
import adf.tactics.TacticsAmbulance;
import adf.tactics.TacticsFire;
import adf.tactics.TacticsPolice;

/**
 * Created by takamin on 10/14/15.
 */
public class AgentLoader extends AbstractLoader
{
	@Override
	public TacticsAmbulance getTacticsAmbulance()
	{
		return new MyTacticsAmbulance();
	}

	@Override
	public TacticsFire getTacticsFire()
	{
		return new MyTacticsFire();
	}

	@Override
	public TacticsPolice getTacticsPolice()
	{
		return new MyTacticsPolice();
	}
}
