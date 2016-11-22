/*******************************************************************************
 * @(#)TimeClient.java 2016年11月21日
 *
 * Copyright 2016 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.aio;


/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2016年11月21日 下午5:18:42
 */
public class TimeClient {
 public static void main(String[] args) {
        
        int port = 8080;
        if(args !=null && args.length>0){
            try{
                port = Integer.valueOf(args[0]);
            }catch(NumberFormatException e){
                
            }
        }
        new Thread(new AsyncTimeClientHandler("127.0.0.1", port), "AIO-001").start();
    }
}
