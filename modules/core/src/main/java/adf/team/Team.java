package adf.team;

import adf.team.tactics.TacticsAmbulance;
import adf.team.tactics.TacticsFire;
import adf.team.tactics.TacticsPolice;

public abstract class Team {
    public abstract TacticsAmbulance getAmbulanceTeamTactics();

    public abstract TacticsFire getFireBrigadeTactics();

    public abstract TacticsPolice getPoliceForceTactics();
}
