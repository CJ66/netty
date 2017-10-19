/*******************************************************************************
 * @(#)SubscribeResp.java 2017年2月6日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.nettyserial;

import java.io.Serializable;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年2月6日 上午11:35:11
 */
public class SubscribeResp implements Serializable{

    /**
     * serialVersionUID : TODO 这里请补充该字段的简述说明
     */
    private static final long serialVersionUID = 1L;
    
    private int subReqID;
    
    private int respCode;
    
    private String desc;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SubscribeResp [subReqID=" + subReqID + ", respCode=" + respCode + ", desc=" + desc
                + "]";
    }
    
    
}
