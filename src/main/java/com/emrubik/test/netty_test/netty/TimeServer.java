/*******************************************************************************
 * @(#)TimeServer.java 2016年11月22日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.netty;


import com.emrubik.test.netty_test.fakeasyn.TimeServerHandlerExecutePool;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月22日 下午5:01:38
 */
public class TimeServer {
    
    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .childHandler(new ChildChannelHandler());
            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();
            //等待服务端监听端口关闭
            f.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
        /** 
         * initChannel TODO 这里请补充overriding方法的简要说明
         * @param arg0
         * @throws Exception TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
         * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
         */
        @Override
        protected void initChannel(SocketChannel arg0) throws Exception {
            // TODO Auto-generated method stub
            arg0.pipeline().addLast(new TimeServerHandler());
        }
    }
    
    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length>0){
            try{
                port = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){
                
            }
        }
        new TimeServer().bind(port);
    }
}



