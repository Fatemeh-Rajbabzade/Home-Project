public class Bluetooth implements Protocol {

    public void connect() {
        System.out.println("Connecting  Bluetooth");
    }

    public String getProtocolType() {
        return "Bluetooth";
    }
}
