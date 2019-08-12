import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class MainTest {

    public static final String CORRECT_IP_ADDR1 = "192.168.0.1";
    public static final String IP1_IN_RANGE = "192.168.0.2";
    public static final String IP2_IN_RANGE = "192.168.0.3";
    public static final String IP3_IN_RANGE = "192.168.0.4";
    public static final String CORRECT_IP_ADDR2 = "192.168.0.5";
    public static final String CORRECT_IP_ADDR3 = "192.168.1.0";
    public static final int IP_ADDR13_DISTANCE = 254;

    public static final String INCORRECT_IP_ADDR1 = "192.356.0.1";
    public static final String INCORRECT_IP_ADDR2 = "192.";
    public static final String INCORRECT_IP_ADDR3 = "a";

    @Test (expected = IllegalArgumentException.class)
    public void testWithNoArgs() {
        List<String> result = Main.getIPs(new String[] {});
        Assert.assertEquals(0, result.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWithOneArg() {
        List<String> result = Main.getIPs(new String[] {CORRECT_IP_ADDR1});
        Assert.assertEquals(0, result.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testWithThreeArgs() {
        List<String> result = Main.getIPs(new String[] {CORRECT_IP_ADDR1, CORRECT_IP_ADDR2, CORRECT_IP_ADDR3});
        Assert.assertEquals(0, result.size());
    }


    @Test
    public void testWithIncorrectIPs() {
        List<String> result = Main.getIPs(new String[] {CORRECT_IP_ADDR1, INCORRECT_IP_ADDR1});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(Main.INCORRECT_IP_2, result.get(0));
        result = Main.getIPs(new String[] {INCORRECT_IP_ADDR2, CORRECT_IP_ADDR2});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(Main.INCORRECT_IP_1, result.get(0));
        result = Main.getIPs(new String[] {INCORRECT_IP_ADDR3, INCORRECT_IP_ADDR1});
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(Main.INCORRECT_IP_1, result.get(0));
        Assert.assertEquals(Main.INCORRECT_IP_2, result.get(1));
    }

    @Test
    public void testNoIPsFound() {
        List<String> result = Main.getIPs(new String[] {CORRECT_IP_ADDR1, CORRECT_IP_ADDR1});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(Main.NO_IPS_FOUND, result.get(0));
        result = Main.getIPs(new String[] {CORRECT_IP_ADDR2, CORRECT_IP_ADDR1});
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(Main.NO_IPS_FOUND, result.get(0));
    }

    @Test
    public void testValidRange() {
        List<String> result = Main.getIPs(new String[] {CORRECT_IP_ADDR1, CORRECT_IP_ADDR2});
        String[] expected = new String[] {IP1_IN_RANGE, IP2_IN_RANGE, IP3_IN_RANGE};
        Assert.assertEquals(expected.length, result.size());
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], result.get(i));
        }
        result = Main.getIPs(new String[] {CORRECT_IP_ADDR1, CORRECT_IP_ADDR3});
        Assert.assertEquals(IP_ADDR13_DISTANCE, result.size());
    }

}
