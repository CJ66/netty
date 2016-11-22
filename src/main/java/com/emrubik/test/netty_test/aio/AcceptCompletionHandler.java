/*******************************************************************************
 * @(#)AcceptCompletionHandler.java 2016年11月21日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月21日 下午5:54:51
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler>{

    /** 
     * completed TODO 这里请补充overriding方法的简要说明
     * @param result
     * @param attachment TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.nio.channels.CompletionHandler#completed(java.lang.Object, java.lang.Object)
     */
    public void completed(AsynchronousSocketChannel result,
            AsyncTimeServerHandler attachment) {
        // TODO Auto-generated method stub
        attachment.asynchronousServerSocketChannel.accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        result.read(buffer,buffer,new ReadCompletionHandler(result));
    }

    /** 
     * failed TODO 这里请补充overriding方法的简要说明
     * @param exc
     * @param attachment TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     * @see java.nio.channels.CompletionHandler#failed(java.lang.Throwable, java.lang.Object)
     */
    public void failed(Throwable exc, AsyncTimeServerHandler attachment) {
        // TODO Auto-generated method stub
        attachment.latch.countDown();
    }

}
