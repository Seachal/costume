/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年10月19日
 */
package costumetrade.common.wenqian.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import costumetrade.common.util.HttpClientUtils;
import costumetrade.common.util.MessageFormatUtil;
import costumetrade.common.wenqian.constant.WQAPIConstants;

/**
 * 文签电子签章帮助类
 * 
 * @author liyb
 * @version WQSignUtils.java,2016年10月19日 下午3:10:02
 */
public class WQSignUtils {

	private static final String CONTRACT = "-借贷合同";

	/**
	 * 获取授权码
	 * 
	 * @return
	 */
	public static String getToken() {

		List<BasicNameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("client_id", WQAPIConstants.CLIENT_ID));
		nvps.add(new BasicNameValuePair("client_secret", WQAPIConstants.CLIENT_SECRET));
		nvps.add(new BasicNameValuePair("grant_type", WQAPIConstants.GRANT_TYPE));
		nvps.add(new BasicNameValuePair("scope", WQAPIConstants.SCOPE));

		String response = HttpClientUtils.doPost(WQAPIConstants.token_api,
				new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
		JSONObject responseJSON = JSONObject.parseObject(response);
		if (responseJSON.containsKey("access_token")) {
			return responseJSON.get("access_token").toString();
		}
		throw new RuntimeException("获取文签授权码失败");
	}

	/**
	 * 同步用户到文签
	 * 
	 * @param token
	 *            授权码
	 * @param entName
	 *            企业名称（公司名称）
	 * @param adminUser
	 *            用户名称
	 * @param adminPhone
	 *            用户电话
	 * @param certType
	 *            注册类型
	 * @param licenseCode
	 *            营业执照注册号
	 * @param adminIdenCode
	 *            签署人身份证号
	 * @return 是否同步成功(true:成功 false:失败)
	 */
	public static boolean syncUsertowq(String token, String entName, String adminUser, String adminPhone,
			Integer certType, String licenseCode, String adminIdenCode) {
		boolean bool = false;
		try {
			if (StringUtils.isNotEmpty(token)) {
				List<BasicNameValuePair> nvps = new ArrayList<>();
				nvps.add(new BasicNameValuePair("client_id", WQAPIConstants.CLIENT_ID));
				nvps.add(new BasicNameValuePair("access_token", token));
				nvps.add(new BasicNameValuePair("ent_name", entName));// 企业名称
				nvps.add(new BasicNameValuePair("admin_user", adminUser));// 用户姓名
				nvps.add(new BasicNameValuePair("admin_phone", adminPhone));// 用户电话
				nvps.add(new BasicNameValuePair("share_id", adminPhone));// 用户唯一标识
				nvps.add(new BasicNameValuePair("admin_user", adminUser));
				nvps.add(new BasicNameValuePair("admin_user", adminUser));

				if (certType != null) {
					nvps.add(new BasicNameValuePair("cert_type", String.valueOf(certType)));
				}
				if (StringUtils.isNotEmpty(licenseCode)) {
					nvps.add(new BasicNameValuePair("license_code", licenseCode));
				}
				if (StringUtils.isNotEmpty(adminIdenCode)) {
					nvps.add(new BasicNameValuePair("admin_iden_code", adminIdenCode));
				}

				String response = HttpClientUtils.doPost(WQAPIConstants.sync_user_api,
						new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
				System.err.println(response);
				JSONObject responseJSON = JSONObject.parseObject(response);
				if (responseJSON.containsKey("error_code")) {
					int errorcode = responseJSON.getIntValue("error_code");
					if (errorcode == WQAPIConstants.success) {
						bool = true;
					} else {
						if (errorcode == WQAPIConstants.phone_exist_error
								|| errorcode == WQAPIConstants.company_exist_error
								|| errorcode == WQAPIConstants.user_exist_error) {
							// 此处调用更改用户信息的接口
							bool = true;
						}
					}
				}
			}
		} catch (Exception e) {
			bool = false;
			e.printStackTrace();
		}
		return bool;
	}

	/**
	 * 上传合同
	 * 
	 * @param token
	 *            授权码
	 * @param shareId
	 *            用户唯一编号
	 * @param orderNo
	 *            订单号
	 * @param file
	 *            文件对象
	 * @return 返回上传成功后文档ID
	 */
	public static String uploadContract(String token, String shareId, String orderNo, File file) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("client_id", WQAPIConstants.CLIENT_ID);
		params.put("share_id", shareId);
		params.put("doc_name", orderNo + CONTRACT);
		String upload_api = WQAPIConstants.upload_api + "?access_token=" + token;
		String response = UploadContractUtils.doUploadFile(upload_api, file, "document", params);
		JSONObject responseJSON = JSONObject.parseObject(response);
		if (responseJSON.containsKey("error_code")) {
			Integer errorcode = responseJSON.getInteger("error_code");
			if (WQAPIConstants.success.equals(errorcode)) {
				return responseJSON.getString("document_id");
			}
		}
		throw new RuntimeException("上传合同失败,消息："+response);

	}

