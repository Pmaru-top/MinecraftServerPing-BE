package tax.cute.minecraftserverpingbe;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class MCBePing {
    private String description;
    private int protocol_num;
    private String version;
    private int online_players;
    private int max_players;
    private String server_core;
    private String default_mode;
    private String world_name;
    private int delay;

    public MCBePing(
            String description,int protocol_num,String version,int online_players,int max_players,String server_core,
            String default_mode,String world_name,int delay
    ) {
        this.description = description;
        this.protocol_num = protocol_num;
        this.version = version;
        this.online_players = online_players;
        this.max_players = max_players;
        this.server_core = server_core;
        this.default_mode = default_mode;
        this.world_name = world_name;
        this.delay = delay;
    }

    public static MCBePing getMotd(String host) throws IOException {
        return getMotd(host, 19132, 2000);
    }

    public static MCBePing getMotd(String host, int port) throws IOException {
        return getMotd(host, port, 2000);
    }

    public static MCBePing getMotd(String host, int port, int timeout) throws IOException {
        DatagramPacket receivePacket = new DatagramPacket(new byte[1024], 1024);
        DatagramSocket socket = new DatagramSocket();

        //set time out
        socket.setSoTimeout(timeout);

        //connect
        socket.connect(new InetSocketAddress(host, port));

        long start = System.currentTimeMillis();

        //send PING_PACKAGE
        socket.send(new DatagramPacket(Util.getPingPackage(), 0, Util.getPingPackage().length));

        //receive data
        socket.receive(receivePacket);

        //get delay
        int delay = (int)(System.currentTimeMillis() - start);

        //close
        socket.close();

        //data to text
        String text = new String(receivePacket.getData(), StandardCharsets.UTF_8);

        //filter data

        //args size
        //13:BDS
        //10:Nukkit
        //114514:Japan Shimokitazawa server

        String description = null;
        int protocol_num = -1;
        String version = null;
        int online_players = -1;
        int max_players = -1;
        String server_core;
        String default_mode = null;
        String world_name = null;

        String[] args = text.split(";");
        if (args.length > 0)
            description = args[1];

        if (args.length > 1)
            protocol_num = Integer.parseInt(args[2]);

        if (args.length > 2)
            version = args[3];

        if (args.length > 3)
            online_players = Integer.parseInt(args[4]);

        if (args.length > 4)
            max_players = Integer.parseInt(args[5]);

        //Only supports querying the world name of the BDS server

        if(args.length > 7)
            default_mode = args[8];

        if (args.length == 10) {
            server_core = "nukkitx";
        } else if (args.length == 13) {
            server_core = "bedrock";
            world_name = args[7];
        } else {
            server_core = "unknown";
        }

        return new MCBePing(
                description,protocol_num,version,online_players,max_players,server_core,
                default_mode,world_name,delay
                );
    }

    public String getDescription() {
        return this.description;
    }

    public int getProtocol_num() {
        return this.protocol_num;
    }

    public String getVersion() {
        return this.version;
    }

    public int getOnline_players() {
        return this.online_players;
    }

    public int getMax_players() {
        return this.max_players;
    }

    public String getServer_core() {
        return this.server_core;
    }

    public String getDefault_mode() {
        return this.default_mode;
    }

    public String getWorld_name() {
        return this.world_name;
    }

    public int getDelay() {
        return this.delay;
    }
}