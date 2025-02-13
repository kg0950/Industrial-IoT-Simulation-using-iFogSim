import org.fog.application.Application;
import org.fog.entities.FogDevice;
import org.fog.entities.Sensor;
import org.fog.entities.Actuator;
import org.fog.placement.Controller;
import org.fog.placement.ModulePlacementEdgewards;
import org.fog.utils.FogLinearPowerModel;
import org.fog.utils.Config;
import org.cloudbus.cloudsim.core.CloudSim;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IndustrialIoTSimulation {
    
    public static void main(String[] args) {
        Config.ENABLE_OUTPUT = true;
        Config.CLOUD_DELAY = 100; // Cloud processing delay
        
        int numFogNodes = 3; // Number of Fog nodes (Gateways)
        CloudSim.init(1, Calendar.getInstance(), false);
        
        List<FogDevice> fogDevices = createFogDevices(numFogNodes);
        Application application = createApplication("IIoT_APP");
        
        Controller controller = new Controller("IIoT_Controller", fogDevices, new ArrayList<>(), application);
        controller.submitApplication(application, new ModulePlacementEdgewards(fogDevices, new ArrayList<>(), application));
        
        CloudSim.startSimulation();
        CloudSim.stopSimulation();
    }
    
    private static List<FogDevice> createFogDevices(int numFogNodes) {
        List<FogDevice> fogDevices = new ArrayList<>();
        
        FogDevice cloud = createFogDevice("Cloud", 50000, 40000, 100, 10000, 0);
        fogDevices.add(cloud);
        
        for (int i = 0; i < numFogNodes; i++) {
            FogDevice gateway = createFogDevice("FogNode" + i, 10000, 8000, 10, 1000, 1);
            gateway.setParentId(cloud.getId());
            fogDevices.add(gateway);
            
            Sensor sensor = new Sensor("Sensor" + i, "TEMP", gateway.getId(), "IIoT_APP");
            Actuator actuator = new Actuator("Actuator" + i, gateway.getId(), "IIoT_APP");
        }
        return fogDevices;
    }
    
    private static FogDevice createFogDevice(String name, int mips, int ram, int upBw, int downBw, int level) {
        FogDevice device = new FogDevice(name, mips, ram, upBw, downBw, 0, 0.01, 100, 0.1);
        device.setLevel(level);
        device.setPowerModel(new FogLinearPowerModel(100, 50));
        return device;
    }
    
    private static Application createApplication(String appId) {
        Application application = Application.createApplication(appId, 1);
        application.addAppModule("SensorProcessing", 10);
        application.addAppModule("MachineLearning", 30);
        
        application.addAppEdge("TEMP", "SensorProcessing", 1000, 100, "TEMP", Tuple.UP, AppEdge.SENSOR);
        application.addAppEdge("SensorProcessing", "MachineLearning", 3000, 500, "ML_ANALYSIS", Tuple.UP, AppEdge.MODULE);
        application.addAppEdge("MachineLearning", "Actuator", 500, 100, "ACTUATE", Tuple.DOWN, AppEdge.ACTUATOR);
        
        return application;
    }
}