	/**
	 * 发起合同关键字签署
	 * 
	 * @param token
	 *            授权码
	 * @param phoneA
	 *            甲方用户手机号
	 * @param phoneB
	 *            乙方用户手机号
	 * @param docid
	 *            文档ID
	 * @param partAkey
	 *            甲方签署关键字
	 * @param partBkey
	 *            乙方签署关键字
	 * @return
	 */
	public static String sendKeyword(String token, String phoneA, String phoneB, String docid, String partAkey,
			String partBkey) {
		String keyword = "";
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("client_id", WQAPIConstants.CLIENT_ID);
			params.put("access_token", token);
			params.put("share_id", phoneA);
			params.put("document_id", docid);

			JSONObject details = new JSONObject();
			JSONArray signerArray = new JSONArray();
			JSONObject signer1 = new JSONObject();
			signer1.put("phone", phoneA);
			signer1.put("type", 20);
			signerArray.add(signer1);

			if (StringUtils.isNotEmpty(phoneB)) {
				JSONObject signer2 = new JSONObject();
				signer2.put("phone", phoneB);
				signer2.put("type", 20);
				signerArray.add(signer2);
			}

			details.put("signer", signerArray);

			JSONArray keywordArray = new JSONArray();
			JSONObject keyword1 = new JSONObject();
			keyword1.put("phone", phoneA);
			keyword1.put("word", partAkey);
			keywordArray.add(keyword1);

			if (StringUtils.isNotEmpty(phoneB)) {
				JSONObject keyword2 = new JSONObject();
				keyword2.put("phone", phoneB);
				keyword2.put("word", partBkey);
				keywordArray.add(keyword2);
			}

			details.put("keyword", keywordArray);
			params.put("details", details);
			System.err.println(params);
			String sendkeyword_api = MessageFormatUtil.messageFormat(WQAPIConstants.sendkeyword_api, docid);
			String response = HttpClientUtils.doPost(sendkeyword_api, params);
			JSONObject responseJSON = JSONObject.parseObject(response);
			System.err.println("发起合同关键字签署：" + responseJSON);
			if (responseJSON.containsKey("error_code")) {
				int errorcode = responseJSON.getIntValue("error_code");
				if (errorcode == WQAPIConstants.success) {
					JSONArray signerarray = responseJSON.getJSONArray("signer");
					// JSONArray keywordarray =
					// JSONArray.fromObject(responseJSON.get("keyword"));
					keyword = signerarray + "";
				}
			}
		} catch (Exception e) {
			keyword = "";
			e.printStackTrace();
		}
		return keyword;
	}

	public static void main(String[] args) {
		String token = getToken();
		System.err.println(token);
		String entNameA = "BBBB担保方";
		String adminUserA = "担保BB";
		String adminPhoneA = "15809999991";
		boolean bool1 = syncUsertowq(token, entNameA, adminUserA, adminPhoneA, null, "", "");
		System.err.println("同步甲方用户：" + bool1);
		String entNameB = "BBBB资金方";
		String adminUserB = "资金BB";
		String adminPhoneB = "15809999992";
		boolean bool2 = syncUsertowq(token, entNameB, adminUserB, adminPhoneB, null, "", "");
		System.err.println("同步乙方用户：" + bool2);

		String orderNo = "nfdb20161516984789";
		File file = new File("E:\\wq.docx");
		if (bool1 && bool2) {
			String documentId = uploadContract(token, adminPhoneA, orderNo, file);
			if (StringUtils.isNotEmpty(documentId)) {
				System.err.println("上传合同成功,文档ID：" + documentId);
				String signer = sendKeyword(token, adminPhoneA, adminPhoneB, documentId, "甲方", "乙方");
				System.err.println(signer);
				File stampfile = new File("E:\\image\\hr-stamp.png");
				String entName = "浙江惠瀜网络";
				SignContractUtil.uploadstamp(token, adminPhoneA, stampfile, entName);
			} else {
				System.err.println("上传合同失败!");
			}

		}

	}

}
