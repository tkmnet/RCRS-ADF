package adf.team.precompute;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ComputeData {

    public static final int TYPE_AMBULANCE = 0;
    public static final int TYPE_FIRE = 1;
    public static final int TYPE_POLICE = 2;

    private boolean isWrite;
    private int agentType;

    private Map<String, Integer> intMap;
    private Map<String, Long> longMap;
    private Map<String, Double> doubleMap;
    private Map<String, String> stringMap;
    private Map<String, Serializable> objMap;

    public ComputeData(int type, boolean readOnly) {
        this.agentType = type;
        this.isWrite = !readOnly;
        this.intMap = new HashMap<>();
        this.longMap = new HashMap<>();
        this.doubleMap = new HashMap<>();
        this.stringMap = new HashMap<>();
        this.objMap = new HashMap<>();
        this.init(readOnly);
    }

    private void init(boolean readOnly) {
        if(readOnly) {

        }
    }

}
