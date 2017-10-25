/*******************************************************************************
 * @(#)ChineseProverbClientHandler.java 2017年10月25日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.netty_udp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年10月25日 上午9:26:32
 */
public class ChineseProverbClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

    /** 
     * messageReceived TODO 这里请补充overriding方法的简要说明
     * @param arg0
     * @param arg1
     * @throws Exception TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see io.netty.channel.SimpleChannelInboundHandler#messageReceived(io.netty.channel.ChannelHandlerContext, java.lang.Object)
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg)
            throws Exception {
        // TODO Auto-generated method stub
        String response = msg.content().toString(CharsetUtil.UTF_8);
        if(response.startsWith("谚语查询结果：")){
            System.out.println(response);
            ctx.close();
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
