package device;

import enums.DeviceType;

import java.time.LocalDateTime;

public class SmartLamp extends SmartDeviceImpl {
    private static final DeviceType type = DeviceType.LAMP;
    private static int countOfDevices = 0;

    public SmartLamp(String name, double powerConsumption, LocalDateTime installationDateTime) {
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
