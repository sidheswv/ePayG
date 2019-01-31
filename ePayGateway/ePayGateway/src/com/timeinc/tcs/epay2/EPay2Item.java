/**
 * EPay2Item.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.timeinc.tcs.epay2;

public interface EPay2Item extends java.rmi.Remote {
    public com.timeinc.tcs.epay2.EPay2DTO requestAuthorization(java.lang.String tranRequestType, java.lang.String presenterId, java.lang.String clientId, java.lang.String refId, java.lang.String firstName, java.lang.String lastName, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String postalCode, java.lang.String plus4Zip, java.lang.String countryCode, java.lang.String epvType, java.lang.String accountNumber, java.lang.String ccExp, java.lang.String ccSecurityCode, java.lang.String bankRoutingNumber, java.lang.String bankAccountType, java.lang.String currencyCode, java.lang.String tranAmount, java.lang.String salesTax, java.lang.String descriptor, java.lang.String descriptorCityPhone, java.lang.String clientPassThrough, java.lang.String clientTranId, java.lang.String rvrslTCSTranId, java.lang.String rvrslTranDate, java.lang.String rvrslAuthCode, java.lang.String encryptionFlag, java.lang.String companyName, java.lang.String billingFreq, java.lang.String billingStartDate, java.lang.String accountNbrToken) throws java.rmi.RemoteException;
}
