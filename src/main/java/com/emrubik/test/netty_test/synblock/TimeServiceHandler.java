/*******************************************************************************
 * @(#)TimeServiceHandler.java 2016年11月18日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.synblock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月18日 下午3:44:45
 */
public class TimeServiceHandler implements Runnable {

    private Socket socket;

    public TimeServiceHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * run TODO 这里请补充overriding方法的简要说明 TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.lang.Runnable#run()
     */
    public void run() {
        // TODO Auto-generated method stub
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream());
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine();
                if (body == null) break;
                System.out.println("The time server receive order :" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)
                        ? new Date(System.currentTimeMillis()).toString() : "BAD ORDERS";
                out.println(currentTime);

            }
        } catch (Exception e) {
            if (out != null) {
                out.close();
                out = null;
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException a) {
                    // TODO Auto-generated catch block
                    a.printStackTrace();
                }
                in = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException a) {
                    // TODO Auto-generated catch block
                    a.printStackTrace();
                }
                socket = null;
            }
        }
    }

}
