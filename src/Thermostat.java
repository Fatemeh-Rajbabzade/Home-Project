public class Thermostat extends Device{
    private int temperature;

    public Thermostat(String name, String protocol){
        super(name, protocol);
        this.temperature = 20;
    }

    //getter & setter
    public int getTemperature(){
        return temperature;
    }

    public void setTemperature(int temperature){
        this.temperature = temperature;
    }

    @Override
    public String getInfo(){
        return name + " " + status + " " + temperature +"C "  + protocol;
    }
}
