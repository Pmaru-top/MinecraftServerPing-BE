package tax.cute.minecraftserverpingbe;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String host = "pe.mineplex.com";
        int port = 19132;
        MCBePing ping = MCBePing.getMotd(host,port);

        //When the server does not return the specified parameter, if it is String, get a null pointer, if it is Integer, get -1
        //In order to avoid an exception caused by a null pointer, it is best to use String.valueOf() for character types

        System.out.println("Now pinging " + host + ":" + port);

        System.out.println("ConnectDelay:" + ping.getDelay() + "ms");
        System.out.println("Description:" + String.valueOf(ping.getDescription()));
        System.out.println("Version:" + String.valueOf(ping.getVersion()));
        System.out.println("ProtocolNum:" + ping.getProtocol_num());
        System.out.println("Players:" + ping.getOnline_players() + "/" + ping.getMax_players());
        System.out.println("Type:" + ping.getType());
        System.out.println("DefaultMode:" + String.valueOf(ping.getDefault_mode()));
        System.out.println("WorldName(Only BDS server available):" + String.valueOf(ping.getWorld_name()));
    }
}
