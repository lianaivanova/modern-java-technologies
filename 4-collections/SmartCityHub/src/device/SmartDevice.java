package device;

import enums.DeviceType;

import java.time.LocalDateTime;

public interface SmartDevice {

    String getId();

    String getName();

    double getPowerConsumption();

    LocalDateTime getInstallationDateTime();

    DeviceType getType();
}
