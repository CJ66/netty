/*******************************************************************************
 * @(#)Test.java 2018年6月28日
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.bytebuf;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2018年6月28日 下午3:25:35
 */
public class Test {
    public static void main(String[] args) {
        testByteBuffer();
    }
    
    public static void testByteBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(88);
        String value = "Netty权威指南";
        buffer.put(value.getBytes());
        System.out.println(buffer.toString());
        buffer.flip();
        System.out.println(buffer.toString());
        byte[] vArray = new byte[buffer.remaining()];
        buffer.get(vArray);
        String decodeValue = new String(vArray);
        System.out.println(decodeValue);

    }
}
