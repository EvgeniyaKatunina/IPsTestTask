import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage: IPAddr1 IPAddr2.");
        }
        InetAddress i1 = null;
        InetAddress i2 = null;

        boolean areIPsCorrect = true;
        try {
            i1 = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.out.println("Incorrect IPAddr1.");
            areIPsCorrect = false;
        }
        try {
            i2 = InetAddress.getByName(args[1]);
        } catch (UnknownHostException e) {
            System.out.println("Incorrect IPAddr2.");
            areIPsCorrect = false;
        }

        if (!areIPsCorrect) {
            return;
        }

        int intRepresentation1 = ByteBuffer.wrap(i1.getAddress()).getInt();
        int intRepresentation2 = ByteBuffer.wrap(i2.getAddress()).getInt();

        if (intRepresentation2 <= intRepresentation1 + 1) {
            System.out.println("There are no IP addresses found in given range.");
        }

        for (int i = intRepresentation1 + 1; i < intRepresentation2; i++) {
            try {
                System.out.println(InetAddress.getByAddress(ByteBuffer.allocate(4).putInt(i).array()).getHostAddress());
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }

    }
}
