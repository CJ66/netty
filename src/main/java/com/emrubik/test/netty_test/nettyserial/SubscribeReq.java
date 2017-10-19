/*******************************************************************************
 * @(#)SubscribeReq.java 2017年2月6日
 *
 * Copyright 2017 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.test.netty_test.nettyserial;

import java.io.Serializable;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2017年2月6日 上午11:33:00
 */
public class SubscribeReq implements Serializable{

    /**
     * serialVersionUID : TODO 这里请补充该字段的简述说明
     */
    private static final long serialVersionUID = 1L;
    
    private int subReqID;
    
    private String userName;
    
    private String productName;
    
    private String phoneNumber;
    
    private String address;

    public int getSubReqID() {
        return subReqID;
    }

    public void setSubReqID(int subReqID) {
        this.subReqID = subReqID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SubscribeReq [subReqID=" + subReqID + ", userName=" + userName + ", productName="
                + productName + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
    }
    
    

}
