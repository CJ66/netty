/*******************************************************************************
 * @(#)ChineseProverbServerHandler.java 2017年10月24日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.netty_udp;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.ThreadLocalRandom;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年10月24日 下午3:15:47
 */
public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    // 谚语列表
    private static final String[] DICTIONARY = { "只要功夫深，铁杵磨成针。", "旧时王谢堂前燕，飞入寻常百姓家。",
            "弱阳亲友如相，一片冰心在玉壶。", "一寸光阴一寸金，寸金难买寸光阴。", "老骥伏枥，志在千里。烈士暮年，壮心不已。" };

    private String nextQuote() {
        int quoteId = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[quoteId];
    }

    /**
     * messageReceived TODO 这里请补充overriding方法的简要说明
     * @param arg0
     * @param arg1
     * @throws Exception TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see io.netty.channel.SimpleChannelInboundHandler#messageReceived(io.netty.channel.ChannelHandlerContext,
     *      java.lang.Object)
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket packet)
            throws Exception {
        // TODO Auto-generated method stub
        String req = packet.content().toString(CharsetUtil.UTF_8);
        System.out.println(req);
        if ("谚语字典查询？".equals(req)) {
            ctx.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("谚语查询结果： " + nextQuote(), CharsetUtil.UTF_8),
                    packet.sender()));
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
