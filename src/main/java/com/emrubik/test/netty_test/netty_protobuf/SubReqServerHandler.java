/*******************************************************************************
 * @(#)SubReqServerHandler.java 2017年2月6日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.netty_protobuf;

import com.emrubik.test.netty_test.netty_protobuf.SubscribeReqProto.SubscribeReq;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年2月6日 上午11:48:03
 */
public class SubReqServerHandler extends ChannelHandlerAdapter{

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // TODO Auto-generated method stub
//        SubscribeReq req = (SubscribeReq) msg;
        SubscribeReqProto.SubscribeReq req = (SubscribeReq) msg;
        if("Lilinfeng".equalsIgnoreCase(req.getUserName())){
            System.out.println("Service accept client subscribe req : [" + req.toString() + "]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }
    
    private SubscribeRespProto.SubscribeResp resp(int subReqID){
//        SubscribeResp resp = new SubscribeResp();
//        resp.setSubReqID(subReqID);
//        resp.setRespCode(0);
//        resp.setDesc("Netty book order succeed, 3 days later , sent to the designated address");
//        return resp;
        
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode("0");
        builder.setDesc("Netty book order succeed, 3 days later , sent to the designated address");
        return builder.build();
        
    }
}
