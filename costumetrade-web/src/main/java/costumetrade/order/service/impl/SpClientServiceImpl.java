package costumetrade.order.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.service.SpClientService;
/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;*/
import costumetrade.order.domain.SpClient;

import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.mapper.SpCustProdPriceMapper;


@Transactional
@Service
public class SpClientServiceImpl implements SpClientService{
	@Autowired
	private SpClientMapper spClientMapper;

	@Autowired
	private SpCustProdPriceMapper spCustProdPriceMapper;

	/** 
	 *  生成web版本二维码 
	 * @param url 要生成二维码的路径 
	 * @param response response对象 
	 * @param width 二维码宽度 
	 * @param height 二维码高度 
	 * @throws IOException 
	 */

	@Override
	public ResponseEntity<byte[]> getTwoDimension(String url,
			int width, int height) {
		ServletOutputStream stream = null;
		ByteArrayOutputStream out = null;
		if(url!=null && !"".equals(url)){
			
			try {
				/*QRCodeWriter writer = new QRCodeWriter();
				
				BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
				//MatrixToImageWriter.writeToStream(m, "png", stream);
				BufferedImage image = MatrixToImageWriter.toBufferedImage(m);
				out = new ByteArrayOutputStream();
				ImageIO.write(image, "png", out);*/
				
			} catch (Exception e2) {
				e2.getStackTrace();			
			}
		}
		return new ResponseEntity<byte[]>(out.toByteArray(), HttpStatus.CREATED);
	}
	@Override
	public void getTwoDimension1(String url,HttpServletResponse resp,
			int width, int height) {
		ServletOutputStream stream = null;
		ByteArrayOutputStream out = null;
		if(url!=null && !"".equals(url)){
			
			try {
/*				QRCodeWriter writer = new QRCodeWriter();
				stream = resp.getOutputStream();
				BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
				MatrixToImageWriter.writeToStream(m, "png", stream);
				BufferedImage image = toBufferedImage(m);
//				out = new ByteArrayOutputStream();
				ImageIO.write(image, "png", stream);*/
				
			} catch (Exception e2) {
				e2.getStackTrace();			
			}
		}
		
	}
	
/*	  private static BufferedImage toBufferedImage(BitMatrix matrix) {
		  int width = matrix.getWidth();
		  int height = matrix.getHeight();
		  BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		  for (int x = 0; x < width; x++) {
			  for (int y = 0; y < height; y++) {
		                  image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
		      }
		  }
		  return image;
	}*/
	@Override
	public int saveClient(SpClient client) {
		if(client.getId() == null){
			int custProdInt = spCustProdPriceMapper.insert(client.getProdPrice());
			client.setCate(String.valueOf(custProdInt));
			return spClientMapper.insertSelective(client);
		}else{
			spCustProdPriceMapper.updateByPrimaryKey(client.getProdPrice());
			return spClientMapper.updateByPrimaryKeySelective(client);
		}
		
	}
	@Override
	public List<SpClient> getClients(SpClient spClient) {
		List<SpClient> clientList = spClientMapper.select(spClient);
		return clientList;
	}
	
	
	@Override
	public SpClient getClient(Integer clientId) {
		return spClientMapper.selectByPrimaryKey(clientId);
	}
	@Override
	public int deleteClient(Integer clientId) {
		return spClientMapper.deleteById(clientId);
	}
	@Override
	public List<SpCustProdPrice> initCustomer(Integer storeId) {
		List<SpCustProdPrice> customTypeList= new ArrayList<SpCustProdPrice>();//存放ID 和客户种类值 
		//获取客户种类
		List<SpCustProdPrice> custProdPrice = new ArrayList<SpCustProdPrice>();
		SpCustProdPrice spCustProdPrice = new SpCustProdPrice();
		spCustProdPrice.setType(2+"");
		spCustProdPrice.setStoreid(storeId);
		custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
		if(custProdPrice.size()>0){
			for(SpCustProdPrice price : custProdPrice){
				SpCustProdPrice prodPrice = new SpCustProdPrice(); 
				prodPrice.setId(Integer.parseInt(price.getCustTypeCode()));
				prodPrice.setCusttypename(price.getCusttypename());
				customTypeList.add(prodPrice);
			}
		}
		return customTypeList;
	}
	
}
