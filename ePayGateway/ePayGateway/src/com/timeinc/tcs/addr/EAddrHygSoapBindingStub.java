/**
 * EAddrHygSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.timeinc.tcs.addr;

public class EAddrHygSoapBindingStub extends com.ibm.ws.webservices.engine.client.Stub implements com.timeinc.tcs.addr.EAddrHyg {
    public EAddrHygSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws com.ibm.ws.webservices.engine.WebServicesFault {
        if (service == null) {
            super.service = new com.ibm.ws.webservices.engine.client.Service();
        }
        else {
            super.service = service;
        }
        super.engine = ((com.ibm.ws.webservices.engine.client.Service) super.service).getEngine();
        initTypeMapping();
        super.cachedEndpoint = endpointURL;
        super.connection = ((com.ibm.ws.webservices.engine.client.Service) super.service).getConnection(endpointURL);
        super.messageContexts = new com.ibm.ws.webservices.engine.MessageContext[1];
    }

    private void initTypeMapping() {
        javax.xml.rpc.encoding.TypeMapping tm = super.getTypeMapping(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
        java.lang.Class javaType = null;
        javax.xml.namespace.QName xmlType = null;
        javax.xml.namespace.QName compQName = null;
        javax.xml.namespace.QName compTypeQName = null;
        com.ibm.ws.webservices.engine.encoding.SerializerFactory sf = null;
        com.ibm.ws.webservices.engine.encoding.DeserializerFactory df = null;
        javaType = com.timeinc.tcs.addr.EAddrDTO.class;
        xmlType = com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrDTO");
        sf = com.ibm.ws.webservices.engine.encoding.ser.BaseSerializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanSerializerFactory.class, javaType, xmlType);
        df = com.ibm.ws.webservices.engine.encoding.ser.BaseDeserializerFactory.createFactory(com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializerFactory.class, javaType, xmlType);
        if (sf != null || df != null) {
            tm.register(javaType, xmlType, sf, df);
        }

    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _requestCleansingOperation0 = null;
    private static com.ibm.ws.webservices.engine.description.OperationDesc _getrequestCleansingOperation0() {
        com.ibm.ws.webservices.engine.description.ParameterDesc[]  _params0 = new com.ibm.ws.webservices.engine.description.ParameterDesc[] {
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "clientId"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "address1"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "address2"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "city"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "state"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
         new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "zipcode"), com.ibm.ws.webservices.engine.description.ParameterDesc.IN, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false, false, false, true, false), 
          };
        _params0[0].setOption("partName","string");
        _params0[0].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[1].setOption("partName","string");
        _params0[1].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[2].setOption("partName","string");
        _params0[2].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[3].setOption("partName","string");
        _params0[3].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[4].setOption("partName","string");
        _params0[4].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        _params0[5].setOption("partName","string");
        _params0[5].setOption("partQNameString","{http://www.w3.org/2001/XMLSchema}string");
        com.ibm.ws.webservices.engine.description.ParameterDesc  _returnDesc0 = new com.ibm.ws.webservices.engine.description.ParameterDesc(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("", "requestCleansingReturn"), com.ibm.ws.webservices.engine.description.ParameterDesc.OUT, com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrDTO"), com.timeinc.tcs.addr.EAddrDTO.class, true, false, false, false, true, false); 
        _returnDesc0.setOption("partName","EAddrDTO");
        _returnDesc0.setOption("partQNameString","{http://addr.tcs.timeinc.com}EAddrDTO");
        com.ibm.ws.webservices.engine.description.FaultDesc[]  _faults0 = new com.ibm.ws.webservices.engine.description.FaultDesc[] {
          };
        _requestCleansingOperation0 = new com.ibm.ws.webservices.engine.description.OperationDesc("requestCleansing", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "requestCleansing"), _params0, _returnDesc0, _faults0, "requestCleansing");
        _requestCleansingOperation0.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "requestCleansingRequest"));
        _requestCleansingOperation0.setOption("ResponseLocalPart","requestCleansingResponse");
        _requestCleansingOperation0.setOption("outputName","requestCleansingResponse");
        _requestCleansingOperation0.setOption("ResponseNamespace","http://addr.tcs.timeinc.com");
        _requestCleansingOperation0.setOption("targetNamespace","http://addr.tcs.timeinc.com");
        _requestCleansingOperation0.setOption("buildNum","cf150632.18");
        _requestCleansingOperation0.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrHygService"));
        _requestCleansingOperation0.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "requestCleansingResponse"));
        _requestCleansingOperation0.setOption("inputName","requestCleansingRequest");
        _requestCleansingOperation0.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrHyg"));
        _requestCleansingOperation0.setUse(com.ibm.ws.webservices.engine.enumtype.Use.LITERAL);
        _requestCleansingOperation0.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return _requestCleansingOperation0;

    }

    private int _requestCleansingIndex0 = 0;
    private synchronized com.ibm.ws.webservices.engine.client.Stub.Invoke _getrequestCleansingInvoke0(Object[] parameters) throws com.ibm.ws.webservices.engine.WebServicesFault  {
        com.ibm.ws.webservices.engine.MessageContext mc = super.messageContexts[_requestCleansingIndex0];
        if (mc == null) {
            mc = new com.ibm.ws.webservices.engine.MessageContext(super.engine);
            mc.setOperation(EAddrHygSoapBindingStub._requestCleansingOperation0);
            mc.setUseSOAPAction(true);
            mc.setSOAPActionURI("requestCleansing");
            mc.setEncodingStyle(com.ibm.ws.webservices.engine.Constants.URI_LITERAL_ENC);
            mc.setProperty(com.ibm.ws.webservices.engine.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
            mc.setProperty(com.ibm.ws.webservices.engine.WebServicesEngine.PROP_DOMULTIREFS, Boolean.FALSE);
            super.primeMessageContext(mc);
            super.messageContexts[_requestCleansingIndex0] = mc;
        }
        try {
            mc = (com.ibm.ws.webservices.engine.MessageContext) mc.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw com.ibm.ws.webservices.engine.WebServicesFault.makeFault(cnse);
        }
        return new com.ibm.ws.webservices.engine.client.Stub.Invoke(connection, mc, parameters);
    }

    public com.timeinc.tcs.addr.EAddrDTO requestCleansing(java.lang.String clientId, java.lang.String address1, java.lang.String address2, java.lang.String city, java.lang.String state, java.lang.String zipcode) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new com.ibm.ws.webservices.engine.NoEndPointException();
        }
        java.util.Vector _resp = null;
        try {
            _resp = _getrequestCleansingInvoke0(new java.lang.Object[] {clientId, address1, address2, city, state, zipcode}).invoke();

        } catch (com.ibm.ws.webservices.engine.WebServicesFault wsf) {
            Exception e = wsf.getUserException();
            throw wsf;
        } 
        try {
            return (com.timeinc.tcs.addr.EAddrDTO) ((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue();
        } catch (java.lang.Exception _exception) {
            return (com.timeinc.tcs.addr.EAddrDTO) super.convert(((com.ibm.ws.webservices.engine.xmlsoap.ext.ParamValue) _resp.get(0)).getValue(), com.timeinc.tcs.addr.EAddrDTO.class);
        }
    }

    private static void _staticInit() {
        _requestCleansingOperation0 = _getrequestCleansingOperation0();
    }

    static {
       _staticInit();
    }
}
