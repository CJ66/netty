/*******************************************************************************
 * @(#)WebSocketServer.java 2017年10月23日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.netty_websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年10月23日 下午5:37:12
 */
public class WebSocketServer {

    public void bind(int port) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            System.out.println("启动service端-------------------------");
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new ChildChannelHandler());

            System.out.println("Web socket server started at port" + port + ".");
            System.out.println("Open your browser and navigate to http://localhost:" + port + '/');
            System.out.println("服务端开启等待客户端连接 ... ...");

            Channel ch = b.bind(port).sync().channel();

            ch.closeFuture().sync();
        } finally {
            // 优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
        /**
         * initChannel TODO 这里请补充overriding方法的简要说明
         * @param arg0
         * @throws Exception TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
         * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
         */
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline().addLast("http-codec", new HttpServerCodec());
            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
            ch.pipeline().addLast("handler", new WebSocketServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 7397;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {

            }
        }
        new WebSocketServer().bind(port);
    }
}
