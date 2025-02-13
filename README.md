
This project simulates an Industrial Internet of Things (IIoT) environment using the iFogSim framework. The simulation models a smart manufacturing setup where edge computing and fog computing resources process IoT-generated data efficiently. The primary objectives are to minimize latency, optimize resource allocation, and reduce energy consumption in an industrial setting.

**Key Components:**
1. **Fog Devices:** Multiple fog nodes (industrial gateways) that act as intermediaries between IoT devices and the cloud.
2. **Cloud Server:** A centralized computing unit for high-computation tasks.
3. **Sensors & Actuators:** Temperature sensors to monitor machinery and actuators to take corrective actions based on analysis.
4. **Application Modules:** Processing tasks distributed across cloud, fog, and edge layers.
5. **Task Scheduling & Orchestration:** Ensuring efficient allocation of resources for real-time analytics and predictive maintenance.

**Simulation Workflow:**
1. **IoT Data Generation:** Sensors generate temperature data from industrial machines.
2. **Fog Node Processing:** Gateways process sensor data locally, reducing latency.
3. **Machine Learning Analysis:** A cloud-based module processes data for predictive maintenance.
4. **Actuation & Feedback:** Based on analysis, actuators adjust machinery settings in real time.
5. **Performance Evaluation:** The simulation measures latency, energy consumption, and cost-effectiveness.

**Expected Outcomes:**
- Reduction in network congestion by leveraging fog computing.
- Faster decision-making by processing data closer to the source.
- Enhanced resource management and power efficiency.
- Improved industrial automation and predictive maintenance.
