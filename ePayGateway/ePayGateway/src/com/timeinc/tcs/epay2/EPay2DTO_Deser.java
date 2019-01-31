/**
 * EPay2DTO_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.timeinc.tcs.epay2;

public class EPay2DTO_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public EPay2DTO_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.timeinc.tcs.epay2.EPay2DTO();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_0) {
          ((EPay2DTO)value).setPresenterId(strValue);
          return true;}
        else if (qName==QName_0_1) {
          ((EPay2DTO)value).setClientId(strValue);
          return true;}
        else if (qName==QName_0_2) {
          ((EPay2DTO)value).setRefId(strValue);
          return true;}
        else if (qName==QName_0_3) {
          ((EPay2DTO)value).setTranRequestType(strValue);
          return true;}
        else if (qName==QName_0_4) {
          ((EPay2DTO)value).setTranDate(strValue);
          return true;}
        else if (qName==QName_0_5) {
          ((EPay2DTO)value).setAccountNumberLast4(strValue);
          return true;}
        else if (qName==QName_0_6) {
          ((EPay2DTO)value).setClientTranId(strValue);
          return true;}
        else if (qName==QName_0_7) {
          ((EPay2DTO)value).setAuthNumber(strValue);
          return true;}
        else if (qName==QName_0_8) {
          ((EPay2DTO)value).setResponseCode(strValue);
          return true;}
        else if (qName==QName_0_9) {
          ((EPay2DTO)value).setAvsResponse(strValue);
          return true;}
        else if (qName==QName_0_10) {
          ((EPay2DTO)value).setCsvResponse(strValue);
          return true;}
        else if (qName==QName_0_11) {
          ((EPay2DTO)value).setTcsTranId(strValue);
          return true;}
        else if (qName==QName_0_12) {
          ((EPay2DTO)value).setClientPassThrough(strValue);
          return true;}
        else if (qName==QName_0_13) {
          ((EPay2DTO)value).setTranResponseType(strValue);
          return true;}
        else if (qName==QName_0_14) {
          ((EPay2DTO)value).setTranAmount(strValue);
          return true;}
        return false;
    }
    protected boolean tryAttributeSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        return false;
    }
    protected boolean tryElementSetFromObject(javax.xml.namespace.QName qName, java.lang.Object objValue) {
        if (objValue == null) {
          return true;
        }
        return false;
    }
    protected boolean tryElementSetFromList(javax.xml.namespace.QName qName, java.util.List listValue) {
        return false;
    }
    private final static javax.xml.namespace.QName QName_0_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "clientId");
    private final static javax.xml.namespace.QName QName_0_9 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "avsResponse");
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "clientTranId");
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "refId");
    private final static javax.xml.namespace.QName QName_0_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "accountNumberLast4");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tranResponseType");
    private final static javax.xml.namespace.QName QName_0_7 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "authNumber");
    private final static javax.xml.namespace.QName QName_0_11 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tcsTranId");
    private final static javax.xml.namespace.QName QName_0_10 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "csvResponse");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "presenterId");
    private final static javax.xml.namespace.QName QName_0_12 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "clientPassThrough");
    private final static javax.xml.namespace.QName QName_0_4 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tranDate");
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tranAmount");
    private final static javax.xml.namespace.QName QName_0_8 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "responseCode");
    private final static javax.xml.namespace.QName QName_0_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "tranRequestType");
}
