package com.timeinc.tcs.epayG.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.timeinc.tcs.epayG.beans.ClientBean;
import com.timeinc.tcs.epayG.beans.CpsResponseBean;
import com.timeinc.tcs.epayG.beans.DivisionBean;
import com.timeinc.tcs.epayG.beans.ECDirectBean;
import com.timeinc.tcs.epayG.beans.ECDirectOrphanBean;
import com.timeinc.tcs.epayG.constants.AndroidPayConstants;
import com.timeinc.tcs.epayG.dto.AndroidPayData;
import com.timeinc.tcs.epayG.dto.ApplePayData;
import com.timeinc.tcs.epayG.dto.ECDirectData;
import com.timeinc.tcs.epayG.helper.AndroidPayHelper;
import com.timeinc.tcs.epayG.helper.OrbitalHelper;
import com.timeinc.tcs.epayG.helper.PayPalECHelper;
import com.timeinc.tcs.epayG.validation.Validator;

/**
 * 
 * 
 * @author poduril
 *
 */

public class DBAccess {

	private static DataSource ds = null;

	/**
	 * DbAccess.getDivisionTable Gets Division table data.
	 * 
	 * @return Returns requested column information
	 */
	public static Vector getDivisionTable(String dbOwner, Context ctx) {
		System.out.println("DBAccess.getDivisionTable");
		String queryString = null;
		ResultSet rs = null;
		Connection dataConn = null;

		Vector divisionTable = new Vector();
		Validator.cleanQueryParam(dbOwner);

		try {
			queryString = "select CLIENT_CD, CURRENCY_CD, PRESENTER_CUR_CD, CPS_DIVISION_CD from "
					+ dbOwner + ".TBV_CPS_DIVISION";
			System.out.println("DBAccess.getDivisionTable: " + queryString);

			dataConn = getConnection(ctx);

			PreparedStatement pstmt = dataConn.prepareStatement(queryString);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				DivisionBean divisionRow = new DivisionBean();
				divisionRow.setClientCd(rs.getString(1));
				divisionRow.setCurrencyCd(rs.getString(2));
				divisionRow.setPresenterCurCd(rs.getString(3));
				divisionRow.setDivision(rs.getString(4));
				divisionTable.addElement(divisionRow);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("DBAccess.getDivisionTable: Exception : "
					+ e.getMessage());
			// EPay2Item.exceptionNotify(e);
			return null;
		} finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getDivisionTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getDivisionTable: Release connection: "
									+ e.getMessage());
					// EPay2Item.exceptionNotify(e);
				}
			}
		}
		return divisionTable;
	}

	/**
	 * DbAccess.getCpsResponseTable Gets Response table data.
	 * 
	 * @return Returns requested column information
	 */
	public static Vector getCpsResponseTable(String dbOwner, Context ctx) {
		System.out.println("DBAccess.getCpsResponseTable");
		String queryString = null;
		ResultSet rs = null;
		Connection dataConn = null;
		Validator.cleanQueryParam(dbOwner);
		Vector cpsResponseTable = new Vector();

		try {

			queryString = "select RESP_CD, DECLINE_TY from " + dbOwner
					+ ".TBV_RESPONSE where CLG_HSE_FL = ?";
			System.out.println("DBAccess.getCpsResponseTable: " + queryString);

			dataConn = getConnection(ctx);

			PreparedStatement pstmt = dataConn.prepareStatement(queryString);
			pstmt.setString(1, "P");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				CpsResponseBean cpsResponseRow = new CpsResponseBean();
				cpsResponseRow.setRespCd(rs.getString(1));
				cpsResponseRow.setTranResponseType(rs.getString(2));
				cpsResponseTable.addElement(cpsResponseRow);

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("DBAccess.getCpsResponseTable: Exception : "
					+ e.getMessage());
			// EPay2Item.exceptionNotify(e);
			return null;
		} finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getCpsResponseTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getCpsResponseTable: Release connection: "
									+ e.getMessage());
					// EPay2Item.exceptionNotify(e);
				}
			}
		}
		return cpsResponseTable;
	}

	/**
	 * DbAccess.getClientTable Gets Client table data.
	 * 
	 * @return Returns requested column information
	 */
	public static Vector getClientTable(String dbOwner, Context ctx) {
		System.out.println("DBAccess.getClientTable");
		String queryString = null;
		ResultSet rs = null;
		Connection dataConn = null;
		Validator.cleanQueryParam(dbOwner);
		Vector clientTable = new Vector();

		try {

			queryString = "select CLIENT_ID, CLIENT_CD, TRANS_TYPE from "
					+ dbOwner + ".TBV_CLIENT";
			System.out.println("DBAccess.getClientTable: " + queryString);

			dataConn = getConnection(ctx);
			PreparedStatement pstmt = dataConn.prepareStatement(queryString);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ClientBean clientRow = new ClientBean();
				clientRow.setClientId(rs.getString(1));
				clientRow.setClientCd(rs.getString(2));
				clientRow.setTransType(rs.getString(3));
				clientTable.addElement(clientRow);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("DBAccess.getClientTable: SQLException : "
					+ e.getMessage());
			// EPay2Item.exceptionNotify(e);
			return null;
		} finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getClientTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getClientTable: Release connection: "
									+ e.getMessage());
					// EPay2Item.exceptionNotify(e);
				}
			}
		}
		return clientTable;
	}

	// Lakshmi Code
	public static HashMap<String, String> getAuthTable(String dbOwner,
			Context ctx) {
		// TODO Auto-generated method stub
		System.out.println("DBAccess.getAuthTable");
		String queryString = null;
		ResultSet rs = null;
		Connection dataConn = null;
		Validator.cleanQueryParam(dbOwner);
		HashMap<String, String> authRespTable = new HashMap<String, String>();

		try {

			queryString = "select CLIENT_CD, PAYPAL_EMAIL_ADDR from " + dbOwner
					+ ".TBV_CPS_DIVISION where CURRENCY_CD = ?";
			System.out.println("DBAccess.getCpsResponseTable: " + queryString);

			dataConn = getConnection(ctx);
			// System.out.println("DataConnection " + dataConn);
			PreparedStatement pstmt = dataConn.prepareStatement(queryString);
			pstmt.setString(1, "USD");
			System.out
					.println("DBAccess.getCpsResponseTable 2: " + queryString);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authRespTable.put(rs.getString(1), rs.getString(2));

			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("DBAccess.getAuthTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.getAuthTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.getAuthTable: ErrorCode : "
					+ e.getErrorCode());
			return null;
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getAuthTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getAuthTable: Release connection: "
									+ e.getMessage());
				}
			}
		}

		return authRespTable;
	}

	/**
	 * Method to insert data to Orbital table
	 * 
	 * @param dbOwner
	 * @param ctx
	 * @param applePayData
	 */
	public static void insertToOrbitalTable(String dbOwner, Context ctx,
			ApplePayData applePayData) {
		System.out.println("DBAccess.insertToOrbitalTable");
		String insertString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		String payment_gateway = "APPLE_PAY";
		Validator.cleanQueryParam(dbOwner);

		try {
			insertString = "INSERT INTO "
					+ dbOwner
					+ ".TBV_ORBITAL_RETRY "
					+ "(EFFORT_KEY, EFFORT_KEY_OPTION, DOLLAR_VALUE, CUST_NAME, BUNDLE_DATA, BUNDLE_SIGNATURE, BUNDLE_APP_DATA, BUNDLE_EP_KEY, "
					+ "BUNDLE_PUB_HASH, BUNDLE_TRAN_ID, BUNDLE_PNW, BUNDLE_TYPE, BUNDLE_VALUE, IS_TEST_ENV, MERCHANT_ID, KEYLINE, EFFORT_TERM, "
					+ "EFFORT_VALUE, ORBITAL_SUCCESS, MAG_CODE, PAYMENT_GATEWAY, ACCOUNT_NUMBER, ACTIVITY, TAX, TOTAL_VALUE, SEG_ID, PDAT, UNIQ, IS_CRYPTOGRAM_PRESENT, CUST_ADDR_1, ZIPCODE, IS_MATCH_TO_LISTENER, TIME_STAMP) "
					+ "VALUES('" + applePayData.getEffortKey() + "','"
					+ applePayData.getEffortKeyOption() + "','"
					+ applePayData.getDollarValue() + "','"
					+ applePayData.getCustName() + "','"
					+ applePayData.getaPayData() + "','"
					+ applePayData.getaPaySign() + "','"
					+ applePayData.getaPayAppData() + "','"
					+ applePayData.getaPayEpKey() + "','"
					+ applePayData.getaPayPubHash() + "','"
					+ applePayData.getaPayTranId() + "','"
					+ applePayData.getaPayPNW() + "','"
					+ applePayData.getaPayType() + "','"
					+ applePayData.getaPayValue() + "','"
					+ applePayData.getIsTestEnv().toString() + "','"
					+ applePayData.getMerchantId() + "','"
					+ applePayData.getKeyline() + "','"
					+ applePayData.getEffortTerm() + "','"
					+ applePayData.getEffortValue() + "','"
					+ applePayData.getOrbitalSuccess() + "','"
					+ applePayData.getMagCode() + "','" + payment_gateway
					+ "','" + applePayData.getAccountNumber() + "','"
					+ applePayData.getActivity() + "','"
					+ applePayData.getTax() + "','"
					+ applePayData.getTotalValue() + "','"
					+ applePayData.getSegId() + "','" + applePayData.getPdat()
					+ "','" + applePayData.getUniq() + "','"
					+ applePayData.getIsCryptogramPresent() + "','"
					+ applePayData.getCustAddr1() + "','"
					+ applePayData.getPostalCode() + "','"
					+ applePayData.getIsMatchToListener() + "','"
					+ OrbitalHelper.getCurrentDateTime() + "')";

			System.out
					.println("DBAccess.insertToOrbitalTable: " + insertString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(insertString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertToOrbitalTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertToOrbitalTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.insertToOrbitalTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.insertToOrbitalTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.insertToOrbitalTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.insertToOrbitalTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.insertToOrbitalTable: Release connection: "
									+ e.getMessage());
				}
			}
		}
	}

	/**
	 * Method to update data to Orbital table
	 * 
	 * @param dbOwner
	 * @param ctx
	 * @param applePayData
	 */
	public static void updateOrbitalTable(String dbOwner, Context ctx,
			ApplePayData applePayData) {
		System.out.println("DBAccess.updateOrbitalTable");
		String updateString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		String payment_gateway = "APPLE_PAY";
		Validator.cleanQueryParam(dbOwner);

		try {
			updateString = "UPDATE " + dbOwner
					+ ".TBV_ORBITAL_RETRY SET KEYLINE = '"
					+ applePayData.getKeyline() + "', ORBITAL_SUCCESS = '"
					+ applePayData.getOrbitalSuccess() + "', TIME_STAMP = '"
					+ OrbitalHelper.getCurrentDateTime() + "', SEG_ID = '"
					+ applePayData.getSegId() + "', PDAT = '"
					+ applePayData.getPdat() + "', UNIQ = '"
					+ applePayData.getUniq() + "', ACCOUNT_NUMBER = '"
					+ applePayData.getAccountNumber() + "', ACTIVITY = '"
					+ applePayData.getActivity() + "', TAX = '"
					+ applePayData.getTax() + "', EFFORT_TERM = '"
					+ applePayData.getEffortTerm() + "', EFFORT_VALUE = '"
					+ applePayData.getEffortValue()
					+ "', IS_MATCH_TO_LISTENER = '"
					+ applePayData.getIsMatchToListener()
					+ "', DOLLAR_VALUE = '" + applePayData.getDollarValue()
					+ "', IS_CRYPTOGRAM_PRESENT = '"
					+ applePayData.getIsCryptogramPresent()
					+ "', TOTAL_VALUE = '" + applePayData.getTotalValue()
					+ "', CUST_ADDR_1 = '" + applePayData.getCustAddr1()
					+ "', ZIPCODE = '" + applePayData.getPostalCode() + "'"
					+ " WHERE EFFORT_KEY =  '" + applePayData.getEffortKey()
					+ "' AND EFFORT_KEY_OPTION =  '"
					+ applePayData.getEffortKeyOption() 
					+ "' AND MAG_CODE =  '"
					+ applePayData.getMagCode() + "' AND CUST_NAME =  '"
					+ applePayData.getCustName() + "' AND  PAYMENT_GATEWAY =  '"
					+ payment_gateway + "'";
			System.out.println("DBAccess.updateOrbitalTable: " + updateString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(updateString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateOrbitalTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateOrbitalTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.updateOrbitalTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.updateOrbitalTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.updateOrbitalTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.updateOrbitalTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.updateOrbitalTable: Release connection: "
									+ e.getMessage());
				}
			}
		}
	}

	/**
	 * Method to get apple pay details from Orbital table.
	 * 
	 * @param dbOwner
	 * @param ctx
	 * @param applePayData
	 * @return
	 */
	public static ApplePayData getApplePayData(String dbOwner, Context ctx,
			ApplePayData applePayData) {
		System.out.println("DBAccess.getApplePayData");
		String selectString = null;
		Connection dataConn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int count = 0;
		String payment_gateway = "APPLE_PAY";
		Validator.cleanQueryParam(dbOwner);
		try {
			selectString = "SELECT BUNDLE_DATA, BUNDLE_SIGNATURE, BUNDLE_APP_DATA, BUNDLE_EP_KEY, BUNDLE_PUB_HASH, BUNDLE_TRAN_ID, BUNDLE_PNW, BUNDLE_TYPE, BUNDLE_VALUE, MERCHANT_ID FROM "
					+ dbOwner
					+ ".TBV_ORBITAL_RETRY  WHERE EFFORT_KEY =  '"
					+ applePayData.getEffortKey()
					+ "' AND EFFORT_KEY_OPTION =  '"
					+ applePayData.getEffortKeyOption()
					+ "' AND CUST_NAME =  '"
					+ applePayData.getCustName()
					+ "' AND MAG_CODE  =  '" + applePayData.getMagCode() 
					+ "' AND  PAYMENT_GATEWAY =  '"
					+ payment_gateway + "'";
			System.out.println("DBAccess.getApplePayData: " + selectString);

			dataConn = getConnection(ctx);
			pstmt = dataConn.prepareStatement(selectString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				applePayData.setaPayData(rs.getString(1));
				applePayData.setaPaySign(rs.getString(2));
				applePayData.setaPayAppData(rs.getString(3));
				applePayData.setaPayEpKey(rs.getString(4));
				applePayData.setaPayPubHash(rs.getString(5));
				applePayData.setaPayTranId(rs.getString(6));
				applePayData.setaPayPNW(rs.getString(7));
				applePayData.setaPayType(rs.getString(8));
				applePayData.setaPayValue(rs.getString(9));
				applePayData.setMerchantId(rs.getString(10));
				count++;
			}
			if (count == 0) {
				System.out
						.println("No Data Found for query in DBAccess.getApplePayData");
				return null;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("DBAccess.getApplePayData: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.getApplePayData: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.getApplePayData: ErrorCode : "
					+ e.getErrorCode());
			return null;
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getApplePayData: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getApplePayData: Release connection: "
									+ e.getMessage());
				}
			}
		}
		return applePayData;
	}
	
	/**
	 * Method to insert data to Orbital table
	 * 
	 * @param dbOwner
	 * @param ctx
	 * @param applePayData
	 */
	public static void insertAndroidDataToOrbitalTable(String dbOwner, Context ctx,
			AndroidPayData androidPayData) {
		System.out.println("DBAccess.insertAndroidDataToOrbitalTable");
		String insertString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		String Empty_String = "Empty";
		Validator.cleanQueryParam(dbOwner);

		try {
			insertString = "INSERT INTO "
					+ dbOwner
					+ ".TBV_ORBITAL_RETRY "
					+ "(EFFORT_KEY, EFFORT_KEY_OPTION, DOLLAR_VALUE, CUST_NAME, BUNDLE_DATA, BUNDLE_SIGNATURE, BUNDLE_APP_DATA, BUNDLE_EP_KEY, "
					+ "BUNDLE_PUB_HASH, BUNDLE_TRAN_ID, BUNDLE_PNW, BUNDLE_TYPE, BUNDLE_VALUE, IS_TEST_ENV, MERCHANT_ID, KEYLINE, EFFORT_TERM, "
					+ "EFFORT_VALUE, ORBITAL_SUCCESS, MAG_CODE, PAYMENT_GATEWAY, IS_CRYPTOGRAM_PRESENT, ACCOUNT_NUMBER, ACTIVITY, TAX, TOTAL_VALUE, SEG_ID, PDAT, UNIQ, CUST_ADDR_1, ZIPCODE, IS_MATCH_TO_LISTENER, TIME_STAMP) "
					+ "VALUES('" + androidPayData.getEffortKey() + "','"
					+ androidPayData.getEffortKeyOption() + "','"
					+ androidPayData.getDollarValue() + "','"
					+ androidPayData.getCustName() + "','"
					+ androidPayData.getEphemeralPublicKey() + "','"
					+ androidPayData.getEncryptedMessage() + "','"
					+ androidPayData.getTag()+ "','"
					+ Empty_String + "','"
					+ Empty_String + "','"
					+ Empty_String + "','"
					+ androidPayData.getAndroidPayType() + "','"
					+ Empty_String + "','"
					+ androidPayData.getAndroidPayValue() + "','"
					+ androidPayData.getIsTestEnv().toString() + "','"
					+ androidPayData.getMerchantId() + "','"
					+ androidPayData.getKeyline() + "','"
					+ androidPayData.getEffortTerm() + "','"
					+ androidPayData.getEffortValue() + "','"
					+ androidPayData.getAndroidPayStatus() + "','"
					+ androidPayData.getMagCode() + "','" + AndroidPayConstants.ANDROID
					+ "','"	+ androidPayData.getIsCryptogramPresent() + "','"
					+ androidPayData.getAccountNumber() + "','"
					+ androidPayData.getActivity() + "','"
					+ androidPayData.getTax() + "','"
					+ androidPayData.getTotalValue() + "','"
					+ androidPayData.getSegId() + "','" + androidPayData.getPdat()
					+ "','" + androidPayData.getUniq() + "','"
					+ androidPayData.getCustAddr1() + "','"
					+ androidPayData.getPostalCode() + "','"
					+ androidPayData.getIsMatchToListener() + "','"
					+ AndroidPayHelper.getCurrentDateTime() + "')";

			System.out
					.println("DBAccess.insertAndroidDataToOrbitalTable: " + insertString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(insertString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertAndroidDataToOrbitalTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertAndroidDataToOrbitalTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.insertAndroidDataToOrbitalTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.insertAndroidDataToOrbitalTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.insertAndroidDataToOrbitalTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.insertAndroidDataToOrbitalTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.insertAndroidDataToOrbitalTable: Release connection: "
									+ e.getMessage());
				}
			}
		}
	}

	/**
	 * Method to update data to Orbital table
	 * 
	 * @param dbOwner
	 * @param ctx
	 * @param applePayData
	 */
	public static void updateAndroidPayToOrbitalTable(String dbOwner, Context ctx,
			AndroidPayData androidPayData) {
		System.out.println("DBAccess.updateAndroidPayToOrbitalTable");
		String updateString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		Validator.cleanQueryParam(dbOwner);

		try {
			updateString = "UPDATE " + dbOwner
					+ ".TBV_ORBITAL_RETRY SET ORBITAL_SUCCESS = '"
					+ androidPayData.getAndroidPayStatus() + "', TIME_STAMP = '"
					+ AndroidPayHelper.getCurrentDateTime() + "', IS_CRYPTOGRAM_PRESENT = '"
					+ androidPayData.getIsCryptogramPresent() + "', KEYLINE = '"
					+ androidPayData.getKeyline() + "', SEG_ID = '"
					+ androidPayData.getSegId() + "', PDAT = '"
					+ androidPayData.getPdat() + "', UNIQ = '"
					+ androidPayData.getUniq() + "', ACCOUNT_NUMBER = '"
					+ androidPayData.getAccountNumber() + "', ACTIVITY = '"
					+ androidPayData.getActivity() + "', TAX = '"
					+ androidPayData.getTax() + "', EFFORT_TERM = '"
					+ androidPayData.getEffortTerm() + "', EFFORT_VALUE = '"
					+ androidPayData.getEffortValue()
					+ "', DOLLAR_VALUE = '" + androidPayData.getDollarValue()
					+ "', TOTAL_VALUE = '" + androidPayData.getTotalValue()
					+ "', CUST_ADDR_1 = '" + androidPayData.getCustAddr1()
					+ "', IS_MATCH_TO_LISTENER = '" + androidPayData.getIsMatchToListener()
					+ "', ZIPCODE = '" + androidPayData.getPostalCode() + "'"
					+ " WHERE EFFORT_KEY =  '" + androidPayData.getEffortKey()
					+ "' AND EFFORT_KEY_OPTION =  '"
					+ androidPayData.getEffortKeyOption() 
					+ "' AND MAG_CODE =  '"
					+ androidPayData.getMagCode() 
					+ "' AND PAYMENT_GATEWAY = '"
					+ AndroidPayConstants.ANDROID
					+ "' AND CUST_NAME =  '"
					+ androidPayData.getCustName() + "'";
			System.out.println("DBAccess.updateAndroidPayToOrbitalTable: " + updateString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(updateString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateAndroidPayToOrbitalTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateAndroidPayToOrbitalTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.updateAndroidPayToOrbitalTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.updateAndroidPayToOrbitalTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.updateAndroidPayToOrbitalTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.updateAndroidPayToOrbitalTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.updateAndroidPayToOrbitalTable: Release connection: "
									+ e.getMessage());
				}
			}
		}
	}

	/**
	 * Method to get android pay details from Orbital table.
	 * 
	 * @param dbOwner
	 * @param ctx
	 * @param applePayData
	 * @return
	 */
	public static List<AndroidPayData> getAndroidPayData(String dbOwner, Context ctx) {
		System.out.println("DBAccess.getAndroidPayData");
		String selectString = null;
		Connection dataConn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int count = 0;
		Validator.cleanQueryParam(dbOwner);
		AndroidPayData androidPayData;
		List<AndroidPayData> androidPayDataList = new ArrayList<AndroidPayData>();
		try {
			selectString = "SELECT BUNDLE_DATA, BUNDLE_SIGNATURE, BUNDLE_APP_DATA, EFFORT_KEY, EFFORT_KEY_OPTION, MAG_CODE, CUST_NAME, ORBITAL_SUCCESS "
					+ ", KEYLINE, SEG_ID, PDAT, UNIQ, ACCOUNT_NUMBER, ACTIVITY, TAX, EFFORT_TERM, EFFORT_VALUE, DOLLAR_VALUE, TOTAL_VALUE, CUST_ADDR_1, ZIPCODE, BUNDLE_PNW "
					+ "FROM "
					+ dbOwner
					+ ".TBV_ORBITAL_RETRY  WHERE PAYMENT_GATEWAY =  '"
					+ AndroidPayConstants.ANDROID
					+ "' AND ORBITAL_SUCCESS !=  'Y'"
					+ "  AND KEYLINE !=  'null'"
					+ "  AND BUNDLE_DATA != 'null'";
			System.out.println("DBAccess.getAndroidPayData: " + selectString);

			dataConn = getConnection(ctx);
			pstmt = dataConn.prepareStatement(selectString);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				androidPayData = new AndroidPayData();
				androidPayData.setEphemeralPublicKey(rs.getString(1));
				androidPayData.setEncryptedMessage(rs.getString(2));
				androidPayData.setTag(rs.getString(3));
				androidPayData.setEffortKey(rs.getString(4));
				androidPayData.setEffortKeyOption(rs.getString(5));
				androidPayData.setMagCode(rs.getString(6));
				androidPayData.setCustName(rs.getString(7));
				androidPayData.setAndroidPayStatus(rs.getString(8));
				androidPayData.setKeyline(rs.getString(9));
				androidPayData.setSegId(rs.getString(10));
				androidPayData.setPdat(rs.getString(11));
				androidPayData.setUniq(rs.getString(12));
				androidPayData.setAccountNumber(rs.getString(13));
				androidPayData.setActivity(rs.getString(14));
				androidPayData.setTax(rs.getString(15));
				androidPayData.setEffortTerm(rs.getString(16));
				androidPayData.setEffortValue(rs.getString(17));
				androidPayData.setDollarValue(rs.getString(18));
				androidPayData.setTotalValue(rs.getString(19));
				androidPayData.setCustAddr1(rs.getString(20));
				androidPayData.setPostalCode(rs.getString(21));
				androidPayData.setAndroidPayType(rs.getString(22));
				androidPayDataList.add(androidPayData);
				count++;
			}
			if (count == 0) {
				System.out
						.println("No Data Found for query in DBAccess.getAndroidPayData");
				return null;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("DBAccess.getAndroidPayData: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.getAndroidPayData: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.getAndroidPayData: ErrorCode : "
					+ e.getErrorCode());
			return null;
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getAndroidPayData: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getAndroidPayData: Release connection: "
									+ e.getMessage());
				}
			}
		}
		return androidPayDataList;
	}

	public static void closeConnection(Connection conn) {

		try {
			conn.close();
		} catch (Exception e) {
			System.out.println("DBAccess: release connection error: "
					+ e.getMessage());
			// EPay2Item.exceptionNotify(e);
		}
	}

	public static Connection getConnection(Context ctx) {

		Connection dataConn = null;
		DataSource currds = null;
		try {
			currds = getDataSource(ctx);

			if (currds != null)
				dataConn = currds.getConnection();

		} catch (SQLException e) {

			System.out.println("Exception :" + e.getMessage());
			System.out.println("Exception :" + e.getErrorCode());
		}

		catch (Throwable e) {
			// Log.handleException(e);
			if (dataConn != null) {
				try {
					dataConn.close();
				} catch (Exception ee) {
					System.out.println("DBAccess: release connection: "
							+ e.getMessage());
					// EPay2Item.exceptionNotify(ee);
				}
			}
		}

		return dataConn;
	}

	private static DataSource getDataSource(Context ctx) {

		return getDataSource("java:comp/env/jdbc/epayGDB", ctx);

	}

	private static DataSource getDataSource(String dbds1, Context ctx) {

		// String anotherDs=dbds1;
		if (ds != null)
			return ds;
		try {

			// Context initContext = new InitialContext();

			// Use the web deployment property to bind the jdbc datasource to
			// the one defined in the server

			ds = (DataSource) ctx.lookup(dbds1);
			System.out.println("DBAccess: Connection established!");
			System.out.println("DS :" + ds);
		} catch (Exception e) {
			System.out.println("DBAccess Exception: Connection "
					+ e.getMessage());
			e.printStackTrace();
			ds = null;
			// EPay2Item.exceptionNotify(e);
		}

		return ds;
	}

	public static void insertToECDirectTable(String dbOwner,
			InitialContext ctx, ECDirectData ecDirectData) {
		// TODO Auto-generated method stub
		
		System.out.println("DBAccess.insertToECDirectTable");
		String insertString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		String payment_gateway = "PayPal EC Direct";
		Validator.cleanQueryParam(dbOwner);
		
		try {
			insertString = "INSERT INTO "
					+ dbOwner
					+ ".TBV_ECDIRECT_TRANS "
					+ "(EFFORT_KEY, EFFORT_KEY_OPTION,EFFORT_TERM,EFFORT_VALUE,CUST_NAME,KEYLINE, "
					+ "CUST_ADDR_1, ZIPCODE, SEG_ID, PDAT, UNIQ, TRANSID, PARENT_TRANSID,"
					+ "TRANS_NAME, RECEIPTID, DOLLAR_VALUE, AMOUNT, PYMT_STATUS, PENDING_REASON,TIME_STAMP,MAGCODE,CORR_ID,API_RC) "
					+ "VALUES('" + ecDirectData.getEffortKey() + "','"
					+ ecDirectData.getEffortKeyOption() + "','"
					+ ecDirectData.getEffortTerm() + "','"
					+ ecDirectData.getEffortValue() + "','"
					+ ecDirectData.getCustName() + "','"
					+ ecDirectData.getKeyline() + "','"
					+ ecDirectData.getCustAddr1() + "','"
					+ ecDirectData.getPostalCode() + "','"
					+ ecDirectData.getSegId() + "','"
					+ ecDirectData.getPdat()  + "','"
					+ ecDirectData.getUniq()  + "','"
				
					+ ecDirectData.getEcDirectTransID() + "','"
					+ ecDirectData.getEcDirectParentTransID() + "','"
					+ ecDirectData.getEcDirectTransName() + "','"
					+ ecDirectData.getEcDirectReceiptID() + "','"
					
					+ ecDirectData.getDollarValue() + "','"
					+ ecDirectData.getEcDirectAmt() + "','"
					+ ecDirectData.getPaymentStatus() + "','"
					+ ecDirectData.getPendingReason() + "','"
                    + PayPalECHelper.getCurrentDateTime() + "','"
					+ ecDirectData.getMagCode() + "','"
					+ ecDirectData.getEcDirectCorrID() + "','"
					+ ecDirectData.getEcDirect_API_RC() + "')";

			System.out
					.println("DBAccess.insertToECDIrectTable: " + insertString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(insertString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertToECDIrectTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertToECDIrectTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.insertToECDIrectTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.insertToECDIrectTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.insertToECDIrectTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.insertToECDIrectTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.insertToECDIrectTable: Release connection: "
									+ e.getMessage());
				}
			}
		}
		
	}

	@SuppressWarnings("null")
	public static ECDirectBean getECDirectData(String dbOwner,
			InitialContext ctx, ECDirectData ecDirectData,ECDirectBean ecDirectBean) {
		// TODO Auto-generated method stub
		
		//ECDirectBean ecDirectBean = null;
		System.out.println("DBAccess.getECDirectData");
		String selectString = null;
		String transId;
		String transName;
		String amount;
		String status;
		Connection dataConn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int count = 0;
		Validator.cleanQueryParam(dbOwner);
		try {
			
			selectString = "SELECT EFFORT_KEY, EFFORT_KEY_OPTION,EFFORT_TERM,EFFORT_VALUE,"
					+ "CUST_NAME,KEYLINE,CUST_ADDR_1, ZIPCODE, SEG_ID, PDAT, UNIQ,"
					+ "TRANSID, PARENT_TRANSID,TRANS_NAME, RECEIPTID, DOLLAR_VALUE, AMOUNT,"
					+ "PYMT_STATUS, PENDING_REASON,MAGCODE FROM "
					+ dbOwner
					+ ".TBV_ECDIRECT_TRANS  WHERE TRANSID =  '"
					+ ecDirectData.getEcDirectTransID() +  "'";
			System.out.println("DBAccess.getECDirectData: " + selectString);

			dataConn = getConnection(ctx);
			pstmt = dataConn.prepareStatement(selectString);
			rs = pstmt.executeQuery();
			System.out.println("The Get query has result set before while loop" + rs);
			while (rs.next()) {
				System.out.println("The Get query has result set" + rs);
				//System.out.println("Result Set Value number " + rs.getString(1));
				//System.out.println("Result Set Value Value" + rs.getString("TRANSID"));
							
				 ecDirectBean.setEffortKey(rs.getString(1));
				 ecDirectBean.setEffortKeyOption(rs.getString(2)) ;
				 ecDirectBean.setEffortTerm(rs.getString(3));
				 ecDirectBean.setEffortValue(rs.getString(4));
				 ecDirectBean.setCustName(rs.getString(5)) ;
				 ecDirectBean.setKeyline(rs.getString(6)) ;
				 ecDirectBean.setCustAddr1(rs.getString(7));
				 ecDirectBean.setPostalCode(rs.getString(8));
				 ecDirectBean.setSegId(rs.getString(9)) ;
				 ecDirectBean.setPdat(rs.getString(10)) ;
				 ecDirectBean.setUniq(rs.getString(11));
/*			      transId = rs.getString(1);
			      transName = rs.getString(2);
			      amount = rs.getString(3);
			      status = rs.getString(4);*/
			      
			      
				 ecDirectBean.setEcDirectTransID(rs.getString(12).trim());
				 ecDirectBean.setEcDirectParentTransID(rs.getString(13).trim());
				 ecDirectBean.setEcDirectTransName(rs.getString(14).trim());
				 ecDirectBean.setEcDirectReceiptID(rs.getString(15).trim());
				
				 ecDirectBean.setDollarValue(rs.getString(16).trim()) ;
				 ecDirectBean.setEcDirectAmt(rs.getString(17).trim())  ;
				 ecDirectBean.setPaymentStatus(rs.getString(18).trim()) ;
				 ecDirectBean.setPendingReason(rs.getString(19).trim());
				 ecDirectBean.setMagCode(rs.getString(20).trim());
				System.out.println("Values set to Bean :");
				//System.out.println("Values set to Bean :" + ecDirectBean.getEcDirectTransID() + ecDirectBean.getEcDirectTransName()  
					//	+ ecDirectBean.getEcDirectAmt() + ecDirectBean.getPaymentStatus() );
				 
				 
				 count++;
			}
			if (count == 0) {
				System.out
						.println("No Data Found for query in DBAccess.getECDirectData");
				return null;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("DBAccess.getECDirectData: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.getECDirectData: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.getECDirectData: ErrorCode : "
					+ e.getErrorCode());
			return null;
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getECDirectData: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getECDirectData: Release connection: "
									+ e.getMessage());
				}
			}
		}
		return ecDirectBean;
	}

	public static void updateECDirectTable(String dbOwner, InitialContext ctx,
			ECDirectData ecDirectData) {
		// TODO Auto-generated method stub
		System.out.println("DBAccess.updateECDirectTable");
		String updateString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		Validator.cleanQueryParam(dbOwner);

		try {
			updateString = "UPDATE " + dbOwner
					+ ".TBV_ECDIRECT_TRANS SET PYMT_STATUS  = '"
					+ ecDirectData.getPaymentStatus() + "', AMOUNT = '"
					+ ecDirectData.getEcDirectAmt() + "', TIME_STAMP = '"
					+ PayPalECHelper.getCurrentDateTime() + "', CORR_ID = '"
					+ ecDirectData.getEcDirectCorrID() + "', API_RC ='"
					+ ecDirectData.getEcDirect_API_RC() +
					"' where TRANSID = '" + ecDirectData.getEcDirectTransID() +  "'" ;

			System.out.println("DBAccess.updateECDirectTable: " + updateString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(updateString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateECDirectTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateECDirectTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.updateECDirectTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.updateECDirectTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.updateECDirectTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.updateECDirectTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.updateECDirectTable: Release connection: "
									+ e.getMessage());
				}
			}
		}		
	
	}

	public static void insertToECDirectOrphanTable(String dbOwner,
			InitialContext ctx, ECDirectData ecDirectData) {
		System.out.println("DBAccess.insertToECDirectOrphanTable");
		String insertString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		String payment_gateway = "PayPal EC Direct";
		Validator.cleanQueryParam(dbOwner);
		
		try {
			insertString = "INSERT INTO "
					+ dbOwner
					+ ".TBV_ECDIRECT_ORPHAN_TRANS "
					+ "(EFFORT_KEY, EFFORT_KEY_OPTION,EFFORT_TERM,EFFORT_VALUE,CUST_NAME,KEYLINE, MAGCODE,"
					+ "CUST_ADDR_1, ZIPCODE, SEG_ID, PDAT, UNIQ,TRANS_NAME,"
					+ " DOLLAR_VALUE, AMOUNT,TIME_STAMP,ISTRANSDONE,PARENT_TRANSID,ECBILLING_TOKEN,CORR_ID,API_RC) "
					+ "VALUES('" + ecDirectData.getEffortKey() + "','"
					+ ecDirectData.getEffortKeyOption() + "','"
					+ ecDirectData.getEffortTerm() + "','"
					+ ecDirectData.getEffortValue() + "','"
					+ ecDirectData.getCustName() + "','"
					+ ecDirectData.getKeyline() + "','"
					+ ecDirectData.getMagCode() + "','"
					+ ecDirectData.getCustAddr1() + "','"
					+ ecDirectData.getPostalCode() + "','"
					+ ecDirectData.getSegId() + "','"
					+ ecDirectData.getPdat()  + "','"
					+ ecDirectData.getUniq()  + "','"
					+ ecDirectData.getEcDirectTransName()  + "','"
					+ ecDirectData.getDollarValue() + "','"
					+ ecDirectData.getEcDirectAmt() + "','"
					+ PayPalECHelper.getCurrentDateTime() + "','"
					+ ecDirectData.getTransDone() + "','"
	
					+ ecDirectData.getEcDirectParentTransID() + "','"
					+ ecDirectData.getEcDirectBID() + "','"
			        + ecDirectData.getEcDirectCorrID() + "','"
			        + ecDirectData.getEcDirect_API_RC() + "')";			
			

			System.out
					.println("DBAccess.insertToECDIrectOrphanTable: " + insertString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(insertString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertToECDIrectOrphanTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.insertToECDIrectOrphanTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.insertToECDIrectOrphanTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.insertToECDIrectOrphanTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.insertToECDIrectOrphanTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.insertToECDIrectOrphanTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.insertToECDIrectOrphanTable: Release connection: "
									+ e.getMessage());
				}
			}
		}
		
		
	}
	
	@SuppressWarnings("null")
	public static ArrayList<ECDirectOrphanBean> getECDirectOrphanData(String dbOwner,
			InitialContext ctx) {
				
		
		ArrayList<ECDirectOrphanBean> orphanList = new ArrayList<ECDirectOrphanBean>();
		System.out.println("DBAccess.getECDirectData");
		String selectString = null;
		Connection dataConn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int count = 0;
		Validator.cleanQueryParam(dbOwner);
		try {
			
			selectString = "SELECT EFFORT_KEY, EFFORT_KEY_OPTION,EFFORT_TERM,EFFORT_VALUE,"
					+ "CUST_NAME,KEYLINE,MAGCODE,CUST_ADDR_1, ZIPCODE, SEG_ID, PDAT, UNIQ,"
					+ "TRANS_NAME,DOLLAR_VALUE, AMOUNT,"
					+ "PARENT_TRANSID, ECBILLING_TOKEN, ISTRANSDONE FROM "
					+ dbOwner
					+ ".TBV_ECDIRECT_ORPHAN_TRANS WHERE ISTRANSDONE !=  'Y'";
			System.out.println("DBAccess.getECDirectOrphan Data: " + selectString);

			dataConn = getConnection(ctx);
			pstmt = dataConn.prepareStatement(selectString);
			rs = pstmt.executeQuery();
			System.out.println("The Get query has result set before while loop");
			while (rs.next()) {
				count++;
				ECDirectOrphanBean ecDirectOrphanBean = new ECDirectOrphanBean();
				System.out.println("The Get query has result set");
						
				if(null != rs.getString(1) && "" != rs.getString(1)){
				ecDirectOrphanBean.setEffortKey(rs.getString(1));
				System.out.println("ecDirectOrphanBean Effort Key :" + ecDirectOrphanBean.getEffortKey());
				}
				
				if(null != rs.getString(2) && "" != rs.getString(2)){
				ecDirectOrphanBean.setEffortKeyOption(rs.getString(2)) ;
				System.out.println("ecDirectOrphanBean Effort Key Option :" + ecDirectOrphanBean.getEffortKeyOption());
				}
				
				if(null != rs.getString(3) && "" != rs.getString(3)){
				ecDirectOrphanBean.setEffortTerm(rs.getString(3));
				System.out.println("ecDirectOrphanBean Effort Key Term :" + ecDirectOrphanBean.getEffortTerm());
				}
				
				if(null != rs.getString(4) && "" != rs.getString(4)){
				ecDirectOrphanBean.setEffortValue(rs.getString(4));
				System.out.println("ecDirectOrphanBean Effort Value :" + ecDirectOrphanBean.getEffortValue());
				}
				
				if(null != rs.getString(5) && "" != rs.getString(5)){
				ecDirectOrphanBean.setCustName(rs.getString(5));
				System.out.println("ecDirectOrphanBean Cust Name :" + ecDirectOrphanBean.getCustName());
				}
				
				ecDirectOrphanBean.setKeyline(rs.getString(6)) ;
				ecDirectOrphanBean.setMagCode(rs.getString(7)) ;
				
				if(null != rs.getString(8) && "" != rs.getString(8)){
				ecDirectOrphanBean.setCustAddr1(rs.getString(8));
				System.out.println("ecDirectOrphanBean Cust Addr1 :" + ecDirectOrphanBean.getCustAddr1());
				}
				
				if(null != rs.getString(9) && "" != rs.getString(9)){
				ecDirectOrphanBean.setPostalCode(rs.getString(9));
				System.out.println("ecDirectOrphanBean Cust Zipcode :" + ecDirectOrphanBean.getPostalCode());
				}
				
				ecDirectOrphanBean.setSegId(rs.getString(10)) ;
				ecDirectOrphanBean.setPdat(rs.getString(11)) ;
				ecDirectOrphanBean.setUniq(rs.getString(12));

				ecDirectOrphanBean.setEcDirectTransName(rs.getString(13).trim());
    			ecDirectOrphanBean.setDollarValue(rs.getString(14).trim()) ;
    			
    			if(null != rs.getString(15) && "" != rs.getString(15)){
    			ecDirectOrphanBean.setEcDirectAmt(rs.getString(15).trim());
    			System.out.println("ecDirectOrphanBean Amount :" + ecDirectOrphanBean.getEcDirectAmt());
    			}
    			
    			if(null != rs.getString(16) && "" != rs.getString(16)){
    			    ecDirectOrphanBean.setEcDirectParentTransID(rs.getString(16).trim()) ;
    			    System.out.println("ecDirectOrphanBean Parent TransID :" + ecDirectOrphanBean.getEcDirectParentTransID());
    			    
    			}
    			else{
    				System.out.println("NO Parent TransID for record nummber :" + count);    				
    			}
    			
    			if(null != rs.getString(17) && "" != rs.getString(17)){
    			    ecDirectOrphanBean.setEcDirectBIDToken(rs.getString(17).trim());
    			    System.out.println("BID Set for record nummber :" + count);
    			}
    			else{
    				
    				System.out.println("NO BID for record nummber :" + count);
    			}
    			
    			if(null != rs.getString(18) && "" != rs.getString(18)){
        			ecDirectOrphanBean.setIsTransDone(rs.getString(18).trim());
        			System.out.println("IsTransDone :" + ecDirectOrphanBean.getIsTransDone());
        		}
    			System.out.println("Values set to Bean :");
    			// Add to list
    			orphanList.add(ecDirectOrphanBean);
			
				System.out.println("Bean set to List :");
				//count++;
				System.out.println("EC Direct - Number of Orphan records set to List :" + count);
				
			}
			if (count == 0) {
				System.out
						.println("No Data Found for query in DBAccess.getECDirectOrphanData");
				return null;
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("DBAccess.getECDirectOrphanData: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.getECDirectOrphanData: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.getECDirectOrphanData: ErrorCode : "
					+ e.getErrorCode());
			return null;
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.getECDirectOrphanData: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.getECDirectOrphanData: Release connection: "
									+ e.getMessage());
				}
			}
		}
		return orphanList;
	}	
	
	public static void updateECDirectOrphanTable(String dbOwner, InitialContext ctx,ECDirectData ecDirectData) {
		// TODO Auto-generated method stub
		System.out.println("DBAccess.updateECDirectOrphanTable");
		String updateString = null;
		Connection dataConn = null;
		PreparedStatement pstmt = null;
		int sqlRc = 0;
		Validator.cleanQueryParam(dbOwner);

		try {
			updateString = "UPDATE " + dbOwner
					+ ".TBV_ECDIRECT_ORPHAN_TRANS SET ISTRANSDONE  = '"
					+ ecDirectData.getTransDone()
					+ "' , TIME_STAMP = '"   + PayPalECHelper.getCurrentDateTime()
					+ "' , CORR_ID = '"   + ecDirectData.getEcDirectCorrID()
					+ "' , API_RC = '"   + ecDirectData.getEcDirect_API_RC()
					+ "' WHERE KEYLINE = '" + ecDirectData.getKeyline()
					+ "' AND MAGCODE = '"  + ecDirectData.getMagCode()
					+ "' AND SEG_ID = '"   + ecDirectData.getSegId()
					+ "' AND PDAT = '"     + ecDirectData.getPdat()
					+ "' AND UNIQ = '"     + ecDirectData.getUniq()
					+ "' AND TRANS_NAME = '"     + ecDirectData.getEcDirectTransName() 
					+ "' AND DOLLAR_VALUE = '"   + ecDirectData.getDollarValue()
					+ "'";

			System.out.println("DBAccess.updateECDirectOrphanTable: " + updateString);

			dataConn = getConnection(ctx);
			if (dataConn != null) {
				pstmt = dataConn.prepareStatement(updateString);
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateECDirectOrphanTable: Update sqlRc: "
								+ sqlRc);
				pstmt = dataConn.prepareStatement("commit");
				sqlRc = pstmt.executeUpdate();
				System.out
						.println("DBAccess.updateECDirectOrphanTable: Commit sqlRc: "
								+ sqlRc);
				pstmt.close();
			}
		} catch (SQLException e) {
			System.out.println("DBAccess.updateECDirectOrphanTable: Exception : "
					+ e.getMessage());
			System.out.println("DBAccess.updateECDirectOrphanTable: SQLstate : "
					+ e.getSQLState());
			System.out.println("DBAccess.updateECDirectOrphanTable: ErrorCode : "
					+ e.getErrorCode());
		}

		finally {
			if (dataConn != null) {
				try {
					dataConn.close();
					System.out
							.println("DBAccess.updateECDirectOrphanTable: dataConn.close()");
				} catch (Exception e) {
					System.out
							.println("DBAccess.updateECDirectOrphanTable: Release connection: "
									+ e.getMessage());
				}
			}
		}		
	
	}

}
