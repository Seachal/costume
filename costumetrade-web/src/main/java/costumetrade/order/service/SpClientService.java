package costumetrade.order.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpClient;

@Service
@Transactional
public interface SpClientService {
	
	public ResponseEntity<byte[]> getTwoDimension(String url,int width,int height);
	/*
	 * 生成二维码
	 * */
	void getTwoDimension1(String url, HttpServletResponse resp, int width,
			int height);
	
	public int saveClient(SpClient client);
	
	public List<SpClient> getClients(SpClient spClient);
	
	public SpClient getClient(Integer clientId);
	
	public int deleteClient(Integer clientId);
}
