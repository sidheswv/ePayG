/**
 * EAddrHygServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.timeinc.tcs.addr;

public class EAddrHygServiceLocator extends com.ibm.ws.webservices.multiprotocol.AgnosticService implements com.ibm.ws.webservices.multiprotocol.GeneratedService, com.timeinc.tcs.addr.EAddrHygService {

    public EAddrHygServiceLocator() {
        super(com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
           "http://addr.tcs.timeinc.com",
           "EAddrHygService"));

        context.setLocatorName("com.timeinc.tcs.addr.EAddrHygServiceLocator");
    }

    public EAddrHygServiceLocator(com.ibm.ws.webservices.multiprotocol.ServiceContext ctx) {
        super(ctx);
        context.setLocatorName("com.timeinc.tcs.addr.EAddrHygServiceLocator");
    }

    // Use to get a proxy class for EAddrHyg
    private final java.lang.String EAddrHyg_address = "http://wstc3:9080/EAddressApp/services/EAddrHyg";

    public java.lang.String getEAddrHygAddress() {
        if (context.getOverriddingEndpointURIs() == null) {
            return EAddrHyg_address;
        }
        String overriddingEndpoint = (String) context.getOverriddingEndpointURIs().get("EAddrHyg");
        if (overriddingEndpoint != null) {
            return overriddingEndpoint;
        }
        else {
            return EAddrHyg_address;
        }
    }

    private java.lang.String EAddrHygPortName = "EAddrHyg";

    // The WSDD port name defaults to the port name.
    private java.lang.String EAddrHygWSDDPortName = "EAddrHyg";

    public java.lang.String getEAddrHygWSDDPortName() {
        return EAddrHygWSDDPortName;
    }

    public void setEAddrHygWSDDPortName(java.lang.String name) {
        EAddrHygWSDDPortName = name;
    }

    public com.timeinc.tcs.addr.EAddrHyg getEAddrHyg() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(getEAddrHygAddress());
        }
        catch (java.net.MalformedURLException e) {
            return null; // unlikely as URL was validated in WSDL2Java
        }
        return getEAddrHyg(endpoint);
    }

    public com.timeinc.tcs.addr.EAddrHyg getEAddrHyg(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        com.timeinc.tcs.addr.EAddrHyg _stub =
            (com.timeinc.tcs.addr.EAddrHyg) getStub(
                EAddrHygPortName,
                (String) getPort2NamespaceMap().get(EAddrHygPortName),
                com.timeinc.tcs.addr.EAddrHyg.class,
                "com.timeinc.tcs.addr.EAddrHygSoapBindingStub",
                portAddress.toString());
        if (_stub instanceof com.ibm.ws.webservices.engine.client.Stub) {
            ((com.ibm.ws.webservices.engine.client.Stub) _stub).setPortName(EAddrHygWSDDPortName);
        }
        return _stub;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.timeinc.tcs.addr.EAddrHyg.class.isAssignableFrom(serviceEndpointInterface)) {
                return getEAddrHyg();
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("WSWS3273E: Error: There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        String inputPortName = portName.getLocalPart();
        if ("EAddrHyg".equals(inputPortName)) {
            return getEAddrHyg();
        }
        else  {
            throw new javax.xml.rpc.ServiceException();
        }
    }

    public void setPortNamePrefix(java.lang.String prefix) {
        EAddrHygWSDDPortName = prefix + "/" + EAddrHygPortName;
    }

    public javax.xml.namespace.QName getServiceName() {
        return com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrHygService");
    }

    private java.util.Map port2NamespaceMap = null;

    protected synchronized java.util.Map getPort2NamespaceMap() {
        if (port2NamespaceMap == null) {
            port2NamespaceMap = new java.util.HashMap();
            port2NamespaceMap.put(
               "EAddrHyg",
               "http://schemas.xmlsoap.org/wsdl/soap/");
        }
        return port2NamespaceMap;
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            String serviceNamespace = getServiceName().getNamespaceURI();
            for (java.util.Iterator i = getPort2NamespaceMap().keySet().iterator(); i.hasNext(); ) {
                ports.add(
                    com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                        serviceNamespace,
                        (String) i.next()));
            }
        }
        return ports.iterator();
    }

    public javax.xml.rpc.Call[] getCalls(javax.xml.namespace.QName portName) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
        if  (portName.getLocalPart().equals("EAddrHyg")) {
            return new javax.xml.rpc.Call[] {
                createCall(portName, "requestCleansing", "requestCleansingRequest"),
            };
        }
        else {
            throw new javax.xml.rpc.ServiceException("WSWS3062E: Error: portName should not be null.");
        }
    }
}
