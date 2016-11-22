/*******************************************************************************
 * @(#)ReadCompletionHandler.java 2016年11月21日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月21日 下午6:02:05
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer>{

    private AsynchronousSocketChannel channel;
    
    public ReadCompletionHandler(AsynchronousSocketChannel channel){
        if(this.channel == null){
            this.channel = channel;
        }
    }
    
    /** 
     * completed TODO 这里请补充overriding方法的简要说明
     * @param result
     * @param attachment TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
     */
    public void completed(Integer result, ByteBuffer attachment) {
        // TODO Auto-generated method stub
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try{
            String req = new String(body,"UTF-8");
            System.out.println("The time server receive order : " + req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            doWrite(currentTime);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
    
    private void doWrite(String currentTime){
        if(currentTime!=null && currentTime.trim().length()>0){
            byte[] bytes = (currentTime).getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {

                public void completed(Integer result, ByteBuffer buffer) {
                    // TODO Auto-generated method stub
                    if(buffer.hasRemaining()){
                        channel.write(buffer, buffer, this);
                    }
                }

                public void failed(Throwable exc, ByteBuffer attachment) {
                    // TODO Auto-generated method stub
                    try{
                        channel.close();
                    }catch(IOException e){
                        
                    }
                }
            });
        }
    }

    /** 
     * failed TODO 这里请补充overriding方法的简要说明
     * @param exc
     * @param attachment TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
     */
    public void failed(Throwable exc, ByteBuffer attachment) {
        // TODO Auto-generated method stub
        try{
            channel.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
