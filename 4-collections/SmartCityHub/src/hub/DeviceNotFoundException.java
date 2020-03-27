package hub;

public class DeviceNotFoundException extends Exception {
    public DeviceNotFoundException() {
        super("This device could not be found.");
    }
}
