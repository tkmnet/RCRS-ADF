package adf.team.event;

import rescuecore2.standard.entities.*;

public interface FindEntityEvent {
    void find(Civilian civilian);
    void find(AmbulanceTeam ambulance);
    void find(FireBrigade fire);
    void find(PoliceForce police);

    void find(Building building);
    void find(Road road);

    void find(Blockade blockade);
}
