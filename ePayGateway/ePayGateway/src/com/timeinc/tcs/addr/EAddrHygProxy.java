package com.timeinc.tcs.addr;

public class EAddrHygProxy implements com.timeinc.tcs.addr.EAddrHyg {
  private boolean _useJNDI = true;
  private String _endpoint = null;
  private com.timeinc.tcs.addr.EAddrHyg __eAddrHyg = null;
  
  public EAddrHygProxy() {
    _initEAddrHygProxy();
  }
  
  private void _initEAddrHygProxy() {
  
  if (_useJNDI) {
    try{
      javax.naming.InitialContext ctx = new javax.naming.InitialContext();
      __eAddrHyg = ((com.timeinc.tcs.addr.EAddrHygService)ctx.lookup("java:comp/env/service/EAddrHygService")).getEAddrHyg();
      }
    catch (javax.naming.NamingException namingException) {}
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__eAddrHyg == null) {
    try{
      __eAddrHyg = (new com.timeinc.tcs.addr.EAddrHygServiceLocator()).getEAddrHyg();
      }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  if (__eAddrHyg != null) {
    if (_endpoint != null)
      ((javax.xml.rpc.Stub)__eAddrHyg)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    else
      _endpoint = (String)((javax.xml.rpc.Stub)__eAddrHyg)._getProperty("javax.xml.rpc.service.endpoint.address");
  }
  
}


public void useJNDI(boolean useJNDI) {
  _useJNDI = useJNDI;
  __eAddrHyg = null;
  
}

public String getEndpoint() {
  return _endpoint;
}

public void setEndpoint(String endpoint) {
  _endpoint = endpoint;
  if (__eAddrHyg != null)
    ((javax.xml.rpc.Stub)__eAddrHyg)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
  
}

public com.timeinc.tcs.addr.EAddrHyg getEAddrHyg() {
  if (__eAddrHyg == null)
    _initEAddrHygProxy();
  return __eAddrHyg;
}

public com.timeinc.tcs.addr.EAddrDTO requestCleansing(java.lang.String clientId, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zipcode) throws java.rmi.RemoteException{
  if (__eAddrHyg == null)
    _initEAddrHygProxy();
  return __eAddrHyg.requestCleansing(clientId, address1, address2, city, state, zipcode);
}


}