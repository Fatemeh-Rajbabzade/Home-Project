import java.util.*;

public class SystemController {
    private Map<String, Device> devices;
    private Map<String, List<Rule>> rules;

    public SystemController() {
        devices = new HashMap<>();
        rules = new HashMap<>();
    }


    //valid time format
    public boolean isValidTime(String time) {
        if (time.length() != 5 || time.charAt(2) != ':')
            return false;

        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));
            return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    //add devices
    public String addDevice(String type, String name, String protocol) {
        if (devices.containsKey(name))
            return "duplicate device name";

        if (type.equals("light")) {
            if (protocol.equals("WiFi") || protocol.equals("Bluetooth")) {
                devices.put(name, new Light(name, protocol));
                return "device added successfully";
            }
        } else if (type.equals("thermostat")) {
            if (protocol.equals("WiFi") || protocol.equals("Bluetooth")) {
                devices.put(name, new Thermostat(name, protocol));
                return "device added successfully";
            }
        }

        return "invalid input";
    }


    //list devices
    public String listDevices() {
        if (devices.isEmpty())
            return "";
        String result = "";
        for (Device device : devices.values()) {
            result += device.getInfo() + "\n";
        }
        return result.trim();
    }


    //setting the devices
    public String setDevice(String name, String property, String value) {
        Device device = devices.get(name);
        if (device == null)
            return "device not found";

        try {
            if (device instanceof Light) {
                if (property.equals("status")) {
                    if (value.equals("on") || value.equals("off")) {
                        device.setStatus(value);
                        return "device updated successfully";
                    }
                    return "invalid value";
                } else if (property.equals("brightness")) {
                    int brightness = Integer.parseInt(value);
                    if (brightness >= 0 && brightness <= 100) {
                        ((Light) device).setBrightness(brightness);
                        return "device updated successfully";
                    }
                    return "invalid value";
                }
            } else if (device instanceof Thermostat) {
                if (property.equals("status")) {
                    if (value.equals("on") || value.equals("off")) {
                        device.setStatus(value);
                        return "device updated successfully";
                    }
                    return "invalid value";
                } else if (property.equals("temperature")) {
                    int temperature = Integer.parseInt(value);
                    if (temperature >= 10 && temperature <= 30) {
                        ((Thermostat)device).setTemperature(temperature);
                        return "device updated successfully";
                    }
                    return "invalid value";
                }
            }
        } catch(NumberFormatException e) {
            return "invalid value";
        }

        return "invalid property";
    }


    //add rules for devices
    public String addRule(String name, String time, String action) {
        if (!devices.containsKey(name))
            return "device not found";
        if (!isValidTime(time))
            return "invalid time";
        if (!action.equals("on") && !action.equals("off"))
            return "invalid action";

        List<Rule> ruleList = rules.get(name);
        if (ruleList != null) {
            for (Rule rule : ruleList) {
                if (rule.getTime().equals(time))
                    return "duplicate rule";
            }
        } else {
            ruleList = new ArrayList<>();
            rules.put(name, ruleList);
        }

        ruleList.add(new Rule(name, time, action));
        return "rule added successfully";
    }


    // check rules
    public String checkRules(String time) {
        if (!isValidTime(time))
            return "invalid time";

        boolean anyActionChecked = false;
        for (Map.Entry<String, List<Rule>> entry : rules.entrySet()) {
            for (Rule rule : entry.getValue()) {
                if (rule.getTime().equals(time)) {
                    Device device = devices.get(rule.getDeviceName());
                    if (device != null) {
                        device.setStatus(rule.getAction());
                        anyActionChecked = true;
                    }
                }
            }
        }
        if (anyActionChecked) {
            return "rules checked";
        } else {
            return "no actions checked";
        }

    }

    //list rules
    public String listRules() {
        String result = "";
        for (List<Rule> ruleList : rules.values()) {
            for (Rule rule : ruleList) {
                result+= rule.getDeviceName() + " " + rule.getTime() + " " + rule.getAction() + "\n";
            }
        }
        return result.trim();
    }

    //remove devices
    public String removeDevice(String name) {
        if (!devices.containsKey(name))
            return "device not found";
        devices.remove(name);
        rules.remove(name);
        return "device removed successfully";
    }
}
