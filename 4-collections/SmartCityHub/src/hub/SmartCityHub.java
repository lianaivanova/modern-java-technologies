package hub;

import device.SmartDevice;
import enums.DeviceType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;


public class SmartCityHub {
    private Set<SmartDevice> devices;
    private Map<DeviceType, Integer> countOfDevices;

    public SmartCityHub() {
        devices = new LinkedHashSet<>();
        countOfDevices = new TreeMap<>();
        countOfDevices.put(DeviceType.CAMERA, 0);
        countOfDevices.put(DeviceType.TRAFFIC_LIGHT, 0);
        countOfDevices.put(DeviceType.LAMP, 0);
    }

    public void register(SmartDevice device) throws DeviceAlreadyRegisteredException {
        if (device == null) {
            throw new IllegalArgumentException();
        }
        if (this.devices.contains(device)) {
            throw new DeviceAlreadyRegisteredException();
        }
        this.devices.add(device);
        int count = countOfDevices.get(device.getType());
        this.countOfDevices.put(device.getType(), count + 1);
    }


    public void unregister(SmartDevice device) throws DeviceNotFoundException {
        if (device == null) {
            throw new IllegalArgumentException();
        }
        if (this.devices.contains(device)) {
            this.devices.remove(device);
            this.countOfDevices.put(device.getType(), this.countOfDevices.get(device.getType()) - 1);
        } else {
            throw new DeviceNotFoundException();
        }
    }


    public SmartDevice getDeviceById(String id) throws DeviceNotFoundException {
        if (id == null) {
            throw new IllegalArgumentException();
        }
        for (SmartDevice currDevice : this.devices) {
            if (currDevice.getId().equals(id)) {
                return currDevice;
            }
        }
        throw new DeviceNotFoundException();
    }

    public int getDeviceQuantityPerType(DeviceType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        return countOfDevices.get(type);
    }

    public Collection<String> getTopNDevicesByPowerConsumption(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        Set<SmartDevice> consumption = new TreeSet<>((o1, o2) -> {
            double currConsumption = Duration.between(LocalDateTime.now(), o1.getInstallationDateTime()).toHours() * o1.getPowerConsumption();
            double otherConsumption = Duration.between(LocalDateTime.now(), o2.getInstallationDateTime()).toHours() * o2.getPowerConsumption();
            if (currConsumption - otherConsumption < 0) {
                return -1;
            } else if (currConsumption - otherConsumption > 0) {
                return 1;
            } else {
                return 0;
            }
        });
        consumption.addAll(devices);

        Iterator value = consumption.iterator();
        List<String> devicesIDs = new LinkedList<>();
        int size = Math.min(n, consumption.size());
        for (int i = 0; i < size; i++) {
            String id = ((SmartDevice) value.next()).getId();
            devicesIDs.add(id);
        }
        return devicesIDs;
    }

    public Collection<SmartDevice> getFirstNDevicesByRegistration(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        if (n > devices.size()) {
            return new LinkedHashSet<SmartDevice>(devices);
        }
        Set<SmartDevice> subSet = new LinkedHashSet<>();
        Iterator value = devices.iterator();
        for (int i = 0; i < n; i++) {
            subSet.add((SmartDevice) value.next());
        }
        return subSet;
    }

}
