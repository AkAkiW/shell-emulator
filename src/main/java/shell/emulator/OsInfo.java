package shell.emulator;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class OsInfo {

    public String getUsername(){
        return System.getProperty("user.name");
    }

    public String getHostname(){
        try{
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException exception){
            return "unknown";
        }
    }

}
