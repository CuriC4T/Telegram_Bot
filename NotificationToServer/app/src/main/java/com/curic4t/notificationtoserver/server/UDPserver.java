package com.curic4t.notificationtoserver.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPserver {
    DatagramSocket socket;
    DatagramPacket datagramPacket;
    InetAddress address;
    int port;
    public UDPserver(String hostname, int port){
        try {
            address = InetAddress.getByAddress(hostname.getBytes());
            socket = new DatagramSocket();
            this.port = port;
        }catch (Exception e){

        }


    }

    public void sendMessage(String msg){
        byte[] buffer = msg.getBytes();
        datagramPacket = new DatagramPacket(buffer,buffer.length,address,port);
        try{
            socket.send(datagramPacket);
        }catch (Exception e){

        }


    }
}
