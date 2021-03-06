package adf.util.map;

import adf.util.compatibility.WorldData;
import com.google.common.base.MoreObjects;
import rescuecore2.misc.Pair;
import rescuecore2.standard.entities.*;
import rescuecore2.worldmodel.EntityID;

import java.util.HashSet;
import java.util.Set;

public class RouteNode {

    public final EntityID nodeID;

    public final Pair<Integer, Integer> position;

    public final Boolean isRoad;

    public boolean passable;

    private Set<EntityID> neighbours;

    private RouteNode(WorldData world, Road road) {
        this.nodeID     = road.getID();
        this.position   = world.getLocation(road);
        this.isRoad     = Boolean.TRUE;
        this.passable   = true;
        this.neighbours = new HashSet<>();
    }

    private RouteNode(WorldData world, Building building) {
        this.nodeID     = building.getID();
        this.position   = world.getLocation(building);
        this.isRoad     = Boolean.FALSE;
        this.passable   = true;
        this.neighbours = new HashSet<>();
    }

    private RouteNode(RouteNode original) {
        this.nodeID     = original.nodeID;
        this.position   = original.position;
        this.isRoad     = original.isRoad;
        this.passable   = original.passable;
        this.neighbours = new HashSet<>(original.getNeighbours());
    }

    public static RouteNode getInstance(WorldData world, Road road) {
        return world != null && road != null ? new RouteNode(world, road) : null;
    }

    public static RouteNode getInstance(WorldData world, Building building) {
        return world != null && building != null ? new RouteNode(world, building) : null;
    }

    public static RouteNode getInstance(WorldData world, Area area) {
        if(world != null && area != null) {
            if(area instanceof Road) {
                return new RouteNode(world, (Road)area);
            }
            if(area instanceof Building) {
                return new RouteNode(world, (Building)area);
            }
        }
        return null;
    }

    public static RouteNode getInstance(WorldData world, EntityID areaID) {
        if(world != null && areaID != null) {
            StandardEntity area = world.getEntity(areaID);
            if(area instanceof Road) {
                return new RouteNode(world, (Road)area);
            }
            if(area instanceof Building) {
                return new RouteNode(world, (Building)area);
            }
        }
        return null;
    }

    public static RouteNode copy(RouteNode original) {
        return original != null ? new RouteNode(original) : null;
    }

    public Set<EntityID> getNeighbours() {
        return this.neighbours;
    }

    public boolean isNeighbourNode(RouteNode node) {
        return this.isNeighbourNode(node.nodeID);
    }

    public boolean isNeighbourNode(EntityID nodeID) {
        return this.neighbours.contains(nodeID);
    }

    public RouteNode addNeighbour(RouteNode node) {
        return this.addNeighbour(node.nodeID);
    }

    public RouteNode addNeighbour(EntityID id) {
        this.neighbours.add(id);
        return this;
    }

    public RouteNode removeNeighbour(RouteNode node) {
        return this.removeNeighbour(node.nodeID);
    }

    public RouteNode removeNeighbour(EntityID id) {
        this.neighbours.remove(id);
        return this;
    }

    public int getX() {
        return this.position.first();
    }

    public int getY() {
        return this.position.second();
    }

    public boolean isSingleNode() {
        if(this.neighbours.isEmpty()) {
            return true;
        }
        for(EntityID id : this.neighbours) {
            if(this.nodeID.getValue() != id.getValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return this.nodeID.getValue();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RouteNode && this.equals((RouteNode)o);
    }

    public boolean equals(RouteNode node) {
        return this.nodeID.getValue() == node.nodeID.getValue();
    }

    @Override
    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("nodeID", this.nodeID.getValue())
                .add("posX", this.position.first())
                .add("posY", this.position.second())
                .add("isRoad", this.isRoad)
                .add("passable", this.passable)
                .add("neighbours", this.neighbours)
                .toString();
    }
}
