package hub;

public class DeviceAlreadyRegisteredException extends Exception {
    public DeviceAlreadyRegisteredException() {
        super("This device is already registered.");
    }
}
