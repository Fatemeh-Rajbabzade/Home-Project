public class Rule {
    private String deviceName;
    private String time;
    private String action;

    public Rule(String deviceName, String time, String action){
        this.deviceName = deviceName;
        this.time = time;
        this.action = action;
    }

    //getter
    public String getDeviceName(){
        return deviceName;
    }

    public String getTime(){
        return time;
    }

    public String getAction(){
        return action;
    }

    public String getRuleInfo(){
        return deviceName + " " + time + action;
    }
}
