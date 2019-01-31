/**
 * EAddrDTO_Deser.java
 *
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf150632.18 v81506105401
 */

package com.timeinc.tcs.addr;

public class EAddrDTO_Deser extends com.ibm.ws.webservices.engine.encoding.ser.BeanDeserializer {
    /**
     * Constructor
     */
    public EAddrDTO_Deser(
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType, 
           com.ibm.ws.webservices.engine.description.TypeDesc _typeDesc) {
        super(_javaType, _xmlType, _typeDesc);
    }
    /**
     * Create instance of java bean
     */
    public void createValue() {
        value = new com.timeinc.tcs.addr.EAddrDTO();
    }
    protected boolean tryElementSetFromString(javax.xml.namespace.QName qName, java.lang.String strValue) {
        if (qName==QName_0_0) {
          ((EAddrDTO)value).setInputAddr1(strValue);
          return true;}
        else if (qName==QName_0_1) {
          ((EAddrDTO)value).setInputAddr2(strValue);
          return true;}
        else if (qName==QName_0_2) {
          ((EAddrDTO)value).setInputCity(strValue);
          return true;}
        else if (qName==QName_0_3) {
          ((EAddrDTO)value).setInputState(strValue);
          return true;}
        else if (qName==QName_0_4) {
          ((EAddrDTO)value).setInputZip(strValue);
          return true;}
        else if (qName==QName_0_5) {
          ((EAddrDTO)value).setOutputAddr1(strValue);
          return true;}
        else if (qName==QName_0_6) {
          ((EAddrDTO)value).setOutputAddr2(strValue);
          return true;}
        else if (qName==QName_0_7) {
          ((EAddrDTO)value).setOutputCity(strValue);
          return true;}
        else if (qName==QName_0_8) {
          ((EAddrDTO)value).setOutputState(strValue);
          return true;}
        else if (qName==QName_0_9) {
          ((EAddrDTO)value).setOutputZip(strValue);
          return true;}
        else if (qName==QName_0_10) {
          ((EAddrDTO)value).setZipPlus(strValue);
          return true;}
        else if (qName==QName_0_11) {
          ((EAddrDTO)value).setDeliveryPoint(strValue);
          return true;}
        else if (qName==QName_0_12) {
          ((EAddrDTO)value).setCodeDPV(strValue);
          return true;}
        else if (qName==QName_0_13) {
          ((EAddrDTO)value).setCarrierRoute(strValue);
          return true;}
        else if (qName==QName_0_14) {
          ((EAddrDTO)value).setLineOfTravel(strValue);
          return true;}
        else if (qName==QName_0_15) {
          ((EAddrDTO)value).setAltSeqLOT(strValue);
          return true;}
        else if (qName==QName_0_16) {
          ((EAddrDTO)value).setDirectionalResCode(strValue);
          return true;}
        else if (qName==QName_0_17) {
          ((EAddrDTO)value).setGeneralResCode(strValue);
          return true;}
        else if (qName==QName_0_18) {
          ((EAddrDTO)value).setSuffixResCode(strValue);
          return true;}
        else if (qName==QName_0_19) {
          ((EAddrDTO)value).setApartmentResCode(strValue);
          return true;}
        else if (qName==QName_0_20) {
          ((EAddrDTO)value).setLacsResCode(strValue);
          return true;}
        else if (qName==QName_0_21) {
          ((EAddrDTO)value).setDpvFPCode(strValue);
          return true;}
        else if (qName==QName_0_22) {
          ((EAddrDTO)value).setResponseCode(strValue);
          return true;}
        else if (qName==QName_0_23) {
          ((EAddrDTO)value).setResponseMessage(strValue);
          return true;}
        else if (qName==QName_0_24) {
          ((EAddrDTO)value).setClientId(strValue);
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
    private final static javax.xml.namespace.QName QName_0_14 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "lineOfTravel");
    private final static javax.xml.namespace.QName QName_0_18 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "suffixResCode");
    private final static javax.xml.namespace.QName QName_0_1 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "inputAddr2");
    private final static javax.xml.namespace.QName QName_0_0 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "inputAddr1");
    private final static javax.xml.namespace.QName QName_0_2 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "inputCity");
    private final static javax.xml.namespace.QName QName_0_10 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "zipPlus");
    private final static javax.xml.namespace.QName QName_0_6 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "outputAddr2");
    private final static javax.xml.namespace.QName QName_0_5 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "outputAddr1");
    private final static javax.xml.namespace.QName QName_0_20 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "lacsResCode");
    private final static javax.xml.namespace.QName QName_0_16 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "directionalResCode");
    private final static javax.xml.namespace.QName QName_0_19 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "apartmentResCode");
    private final static javax.xml.namespace.QName QName_0_13 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "carrierRoute");
    private final static javax.xml.namespace.QName QName_0_17 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "generalResCode");
    private final static javax.xml.namespace.QName QName_0_12 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "codeDPV");
    private final static javax.xml.namespace.QName QName_0_3 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "inputState");
    private final static javax.xml.namespace.QName QName_0_11 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "deliveryPoint");
    private final static javax.xml.namespace.QName QName_0_23 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "responseMessage");
    private final static javax.xml.namespace.QName QName_0_8 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "outputState");
    private final static javax.xml.namespace.QName QName_0_9 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "outputZip");
    private final static javax.xml.namespace.QName QName_0_4 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "inputZip");
    private final static javax.xml.namespace.QName QName_0_7 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "outputCity");
    private final static javax.xml.namespace.QName QName_0_24 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "clientId");
    private final static javax.xml.namespace.QName QName_0_15 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "altSeqLOT");
    private final static javax.xml.namespace.QName QName_0_21 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "dpvFPCode");
    private final static javax.xml.namespace.QName QName_0_22 = 
           com.ibm.ws.webservices.engine.utils.QNameTable.createQName(
                  "",
                  "responseCode");
}
