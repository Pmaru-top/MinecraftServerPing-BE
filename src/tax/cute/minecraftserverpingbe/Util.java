package tax.cute.minecraftserverpingbe;

public class Util {
    //from mirai-bedrock-motd,made some changes
    //Thanks mirai-bedrock-motd:https://github.com/zixuan2020/mirai-bedrock-motd/blob/master/src/main/java/top/zixuan007/BedrockSocket.java
    public static byte[] getPingPackage() {
        final String PING_PACKAGE = "0100000000240d12D300ffff00fefefefefdfdfdfd12345678";
        final byte[] byteArray = new byte[PING_PACKAGE.length() >> 1];
        int index = 0;
        for (int i = 0; i < PING_PACKAGE.length(); i++) {
            if (index > PING_PACKAGE.length() - 1)
                return byteArray;
            byte highDit = (byte) (Character.digit(PING_PACKAGE.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(PING_PACKAGE.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }
}
