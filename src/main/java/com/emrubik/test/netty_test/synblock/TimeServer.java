/*******************************************************************************
 * @(#)TimeServer.java 2016年11月18日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.synblock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月18日 下午3:42:09
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        
        int port = 8080;
        if(args !=null && args.length>0){
            try{
                port = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){
                
            }
        }
        
        ServerSocket server = null;
        try{
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : "+ port);
            Socket socket = null;
            while(true){
                socket = server.accept();
                new Thread(new TimeServiceHandler(socket)).start();
            }
        }finally{
            if(server != null){
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                server=null;
            }
        }
        
    }
}
