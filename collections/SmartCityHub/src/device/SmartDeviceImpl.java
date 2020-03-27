package device;

import java.time.LocalDateTime;

public abstract class SmartDeviceImpl implements SmartDevice {
    private String id;
    private String name;
    private double powerConsumption;
    private LocalDateTime installationDateTime;

    public SmartDeviceImpl(String name, double powerConsumption, LocalDateTime installationDateTime) {
        this.id = this.getType().getShortName() + "-" + name + "-" + this.getCountOfDevices();
        this.name = name;
        this.powerConsumption = powerConsumption;
        this.installationDateTime = installationDateTime;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPowerConsumption() {
        return this.powerConsumption;
    }

    @Override
    public LocalDateTime getInstallationDateTime() {
        return this.installationDateTime;
    }

    @Override
    public int hashCode() {
        return 42 * this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        SmartDeviceImpl device = (SmartDeviceImpl) obj;
        return this.id.equals(device.id);
    }

    public abstract int getCountOfDevices();

}
