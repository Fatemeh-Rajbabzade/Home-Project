import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SystemController controller = new SystemController();

        int q = Integer.parseInt(scanner.nextLine());

        // استفاده از StringBuilder برای ذخیره خروجی‌ها
        StringBuilder outputBuilder = new StringBuilder();

        for (int i = 0; i < q; i++) {
            // extra lines
            String inputLine = scanner.nextLine().trim();
            if (inputLine.isEmpty()) {
                i--;
                continue;
            }

            String[] input = inputLine.split(" ");
            String inputCommand = input[0];

            switch (inputCommand) {
                case "add_device":
                    String type = input[1];
                    String name = input[2];
                    String protocol = input[3];
                    outputBuilder.append(controller.addDevice(type, name, protocol)).append("\n");
                    break;

                case "set_device":
                    String deviceName = input[1];
                    String property = input[2];
                    String value = input[3];
                    outputBuilder.append(controller.setDevice(deviceName, property, value)).append("\n");
                    break;

                case "remove_device":
                    String removeName = input[1];
                    outputBuilder.append(controller.removeDevice(removeName)).append("\n");
                    break;

                case "list_devices":
                    outputBuilder.append(controller.listDevices()).append("\n");
                    break;

                case "add_rule":
                    String ruleName = input[1];
                    String time = input[2];
                    String action = input[3];
                    outputBuilder.append(controller.addRule(ruleName, time, action)).append("\n");
                    break;

                case "check_rules":
                    String checkTime = input[1];
                    outputBuilder.append(controller.checkRules(checkTime)).append("\n");
                    break;

                case "list_rules":
                    outputBuilder.append(controller.listRules()).append("\n");
                    break;

                default:
                    outputBuilder.append("Invalid command").append("\n");
                    break;
            }
        }

        // چاپ همه خروجی‌ها
        System.out.print(outputBuilder.toString());
        scanner.close();
    }
}
