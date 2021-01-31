package com.curic4t.notificationtoserver.server;

import android.util.Log;

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
//            address = InetAddress.getByName("172.30.1.15");



            this.port = port;


        }catch (Exception e){

        }


    }

    public void sendMessage(String msg){
        byte[] buffer = msg.getBytes();

        try{
            socket = new DatagramSocket();
            datagramPacket = new DatagramPacket(buffer,buffer.length,address,port);
            socket.send(datagramPacket);
            socket.close();
        }catch (Exception e){
            Log.d("testtest","error "+e.toString());
        }


    }
}
