package adf.algorithm;

import adf.agent.info.AgentInfo;
import adf.agent.info.ScenarioInfo;
import adf.agent.info.WorldInfo;
import rescuecore2.worldmodel.EntityID;

import java.util.List;

/**
 * Created by uranos on 15/10/23.
 */
public class SamplePathPlanner extends PathPlanner {

    public SamplePathPlanner(WorldInfo wi, AgentInfo ai, ScenarioInfo si) {
        super(wi,ai,si);
    }

    EntityID from;



    @Override
    public List<EntityID> getResult() {
        return null;
    }

    @Override
    public void setFrom(EntityID from) {
        this.from = from;
    }

    @Override
    public void setDist(EntityID... target) {

    }
}
