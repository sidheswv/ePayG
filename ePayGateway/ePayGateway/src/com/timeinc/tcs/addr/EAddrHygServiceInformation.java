/**
 * EAddrHygServiceInformation.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.timeinc.tcs.addr;

public class EAddrHygServiceInformation implements com.ibm.ws.webservices.multiprotocol.ServiceInformation {

    private static java.util.Map operationDescriptions;
    private static java.util.Map typeMappings;

    static {
         initOperationDescriptions();
         initTypeMappings();
    }

    private static void initOperationDescriptions() { 
        operationDescriptions = new java.util.HashMap();

        java.util.Map inner0 = new java.util.HashMap();

        java.util.List list0 = new java.util.ArrayList();
        inner0.put("requestCleansing", list0);

        com.ibm.ws.webservices.engine.description.OperationDesc requestCleansing0Op = _requestCleansing0Op();
        list0.add(requestCleansing0Op);

        operationDescriptions.put("EAddrHyg",inner0);
        operationDescriptions = java.util.Collections.unmodifiableMap(operationDescriptions);
    }

    private static com.ibm.ws.webservices.engine.description.OperationDesc _requestCleansing0Op() {
        com.ibm.ws.webservices.engine.description.OperationDesc requestCleansing0Op = null;
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
        requestCleansing0Op = new com.ibm.ws.webservices.engine.description.OperationDesc("requestCleansing", com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "requestCleansing"), _params0, _returnDesc0, _faults0, null);
        requestCleansing0Op.setOption("inputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "requestCleansingRequest"));
        requestCleansing0Op.setOption("ResponseLocalPart","requestCleansingResponse");
        requestCleansing0Op.setOption("outputName","requestCleansingResponse");
        requestCleansing0Op.setOption("ResponseNamespace","http://addr.tcs.timeinc.com");
        requestCleansing0Op.setOption("targetNamespace","http://addr.tcs.timeinc.com");
        requestCleansing0Op.setOption("buildNum","cf150632.18");
        requestCleansing0Op.setOption("ServiceQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrHygService"));
        requestCleansing0Op.setOption("outputMessageQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "requestCleansingResponse"));
        requestCleansing0Op.setOption("inputName","requestCleansingRequest");
        requestCleansing0Op.setOption("portTypeQName",com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrHyg"));
        requestCleansing0Op.setStyle(com.ibm.ws.webservices.engine.enumtype.Style.WRAPPED);
        return requestCleansing0Op;

    }


    private static void initTypeMappings() {
        typeMappings = new java.util.HashMap();
        typeMappings.put(com.ibm.ws.webservices.engine.utils.QNameTable.createQName("http://addr.tcs.timeinc.com", "EAddrDTO"),
                         com.timeinc.tcs.addr.EAddrDTO.class);

        typeMappings = java.util.Collections.unmodifiableMap(typeMappings);
    }

    public java.util.Map getTypeMappings() {
        return typeMappings;
    }

    public Class getJavaType(javax.xml.namespace.QName xmlName) {
        return (Class) typeMappings.get(xmlName);
    }

    public java.util.Map getOperationDescriptions(String portName) {
        return (java.util.Map) operationDescriptions.get(portName);
    }

    public java.util.List getOperationDescriptions(String portName, String operationName) {
        java.util.Map map = (java.util.Map) operationDescriptions.get(portName);
        if (map != null) {
            return (java.util.List) map.get(operationName);
        }
        return null;
    }

}
