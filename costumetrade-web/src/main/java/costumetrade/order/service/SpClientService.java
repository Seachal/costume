package costumetrade.order.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.ScFocusShop;
import costumetrade.order.domain.SpClient;
import costumetrade.order.query.ClientQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.SsPayment;

@Service
@Transactional
public interface SpClientService {
	
	public ResponseEntity<byte[]> getTwoDimension(String url,int width,int height);
	/*
	 * 生成二维码
	 * */
	void getTwoDimension1(String url, HttpServletResponse resp, int width,
			int height);
	
	public String saveClient(SpClient client);
	
	public List<SpClient> getClients(SpClient spClient);
	
	public SpClient getClient(String clientId);
	
	public int updateClients(SpClient spClient);
	/**
	 * 取消关注
	 * */
	public int cancelFocus(ScFocusShop focusShop);
	
	/**
	 * 查询初始化
	 * */
	public ClientQuery initCustomer(ClientQuery clientQuery);
	
	/**
	 * 扫描二维码 扫好了
	 * 
	 * */
	public Object scanQRCodeOk(QRCodeScanParam param);
	
	public List<OrderQuery> financialCounting(OrderQuery query);
	/**
	 * 弹出客户 供应还款页面初始化
	 * **/
	public SsPayment initAccountInfo(OrderQuery query);
	/**
	 * 保存还款单
	 * */
	public Integer saveAccountInfo(SsPayment pay);
}
