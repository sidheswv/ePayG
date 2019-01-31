package com.timeinc.tcs.epay2;

public class EPay2ItemProxy implements com.timeinc.tcs.epay2.EPay2Item {
  private String _endpoint = null;
  private com.timeinc.tcs.epay2.EPay2Item ePay2Item = null;
  
  public EPay2ItemProxy() {
    _initEPay2ItemProxy();
  }
  
  public EPay2ItemProxy(String endpoint) {
    _endpoint = endpoint;
    _initEPay2ItemProxy();
  }
  
  private void _initEPay2ItemProxy() {
    try {
      ePay2Item = (new com.timeinc.tcs.epay2.EPay2ItemServiceLocator()).getEPay2Item();
      if (ePay2Item != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)ePay2Item)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)ePay2Item)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (ePay2Item != null)
      ((javax.xml.rpc.Stub)ePay2Item)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.timeinc.tcs.epay2.EPay2Item getEPay2Item() {
    if (ePay2Item == null)
      _initEPay2ItemProxy();
    return ePay2Item;
  }
  
  public com.timeinc.tcs.epay2.EPay2DTO requestAuthorization(java.lang.String tranRequestType, java.lang.String presenterId, java.lang.String clientId, java.lang.String refId, java.lang.String firstName, java.lang.String lastName, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String postalCode, java.lang.String plus4Zip, java.lang.String countryCode, java.lang.String epvType, java.lang.String accountNumber, java.lang.String ccExp, java.lang.String ccSecurityCode, java.lang.String bankRoutingNumber, java.lang.String bankAccountType, java.lang.String currencyCode, java.lang.String tranAmount, java.lang.String salesTax, java.lang.String descriptor, java.lang.String descriptorCityPhone, java.lang.String clientPassThrough, java.lang.String clientTranId, java.lang.String rvrslTCSTranId, java.lang.String rvrslTranDate, java.lang.String rvrslAuthCode, java.lang.String encryptionFlag, java.lang.String companyName, java.lang.String billingFreq, java.lang.String billingStartDate, java.lang.String accountNbrToken) throws java.rmi.RemoteException{
    if (ePay2Item == null)
      _initEPay2ItemProxy();
    return ePay2Item.requestAuthorization(tranRequestType, presenterId, clientId, refId, firstName, lastName, address1, address2, city, state, postalCode, plus4Zip, countryCode, epvType, accountNumber, ccExp, ccSecurityCode, bankRoutingNumber, bankAccountType, currencyCode, tranAmount, salesTax, descriptor, descriptorCityPhone, clientPassThrough, clientTranId, rvrslTCSTranId, rvrslTranDate, rvrslAuthCode, encryptionFlag, companyName, billingFreq, billingStartDate, accountNbrToken);
  }
  
  
}