/**
 * EAddrHyg.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.timeinc.tcs.addr;

public interface EAddrHyg extends java.rmi.Remote {
    public com.timeinc.tcs.addr.EAddrDTO requestCleansing(java.lang.String clientId, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zipcode) throws java.rmi.RemoteException;
}
