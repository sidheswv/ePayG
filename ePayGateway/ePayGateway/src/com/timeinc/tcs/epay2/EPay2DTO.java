/**
 * EPay2DTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.timeinc.tcs.epay2;

public class EPay2DTO  implements java.io.Serializable {
    private java.lang.String presenterId;

    private java.lang.String clientId;

    private java.lang.String refId;

    private java.lang.String tranRequestType;

    private java.lang.String tranDate;

    private java.lang.String accountNumberLast4;

    private java.lang.String clientTranId;

    private java.lang.String authNumber;

    private java.lang.String responseCode;

    private java.lang.String avsResponse;

    private java.lang.String csvResponse;

    private java.lang.String tcsTranId;

    private java.lang.String clientPassThrough;

    private java.lang.String tranResponseType;

    private java.lang.String tranAmount;

    private java.lang.String clgHseTransId;

    private java.lang.String accountNbrToken;

    public EPay2DTO() {
    }

    public EPay2DTO(
           java.lang.String presenterId,
           java.lang.String clientId,
           java.lang.String refId,
           java.lang.String tranRequestType,
           java.lang.String tranDate,
           java.lang.String accountNumberLast4,
           java.lang.String clientTranId,
           java.lang.String authNumber,
           java.lang.String responseCode,
           java.lang.String avsResponse,
           java.lang.String csvResponse,
           java.lang.String tcsTranId,
           java.lang.String clientPassThrough,
           java.lang.String tranResponseType,
           java.lang.String tranAmount,
           java.lang.String clgHseTransId,
           java.lang.String accountNbrToken) {
           this.presenterId = presenterId;
           this.clientId = clientId;
           this.refId = refId;
           this.tranRequestType = tranRequestType;
           this.tranDate = tranDate;
           this.accountNumberLast4 = accountNumberLast4;
           this.clientTranId = clientTranId;
           this.authNumber = authNumber;
           this.responseCode = responseCode;
           this.avsResponse = avsResponse;
           this.csvResponse = csvResponse;
           this.tcsTranId = tcsTranId;
           this.clientPassThrough = clientPassThrough;
           this.tranResponseType = tranResponseType;
           this.tranAmount = tranAmount;
           this.clgHseTransId = clgHseTransId;
           this.accountNbrToken = accountNbrToken;
    }


    /**
     * Gets the presenterId value for this EPay2DTO.
     * 
     * @return presenterId
     */
    public java.lang.String getPresenterId() {
        return presenterId;
    }


    /**
     * Sets the presenterId value for this EPay2DTO.
     * 
     * @param presenterId
     */
    public void setPresenterId(java.lang.String presenterId) {
        this.presenterId = presenterId;
    }


    /**
     * Gets the clientId value for this EPay2DTO.
     * 
     * @return clientId
     */
    public java.lang.String getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this EPay2DTO.
     * 
     * @param clientId
     */
    public void setClientId(java.lang.String clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the refId value for this EPay2DTO.
     * 
     * @return refId
     */
    public java.lang.String getRefId() {
        return refId;
    }


    /**
     * Sets the refId value for this EPay2DTO.
     * 
     * @param refId
     */
    public void setRefId(java.lang.String refId) {
        this.refId = refId;
    }


    /**
     * Gets the tranRequestType value for this EPay2DTO.
     * 
     * @return tranRequestType
     */
    public java.lang.String getTranRequestType() {
        return tranRequestType;
    }


    /**
     * Sets the tranRequestType value for this EPay2DTO.
     * 
     * @param tranRequestType
     */
    public void setTranRequestType(java.lang.String tranRequestType) {
        this.tranRequestType = tranRequestType;
    }


    /**
     * Gets the tranDate value for this EPay2DTO.
     * 
     * @return tranDate
     */
    public java.lang.String getTranDate() {
        return tranDate;
    }


    /**
     * Sets the tranDate value for this EPay2DTO.
     * 
     * @param tranDate
     */
    public void setTranDate(java.lang.String tranDate) {
        this.tranDate = tranDate;
    }


    /**
     * Gets the accountNumberLast4 value for this EPay2DTO.
     * 
     * @return accountNumberLast4
     */
    public java.lang.String getAccountNumberLast4() {
        return accountNumberLast4;
    }


    /**
     * Sets the accountNumberLast4 value for this EPay2DTO.
     * 
     * @param accountNumberLast4
     */
    public void setAccountNumberLast4(java.lang.String accountNumberLast4) {
        this.accountNumberLast4 = accountNumberLast4;
    }


    /**
     * Gets the clientTranId value for this EPay2DTO.
     * 
     * @return clientTranId
     */
    public java.lang.String getClientTranId() {
        return clientTranId;
    }


    /**
     * Sets the clientTranId value for this EPay2DTO.
     * 
     * @param clientTranId
     */
    public void setClientTranId(java.lang.String clientTranId) {
        this.clientTranId = clientTranId;
    }


    /**
     * Gets the authNumber value for this EPay2DTO.
     * 
     * @return authNumber
     */
    public java.lang.String getAuthNumber() {
        return authNumber;
    }


    /**
     * Sets the authNumber value for this EPay2DTO.
     * 
     * @param authNumber
     */
    public void setAuthNumber(java.lang.String authNumber) {
        this.authNumber = authNumber;
    }


    /**
     * Gets the responseCode value for this EPay2DTO.
     * 
     * @return responseCode
     */
    public java.lang.String getResponseCode() {
        return responseCode;
    }


    /**
     * Sets the responseCode value for this EPay2DTO.
     * 
     * @param responseCode
     */
    public void setResponseCode(java.lang.String responseCode) {
        this.responseCode = responseCode;
    }


    /**
     * Gets the avsResponse value for this EPay2DTO.
     * 
     * @return avsResponse
     */
    public java.lang.String getAvsResponse() {
        return avsResponse;
    }


    /**
     * Sets the avsResponse value for this EPay2DTO.
     * 
     * @param avsResponse
     */
    public void setAvsResponse(java.lang.String avsResponse) {
        this.avsResponse = avsResponse;
    }


    /**
     * Gets the csvResponse value for this EPay2DTO.
     * 
     * @return csvResponse
     */
    public java.lang.String getCsvResponse() {
        return csvResponse;
    }


    /**
     * Sets the csvResponse value for this EPay2DTO.
     * 
     * @param csvResponse
     */
    public void setCsvResponse(java.lang.String csvResponse) {
        this.csvResponse = csvResponse;
    }


    /**
     * Gets the tcsTranId value for this EPay2DTO.
     * 
     * @return tcsTranId
     */
    public java.lang.String getTcsTranId() {
        return tcsTranId;
    }


    /**
     * Sets the tcsTranId value for this EPay2DTO.
     * 
     * @param tcsTranId
     */
    public void setTcsTranId(java.lang.String tcsTranId) {
        this.tcsTranId = tcsTranId;
    }


    /**
     * Gets the clientPassThrough value for this EPay2DTO.
     * 
     * @return clientPassThrough
     */
    public java.lang.String getClientPassThrough() {
        return clientPassThrough;
    }


    /**
     * Sets the clientPassThrough value for this EPay2DTO.
     * 
     * @param clientPassThrough
     */
    public void setClientPassThrough(java.lang.String clientPassThrough) {
        this.clientPassThrough = clientPassThrough;
    }


    /**
     * Gets the tranResponseType value for this EPay2DTO.
     * 
     * @return tranResponseType
     */
    public java.lang.String getTranResponseType() {
        return tranResponseType;
    }


    /**
     * Sets the tranResponseType value for this EPay2DTO.
     * 
     * @param tranResponseType
     */
    public void setTranResponseType(java.lang.String tranResponseType) {
        this.tranResponseType = tranResponseType;
    }


    /**
     * Gets the tranAmount value for this EPay2DTO.
     * 
     * @return tranAmount
     */
    public java.lang.String getTranAmount() {
        return tranAmount;
    }


    /**
     * Sets the tranAmount value for this EPay2DTO.
     * 
     * @param tranAmount
     */
    public void setTranAmount(java.lang.String tranAmount) {
        this.tranAmount = tranAmount;
    }


    /**
     * Gets the clgHseTransId value for this EPay2DTO.
     * 
     * @return clgHseTransId
     */
    public java.lang.String getClgHseTransId() {
        return clgHseTransId;
    }


    /**
     * Sets the clgHseTransId value for this EPay2DTO.
     * 
     * @param clgHseTransId
     */
    public void setClgHseTransId(java.lang.String clgHseTransId) {
        this.clgHseTransId = clgHseTransId;
    }


    /**
     * Gets the accountNbrToken value for this EPay2DTO.
     * 
     * @return accountNbrToken
     */
    public java.lang.String getAccountNbrToken() {
        return accountNbrToken;
    }


    /**
     * Sets the accountNbrToken value for this EPay2DTO.
     * 
     * @param accountNbrToken
     */
    public void setAccountNbrToken(java.lang.String accountNbrToken) {
        this.accountNbrToken = accountNbrToken;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EPay2DTO)) return false;
        EPay2DTO other = (EPay2DTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.presenterId==null && other.getPresenterId()==null) || 
             (this.presenterId!=null &&
              this.presenterId.equals(other.getPresenterId()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.refId==null && other.getRefId()==null) || 
             (this.refId!=null &&
              this.refId.equals(other.getRefId()))) &&
            ((this.tranRequestType==null && other.getTranRequestType()==null) || 
             (this.tranRequestType!=null &&
              this.tranRequestType.equals(other.getTranRequestType()))) &&
            ((this.tranDate==null && other.getTranDate()==null) || 
             (this.tranDate!=null &&
              this.tranDate.equals(other.getTranDate()))) &&
            ((this.accountNumberLast4==null && other.getAccountNumberLast4()==null) || 
             (this.accountNumberLast4!=null &&
              this.accountNumberLast4.equals(other.getAccountNumberLast4()))) &&
            ((this.clientTranId==null && other.getClientTranId()==null) || 
             (this.clientTranId!=null &&
              this.clientTranId.equals(other.getClientTranId()))) &&
            ((this.authNumber==null && other.getAuthNumber()==null) || 
             (this.authNumber!=null &&
              this.authNumber.equals(other.getAuthNumber()))) &&
            ((this.responseCode==null && other.getResponseCode()==null) || 
             (this.responseCode!=null &&
              this.responseCode.equals(other.getResponseCode()))) &&
            ((this.avsResponse==null && other.getAvsResponse()==null) || 
             (this.avsResponse!=null &&
              this.avsResponse.equals(other.getAvsResponse()))) &&
            ((this.csvResponse==null && other.getCsvResponse()==null) || 
             (this.csvResponse!=null &&
              this.csvResponse.equals(other.getCsvResponse()))) &&
            ((this.tcsTranId==null && other.getTcsTranId()==null) || 
             (this.tcsTranId!=null &&
              this.tcsTranId.equals(other.getTcsTranId()))) &&
            ((this.clientPassThrough==null && other.getClientPassThrough()==null) || 
             (this.clientPassThrough!=null &&
              this.clientPassThrough.equals(other.getClientPassThrough()))) &&
            ((this.tranResponseType==null && other.getTranResponseType()==null) || 
             (this.tranResponseType!=null &&
              this.tranResponseType.equals(other.getTranResponseType()))) &&
            ((this.tranAmount==null && other.getTranAmount()==null) || 
             (this.tranAmount!=null &&
              this.tranAmount.equals(other.getTranAmount()))) &&
            ((this.clgHseTransId==null && other.getClgHseTransId()==null) || 
             (this.clgHseTransId!=null &&
              this.clgHseTransId.equals(other.getClgHseTransId()))) &&
            ((this.accountNbrToken==null && other.getAccountNbrToken()==null) || 
             (this.accountNbrToken!=null &&
              this.accountNbrToken.equals(other.getAccountNbrToken())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getPresenterId() != null) {
            _hashCode += getPresenterId().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getRefId() != null) {
            _hashCode += getRefId().hashCode();
        }
        if (getTranRequestType() != null) {
            _hashCode += getTranRequestType().hashCode();
        }
        if (getTranDate() != null) {
            _hashCode += getTranDate().hashCode();
        }
        if (getAccountNumberLast4() != null) {
            _hashCode += getAccountNumberLast4().hashCode();
        }
        if (getClientTranId() != null) {
            _hashCode += getClientTranId().hashCode();
        }
        if (getAuthNumber() != null) {
            _hashCode += getAuthNumber().hashCode();
        }
        if (getResponseCode() != null) {
            _hashCode += getResponseCode().hashCode();
        }
        if (getAvsResponse() != null) {
            _hashCode += getAvsResponse().hashCode();
        }
        if (getCsvResponse() != null) {
            _hashCode += getCsvResponse().hashCode();
        }
        if (getTcsTranId() != null) {
            _hashCode += getTcsTranId().hashCode();
        }
        if (getClientPassThrough() != null) {
            _hashCode += getClientPassThrough().hashCode();
        }
        if (getTranResponseType() != null) {
            _hashCode += getTranResponseType().hashCode();
        }
        if (getTranAmount() != null) {
            _hashCode += getTranAmount().hashCode();
        }
        if (getClgHseTransId() != null) {
            _hashCode += getClgHseTransId().hashCode();
        }
        if (getAccountNbrToken() != null) {
            _hashCode += getAccountNbrToken().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EPay2DTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://epay2.tcs.timeinc.com", "EPay2DTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("presenterId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "presenterId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "refId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tranRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tranRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tranDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tranDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumberLast4");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountNumberLast4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientTranId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientTranId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responseCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("avsResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "avsResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("csvResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("", "csvResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tcsTranId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tcsTranId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientPassThrough");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clientPassThrough"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tranResponseType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tranResponseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tranAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tranAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clgHseTransId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "clgHseTransId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNbrToken");
        elemField.setXmlName(new javax.xml.namespace.QName("", "accountNbrToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
