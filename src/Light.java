public class Light extends Device {
    private int brightness;

    public Light(String name, String protocol) {
        super(name, protocol);
        this.brightness = 50;
    }

    //getter & setter
    public int getBrightness(){
        return brightness;
    }

    public void setBrightness(int brightness){
        this.brightness = brightness;
    }

    @Override
    public String getInfo(){
        return name + " " + status + " " +brightness + "%" + protocol;

    }
}
