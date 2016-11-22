/*******************************************************************************
 * @(#)AsyncTimeClientHandler.java 2016年11月21日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月21日 下午5:19:21
 */
public class AsyncTimeClientHandler implements CompletionHandler<Void, AsyncTimeClientHandler> ,Runnable{
    
    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;
    
    public AsyncTimeClientHandler(String host, int port){
        this.host = host;
        this.port = port;
        try{
            client = AsynchronousSocketChannel.open();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    /** 
     * run TODO 这里请补充overriding方法的简要说明 TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.lang.Runnable#run()
     */
    public void run() {
        // TODO Auto-generated method stub
        latch = new CountDownLatch(1);
        client.connect(new InetSocketAddress(host, port),this,this);
        try{
            latch.await();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try{
            client.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /** 
     * completed TODO 这里请补充overriding方法的简要说明
     * @param result
     * @param attachment TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
     */
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        // TODO Auto-generated method stub
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        client.write(writeBuffer,writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {

            public void completed(Integer result, ByteBuffer buffer) {
                // TODO Auto-generated method stub
                if(buffer.hasRemaining()){
                    client.write(buffer, buffer, this);
                }else{
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    client.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {

                        public void completed(Integer result, ByteBuffer buffer) {
                            // TODO Auto-generated method stub
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            String body;
                            try{
                                body = new String(bytes,"UTF-8");
                                System.out.println("Now is :" + body);
                                latch.countDown();
                            }catch(UnsupportedEncodingException e){
                                e.printStackTrace();
                            }
                        }

                        public void failed(Throwable exc, ByteBuffer attachment) {
                            // TODO Auto-generated method stub
                            try{
                                client.close();
                                latch.countDown();
                            }catch(IOException e){
                                
                            }
                        }
                    });
                }
            }

            public void failed(Throwable exc, ByteBuffer attachment) {
                // TODO Auto-generated method stub
                try{
                    client.close();
                    latch.countDown();
                }catch(IOException e){
                    
                }
            }
        });
    }

    /** 
     * failed TODO 这里请补充overriding方法的简要说明
     * @param exc
     * @param attachment TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
     */
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        // TODO Auto-generated method stub
        try{
            client.close();
            latch.countDown();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
