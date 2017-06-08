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








import costumetrade.common.page.Page;
import costumetrade.common.util.PingyinUtil;
/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;*/
import costumetrade.order.domain.SpClient;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.query.ClientQuery;
import costumetrade.order.query.Rules;
import costumetrade.order.service.SpClientService;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpEmployeeMapper;


@Transactional
@Service
public class SpClientServiceImpl implements SpClientService{
	@Autowired
	private SpClientMapper spClientMapper;

	@Autowired
	private SpCustProdPriceMapper spCustProdPriceMapper;
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;

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
		int operate = 0;
		String name = getName(client);
		String code = PingyinUtil.getPinYinHeadChar(name);
		String c = code.charAt(0)+"";
		try {
			Integer.parseInt(c);
			client.setFastcode("#");
		} catch (Exception e) {
			client.setFastcode(c.toUpperCase());
		}
		
		SpClient clientExist = spClientMapper.selectByPrimaryKey(client.getId());
		if(clientExist != null ){//如果ID存在，则表示修改
			operate = spClientMapper.updateByPrimaryKeySelective(client);
		}else{
			operate = spClientMapper.insertSelective(client);
		}
		return client.getId() ;
		
	}
	@Override
	public List<SpClient> getClients(SpClient spClient) {
		if(spClient.getSort() != null){
			if("timeOp".equals(spClient.getSort().getValue())){
				spClient.setTimeOp(spClient.getSort().getOp());
			}else if("nameOp".equals(spClient.getSort().getValue())){
				spClient.setNameOp(spClient.getSort().getOp());
			}else if("pointsOp".equals(spClient.getSort().getValue())){
				spClient.setPointsOp(spClient.getSort().getOp());
			}
		}
		List<Rules> rules = spClient.getRules();
		if(rules != null && rules.size()>0){
			for(int i=0 ; i< rules.size() ;i++){
				if("cateList".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					spClient.setCateList(rules.get(i).getValue());
				}else if("districtList".equals(rules.get(i).getFiled())&&(rules.get(i).getValue() !=null&&rules.get(i).getValue().size()>0)){
					spClient.setDistrictList(rules.get(i).getValue());
				}
			}
		}
		Page page = new Page();
		page.setPageNum(spClient.getPageNum());
		List<SpClient> clientList = spClientMapper.select(spClient,page);
		return clientList;
	}
	
	
	@Override
	public SpClient getClient(Integer clientId) {
		return spClientMapper.selectByPrimaryKey(clientId);
	}
	@Override
	public int updateClients(SpClient spClient) {
		
		return spClientMapper.updateByPrimaryKeySelective(spClient);
	}
	@Override
	public ClientQuery initCustomer(ClientQuery clientQuery) {
		
		ClientQuery query = new ClientQuery();
		if(clientQuery.getType() == 1){//查询客户列表
			List<SpCustProdPrice> customTypeList= new ArrayList<SpCustProdPrice>();//存放ID 和客户种类值 
			//获取客户种类
			List<SpCustProdPrice> custProdPrice = new ArrayList<SpCustProdPrice>();
			SpCustProdPrice spCustProdPrice = new SpCustProdPrice();
			spCustProdPrice.setType(2+"");
			spCustProdPrice.setStoreid(clientQuery.getStoreId());
			custProdPrice = spCustProdPriceMapper.select(spCustProdPrice);
			if(custProdPrice.size()>0){
				for(SpCustProdPrice price : custProdPrice){
					SpCustProdPrice prodPrice = new SpCustProdPrice(); 
					prodPrice.setId(Integer.parseInt(price.getCustTypeCode()));
					prodPrice.setCusttypename(price.getCusttypename());
					customTypeList.add(prodPrice);
				}
				query.setCustProdPriceList(customTypeList);
			}
		}
		//获取区域数组
		SpClient client = new SpClient();
		client.setType(clientQuery.getType()+"");
		client.setStoreId(clientQuery.getStoreId());
		List<SpClient> clients = spClientMapper.selectDistincts(client);
		
		List<String> distinctList = new ArrayList<String>();
		
		if(clients !=null && clients.size()>0){
			for(SpClient c : clients){
				distinctList.add(c.getDistrict());
			}
			query.setDistrictList(distinctList);
		}
		Page page = new Page();
		List<SpClient> clientList = spClientMapper.select(client,page);
		List<SpClient> clientLists = new ArrayList<SpClient>();
		if(clientList!=null && clientList.size() >0){
			for(SpClient spClient : clientList){
				spClient.setName(getName(spClient));
				spClient.setReallyName(null);
				spClient.setRemarkName(null);
				spClient.setNickName(null);
				clientLists.add(spClient);
			}
		}
		query.setClientLsit(clientLists);
		return query;
	}
	
	public String getName(SpClient client){
		String name = null;
		if(client.getRemarkName() != null){
			name = client.getRemarkName();
		}else {
			if(client.getReallyName() !=null){
				name = client.getReallyName();
			}else{
				if(client.getNickName() !=null){
					name = client.getNickName();
				}
			}
		}
		return name;
	}
	@Override
	public Object scanQRCodeOk(QRCodeScanParam param) {
		Object object = null;
		if(param.getType()==1||param.getType()==2||param.getType()==3){
			object = spClientMapper.selectByPrimaryKey(param.getId());
		}else if(param.getType()==4){
			SpEmployee employee = new SpEmployee();
			employee.setStoreId(param.getStoreId());
			employee.setId(param.getId());
			object = spEmployeeMapper.selectByPrimaryKey(employee);
		}
		return object;
	}
	
}
