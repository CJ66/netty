/*******************************************************************************
 * @(#)ChineseProverbServer.java 2017年10月24日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.netty_udp;

import com.emrubik.test.netty_test.netty_websocket.WebSocketServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年10月24日 下午3:12:01
 */
public class ChineseProverbServer {
    
    public void run(int port) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        
        try{
            Bootstrap b= new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
            .option(ChannelOption.SO_BROADCAST, true)
            .handler(new ChineseProverbServerHandler());
            System.out.println("启动。。。。。");
            b.bind(port).channel().closeFuture().await();
        }finally{
            group.shutdownGracefully();
        }
        
    }
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        new ChineseProverbServer().run(port);
    }
}
