package device;

import enums.DeviceType;

import java.time.LocalDateTime;

public class SmartTrafficLight extends SmartDeviceImpl {
    private static final DeviceType type = DeviceType.TRAFFIC_LIGHT;
    private static int countOfDevices = 0;

    public SmartTrafficLight(String name, double powerConsumption, LocalDateTime installationDateTime) {
        super(name, powerConsumption, installationDateTime);
        countOfDevices++;
    }

    @Override
    public int getCountOfDevices() {
        return countOfDevices;
    }

    @Override
    public DeviceType getType() {
        return type;
    }
}
