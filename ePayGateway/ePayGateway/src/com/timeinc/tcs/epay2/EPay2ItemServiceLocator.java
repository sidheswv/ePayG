/**
 * EPay2ItemServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.timeinc.tcs.epay2;

import com.timeinc.tcs.epayG.constants.PaymentWidgetConstants;

public class EPay2ItemServiceLocator extends org.apache.axis.client.Service implements com.timeinc.tcs.epay2.EPay2ItemService {

    public EPay2ItemServiceLocator() {
    }


    public EPay2ItemServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EPay2ItemServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EPay2Item
    private java.lang.String EPay2Item_address = PaymentWidgetConstants.SERVICE_LOCATION;

    public java.lang.String getEPay2ItemAddress() {
        return EPay2Item_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EPay2ItemWSDDServiceName = "EPay2Item";

    public java.lang.String getEPay2ItemWSDDServiceName() {
        return EPay2ItemWSDDServiceName;
    }

    public void setEPay2ItemWSDDServiceName(java.lang.String name) {
        EPay2ItemWSDDServiceName = name;
    }

    public com.timeinc.tcs.epay2.EPay2Item getEPay2Item() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EPay2Item_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEPay2Item(endpoint);
    }

    public com.timeinc.tcs.epay2.EPay2Item getEPay2Item(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.timeinc.tcs.epay2.EPay2ItemSoapBindingStub _stub = new com.timeinc.tcs.epay2.EPay2ItemSoapBindingStub(portAddress, this);
            _stub.setPortName(getEPay2ItemWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEPay2ItemEndpointAddress(java.lang.String address) {
        EPay2Item_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.timeinc.tcs.epay2.EPay2Item.class.isAssignableFrom(serviceEndpointInterface)) {
                com.timeinc.tcs.epay2.EPay2ItemSoapBindingStub _stub = new com.timeinc.tcs.epay2.EPay2ItemSoapBindingStub(new java.net.URL(EPay2Item_address), this);
                _stub.setPortName(getEPay2ItemWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EPay2Item".equals(inputPortName)) {
            return getEPay2Item();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://epay2.tcs.timeinc.com", "EPay2ItemService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://epay2.tcs.timeinc.com", "EPay2Item"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EPay2Item".equals(portName)) {
            setEPay2ItemEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
