import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String USAGE = "Usage: IPAddr1 IPAddr2.";
    public static final String INCORRECT_IP_1 = "Incorrect IPAddr1.";
    public static final String INCORRECT_IP_2 = "Incorrect IPAddr2.";
    public static final String NO_IPS_FOUND = "There are no IP addresses found in given range.";

    public static final Integer ARGS_LENGTH = 2;
    public static final Integer IP_ADDR_BYTES_LENGTH = 4;

    public static void main(String[] args) {
        List<String> result = getIPs(args);
        for (String s : result) {
            System.out.println(s);
        }
    }

    static List<String> getIPs(String[] args) {
        ArrayList<String> result = new ArrayList<String>();

        if (args.length != ARGS_LENGTH) {
            throw new IllegalArgumentException(USAGE);
        }
        InetAddress i1 = null;
        InetAddress i2 = null;

        boolean areIPsCorrect = true;
        try {
            i1 = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            result.add(INCORRECT_IP_1);
            areIPsCorrect = false;
        }
        try {
            i2 = InetAddress.getByName(args[1]);
        } catch (UnknownHostException e) {
            result.add(INCORRECT_IP_2);
            areIPsCorrect = false;
        }

        if (!areIPsCorrect) {
            return result;
        }

        int intRepresentation1 = ByteBuffer.wrap(i1.getAddress()).getInt();
        int intRepresentation2 = ByteBuffer.wrap(i2.getAddress()).getInt();

        if (intRepresentation2 <= intRepresentation1 + 1) {
            result.add(NO_IPS_FOUND);
            return result;
        }

        for (int i = intRepresentation1 + 1; i < intRepresentation2; i++) {
            try {
                result.add(InetAddress.getByAddress(ByteBuffer.allocate(IP_ADDR_BYTES_LENGTH).putInt(i).array())
                        .getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
