/*
 * huirong Inc.
 * Copyright (c) 2016 All Rights Reserved.
 * Author     :liyb
 * Create Date:2016年10月20日
 */
package costumetrade.common.wenqian.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import costumetrade.common.util.HttpClientUtils;
import costumetrade.common.util.MessageFormatUtil;
import costumetrade.common.wenqian.constant.WQAPIConstants;

/**
 * 文签电子签章主工具类
 * 
 * @author liyb
 * @version SignContractUtil.java,2016年10月20日 下午4:14:10
 */
public class SignContractUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SignContractUtil.class);

	private static String STAMP = "-文签专用章";
	private static String TOKEN = "";// 授权码
	private static String SHARE_ID = "";// 用户唯一标识(甲方手机号)
	private static String phoneB = "";// 乙方手机号

	public static void main(String[] args) {
		String orderNo = "nfdb20161516984789";
		File file = new File("E:\\wq.docx");
		String syncent = "{\"partA\":{\"entName\": \"BBBB担保方\",\"adminUser\": \"担保BB\",\"adminPhone\": \"15809999991\"},\"partB\": {\"entName\": \"BBBB资金方\",\"adminUser\": \"资金BB\",\"adminPhone\": \"15809999992\"}}";
		JSONObject keyword = initKeyword(orderNo, syncent, file, "甲方", "乙方");
		System.err.println(keyword);
	}

	/**
	 * 1、初始化合同数据以及上传合同文件到文签
	 * 
	 * @param orderNo
	 *            订单号
	 * @param syncent
	 *            同步用户
	 * @param file
	 * @param partAkey
	 *            甲方签署关键字
	 * @param partBkey
	 *            乙方签署关键字
	 * @return
	 */
	public static JSONObject initKeyword(String orderNo, String syncent, File file, String partAkey, String partBkey) {
		/**
		 * 初始化token
		 */
		TOKEN = WQSignUtils.getToken();
		boolean bool = syncPart(syncent);
		if (!bool) {
			throw new RuntimeException("同步企业用户失败");
		}

		String documentId = WQSignUtils.uploadContract(TOKEN, SHARE_ID, orderNo, file);
		String signer = WQSignUtils.sendKeyword(TOKEN, SHARE_ID, phoneB, documentId, partAkey, partBkey);
		JSONObject data = new JSONObject();
		data.put("access_token", TOKEN);
		data.put("shareA_id", SHARE_ID);// 甲方用户唯一标识
		data.put("shareB_id", phoneB);// 乙方用户唯一标识
		data.put("document_id", documentId);
		data.put("signer", signer);
		return data;

	}

	/**
	 * 同步企业用户
	 * 
	 * @param syncent
	 * @return
	 */
	private static boolean syncPart(String syncent) {
		boolean bool = false;
		try {
			String phoneA = "";
			String entName = "";
			Integer certType = null;
			String licenseCode = "";
			String adminIdenCode = "";
			JSONObject syncentObj = JSONObject.parseObject(syncent);
			JSONObject partA = syncentObj.getJSONObject("partA");
			phoneA = partA.getString("adminPhone");
			SHARE_ID = phoneA;
			entName = partA.getString("entName");
			if (partA.containsKey("certType")) {
				certType = partA.getIntValue("certType");
			}
			if (partA.containsKey("licenseCode")) {
				licenseCode = partA.getString("licenseCode");
			}
			if (partA.containsKey("adminIdenCode")) {
				adminIdenCode = partA.getString("adminIdenCode");
			}
			boolean boolPartA = WQSignUtils.syncUsertowq(TOKEN, entName, partA.getString("adminUser"), phoneA, certType,
					licenseCode, adminIdenCode);
			LOGGER.info("同步用户企业名称：{} 的状态为:{}",entName,boolPartA);

			boolean boolPartB = true;
			if (syncentObj.containsKey("partB")) {
				JSONObject partB = syncentObj.getJSONObject("partB");
				phoneB = partB.getString("adminPhone");
				entName = partB.getString("entName");
				if (partB.containsKey("certType")) {
					certType = partB.getIntValue("certType");
				}
				if (partB.containsKey("licenseCode")) {
					licenseCode = partB.getString("licenseCode");
				}
				if (partB.containsKey("adminIdenCode")) {
					adminIdenCode = partB.getString("adminIdenCode");
				}
				boolPartB = WQSignUtils.syncUsertowq(TOKEN, entName, partB.getString("adminUser"), phoneB, certType,
						licenseCode, adminIdenCode);
				System.err.println("同步用户企业名称：" + entName + " 的状态为:" + boolPartB);
			}

			if (boolPartA && boolPartB) {
				bool = true;
			}
		} catch (Exception e) {
			bool = false;
			LOGGER.error("文签同步企业用户失败", e);
		}
		return bool;
	}

	/**
	 * 2、上传签章(调用此方法前先查询数据库是否存在签章ID,如存在则跳过此步骤)
	 * 
	 * @param token
	 *            授权码
	 * @param shareId
	 *            用户唯一标识（即用户电话）
	 * @param stampfile
	 *            签章文件路径
	 * @param entName
	 *            企业名称
	 * @return 返回签章的stamp_id（0：上传签章失败 其他：上传成功后的签章ID）
	 */
	public static int uploadstamp(String token, String shareId, File stampfile, String entName) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("client_id", WQAPIConstants.CLIENT_ID);
		params.put("share_id", shareId);
		params.put("stamp_name", entName + STAMP);
		String upload_api = WQAPIConstants.upload_stamp_api + "?access_token=" + token;
		String response = UploadContractUtils.doUploadFile(upload_api, stampfile, "stamp", params);
		JSONObject responseJSON = JSONObject.parseObject(response);
		if (responseJSON.containsKey("error_code")) {
			int errorcode = responseJSON.getIntValue("error_code");
			if (errorcode == WQAPIConstants.success) {
				return responseJSON.getIntValue("stamp_id");
			}
		}
		throw new RuntimeException("上传签章失败");
	}

	/**
	 * 3、签署合同（甲方和乙方都调用）
	 * 
	 * @param token
	 *            授权码
	 * @param documentId
	 *            文档ID
	 * @param shareId
	 *            用户唯一标识
	 * @param stamps
	 *            签章数据数组JSON格式
	 */
	public static void sign(String token, String documentId, String shareId, String stamps) {

		List<BasicNameValuePair> nvps = new ArrayList<>();
		nvps.add(new BasicNameValuePair("client_id", WQAPIConstants.CLIENT_ID));
		nvps.add(new BasicNameValuePair("share_id", shareId));
		nvps.add(new BasicNameValuePair("document_id", documentId));
		nvps.add(new BasicNameValuePair("stamps", stamps));
		nvps.add(new BasicNameValuePair("access_token", token));
		String sign_api = MessageFormatUtil.messageFormat(WQAPIConstants.sign_api, documentId);
		String response = HttpClientUtils.doPost(sign_api, new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8));
		JSONObject responseJSON = JSONObject.parseObject(response);
		LOGGER.info(">>>>>>>>>签署合同：{}", responseJSON);
		if (responseJSON.containsKey("error_code")) {
			int errorcode = responseJSON.getIntValue("error_code");
			if (errorcode == WQAPIConstants.success) {
				return;
			}
		}
		throw new RuntimeException("签署合同失败");
	}
}
