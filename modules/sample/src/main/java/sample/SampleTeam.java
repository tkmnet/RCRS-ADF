package sample;

import adf.team.Team;
import adf.team.tactics.TacticsAmbulance;
import adf.team.tactics.TacticsFire;
import adf.team.tactics.TacticsPolice;
import sample.tactics.SampleTacticsAmbulance;
import sample.tactics.SampleTacticsFire;
import sample.tactics.SampleTacticsPolice;
import adf.team.precompute.*;

public class SampleTeam extends Team {

    @Override
    public String getTeamName() {
        return "sample";
    }

    @Override
    public TacticsAmbulance getAmbulanceTeamTactics() {
        return new SampleTacticsAmbulance();
    }

    @Override
    public TacticsFire getFireBrigadeTactics() {
        return new SampleTacticsFire();
    }

    @Override
    public TacticsPolice getPoliceForceTactics() {
        return new SampleTacticsPolice();
    }

    @Override
    public PrecomputeAmbulance getAmbulancePrecompute() {
        return null;
    }

    @Override
    public PrecomputeFire getFirePrecompute() {
        return null;
    }

    @Override
    public PrecomputePolice getPolicePrecompute() {
        return null;
    }
}
