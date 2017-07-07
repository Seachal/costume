package costumetrade.order.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.common.page.Page;
import costumetrade.common.util.OrderNoGenerator;
import costumetrade.common.util.PingyinUtil;
import costumetrade.common.util.StringUtil;
import costumetrade.order.domain.ScFocusShop;
/*import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;*/
import costumetrade.order.domain.SpClient;
import costumetrade.order.domain.SsStoOrder;
import costumetrade.order.mapper.ScFocusShopMapper;
import costumetrade.order.mapper.SpClientMapper;
import costumetrade.order.mapper.SsStoDetailMapper;
import costumetrade.order.mapper.SsStoOrderMapper;
import costumetrade.order.query.ClientQuery;
import costumetrade.order.query.OrderQuery;
import costumetrade.order.query.ProductQuery;
import costumetrade.order.query.Rules;
import costumetrade.order.service.SpClientService;
import costumetrade.user.domain.QRCodeScanParam;
import costumetrade.user.domain.ScWeChat;
import costumetrade.user.domain.SpCustProdPrice;
import costumetrade.user.domain.SpEmployee;
import costumetrade.user.domain.SsDataDictionary;
import costumetrade.user.domain.SsPayment;
import costumetrade.user.mapper.ScWeChatMapper;
import costumetrade.user.mapper.SpCustProdPriceMapper;
import costumetrade.user.mapper.SpEmployeeMapper;
import costumetrade.user.mapper.SsDataDictionaryMapper;
import costumetrade.user.mapper.SsPaymentMapper;
import costumetrade.user.service.SpEmployeeService;


@Transactional
@Service
public class SpClientServiceImpl implements SpClientService{
	@Autowired
	private SpClientMapper spClientMapper;

	@Autowired
	private SpCustProdPriceMapper spCustProdPriceMapper;
	@Autowired
	private SpEmployeeMapper spEmployeeMapper;
	@Autowired
	private ScFocusShopMapper scFocusShopMapper;
	@Autowired
	private SpEmployeeService spEmployeeService;
	@Autowired
	private ScWeChatMapper scWeChatMapper;
	@Autowired
	private SsStoOrderMapper ssStoOrderMapper;
	@Autowired 
	private SsPaymentMapper ssPaymentMapper;
	@Autowired
	private SsDataDictionaryMapper ssDataDictionaryMapper;
	@Autowired
	private SsStoDetailMapper ssStoDetailMapper;

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
	public String saveClient(SpClient client) {
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
	public SpClient getClient(String clientId) {
		return spClientMapper.selectByPrimaryKey(clientId);
	}
	@Override
	public int updateClients(SpClient spClient) {
		SpClient c = new SpClient();
		List<String> ids = new ArrayList<String>();
		if(spClient.getCheckAllTag()!=null&&spClient.getCheckAllTag()){
			c.setType(spClient.getType());
			c.setStoreId(spClient.getStoreId());
			List<SpClient> clients = spClientMapper.select(c,null);
			if(clients!=null && clients.size()>0&&spClient.getIdArray()!=null){
				outer:for(SpClient s : clients){
					boolean b=false;
					for(String id : spClient.getIdArray()){
						if(!s.getId().equals(id)){
							ids.add(s.getId());
						}else{
							b=true;
						}
					}
					if(b&&ids.size()>0){
						for(int i=0;i<ids.size();i++){
							if(s.getId().equals(ids.get(i))){
								ids.remove(i);
								break outer;
							}
						}
					}
				}
				
			}
			
		}
		if(ids.size()>0){
			spClient.setIdArray(ids);
		}
		int save = spClientMapper.updateByPrimaryKeySelective(spClient);
		
		List<String> openids = new ArrayList<String>();
		if(save>0&&spClient.getStatus()==1){//批量删除，删除关注
			ScFocusShop shop = new ScFocusShop();
			List<SpClient> clients = spClientMapper.select(spClient,null);
			for(SpClient client : clients){
				openids.add(client.getOpenid());
			}
			if(openids.size()>0){
				shop.setOpenids(openids);
			}else{
				shop.setOpenids(null);
			}
			shop.setShopid(spClient.getStoreId());
			scFocusShopMapper.deleteByPrimaryKey(shop);
		}
		return save;
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
			for(SpClient c:clients){
				if(c!=null && StringUtils.isNotBlank(c.getDistrict())){
					distinctList.add(c.getDistrict());
				}
				
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
		if(StringUtils.isNotBlank(client.getRemarkName())){
			name = client.getRemarkName();
		}else {
			if(StringUtils.isNotBlank(client.getReallyName() )){
				name = client.getReallyName();
			}else{
				if(StringUtils.isNotBlank(client.getNickName())){
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
			SpClient client = spClientMapper.selectByPrimaryKey(param.getId());
			if(client!=null && StringUtil.isNotBlank(client.getOpenid())){
				SpClient cl = new SpClient();
				cl.setStoreId(param.getStoreId());
				cl.setType(param.getType()+"");
				cl.setOpenid(client.getOpenid());
				List<SpClient> clients = spClientMapper.select(cl, null);
				if(clients!=null&& clients.size()>0){
					if(clients.size()>=1){//清楚多余新增的数据
						object = clients.get(0);
						clients.remove(0);
					}
					if(clients.size()>0){
						for(SpClient c:clients){
							spClientMapper.deleteById(c.getId());
						}
					}
				}
			}
		}else if(param.getType()==4){
			SpEmployee employee = new SpEmployee();
			employee = spEmployeeService.employeeInit(param.getId());
			employee.setStoreId(param.getStoreId());
			employee.setId(Integer.parseInt(param.getId()));
			employee = spEmployeeMapper.selectByPrimaryKey(employee);
			if(employee!=null){
				SpEmployee e = new SpEmployee();
				e.setStoreId(param.getStoreId());
				e.setOpenid(employee.getOpenid());
				List<SpEmployee> employees = spEmployeeMapper.selects(e);
				if(employees!=null&& employees.size()>0){
					if(employees.size()>=1){//清楚多余新增的数据
						object = employees.get(0);
						employees.remove(0);
					}
					if(employees.size()>0){
						for(SpEmployee c:employees){
							spEmployeeMapper.deleteByPrimaryKey(c);
						}
					}
				}
			}
		}
		//删除未扫描成功的用户
		spEmployeeMapper.deleteEmployee(null);
		spClientMapper.deleteClient(null);		
		return object;
	}
	@Override
	public int cancelFocus(ScFocusShop focusShop) {
		return scFocusShopMapper.deleteByPrimaryKey(focusShop);
	}
	@Override
	public List<OrderQuery> financialCounting(OrderQuery query) {
		//设置卖家 买家
		OrderQuery q = setSellerAndBuyer(query);
		//默认时间从 当月第一天 到当天时间
		Calendar cale = null;
		if(query.getTimeFrom() == null||StringUtils.isBlank(query.getTimeFrom().toString())){
			 // 获取前月的第一天  
	        cale = Calendar.getInstance();  
	        cale.add(Calendar.MONTH, 0);  
	        cale.set(Calendar.DAY_OF_MONTH, 1);  
	        cale.set(Calendar.HOUR_OF_DAY,0);
	        cale.set(Calendar.MINUTE,0);
	        cale.set(Calendar.SECOND, 0);
	        cale.set(Calendar.MILLISECOND,0);
	        q.setTimeFrom(cale.getTime());
		}
		if(query.getTimeTo() == null||StringUtils.isBlank(query.getTimeTo().toString())){
			cale = Calendar.getInstance();  
	        cale.set(Calendar.HOUR_OF_DAY,23);
	        cale.set(Calendar.MINUTE,59);
	        cale.set(Calendar.SECOND, 59);
	        
			q.setTimeTo(cale.getTime());
		}
	
		List<OrderQuery> querys = ssStoOrderMapper.financialCounting(query);
		List<SsStoOrder> orders = ssStoOrderMapper.financialCountingOrders(query);
		
		OrderQuery orderQuery = new OrderQuery();
		orderQuery.setBegining("期中");
		orderQuery.setOrders(orders);
		orderQuery.setIsContinue(null);
		querys.add(orderQuery);
		return querys;
	}
	@Override
	public SsPayment initAccountInfo(OrderQuery query) {
		//设置卖家 买家
		OrderQuery q = setSellerAndBuyer(query);
		if(query.getTimeFrom() == null||StringUtils.isBlank(query.getTimeFrom().toString())){
	        q.setTimeFrom(new Date());
		}
		SpClient client = spClientMapper.selectByPrimaryKey(query.getClientId());
		if(client==null){
			return null;
		}
		//获取截止到时间的收款，欠款总数
		List<OrderQuery> querys = ssStoOrderMapper.financialCounting(query);
		OrderQuery orderQuery = querys.get(0);
		
		SsPayment pay = new SsPayment();
		pay.setReceivable(orderQuery.getReceivable());
		
		pay.setPaytime(new Date());
		pay.setPayno(OrderNoGenerator.generate(""));
		pay.setPayobjtype(query.getClientType()+"");
		pay.setName(getName(client));	
		pay.setStoreid(client.getStoreId());
		pay.setClientId(query.getClientId());
		pay.setBilltype(query.getClientType()+"");
		SsPayment repay = ssPaymentMapper.countRepay(pay);
		if(repay !=null){
			pay.setPayable(orderQuery.getPayable().subtract(repay.getPayamt()));
		}else{
			pay.setPayable(orderQuery.getPayable());
		}
		
		SsDataDictionary dict = new SsDataDictionary();
		dict.setDictGroup("PAY_TYPE");
		dict.setStoreId(client.getStoreId());
		List<SsDataDictionary> dicts = ssDataDictionaryMapper.select(dict);
		
		pay.setDictionarys(dicts);
		return pay;
	}
	
	public OrderQuery setSellerAndBuyer(OrderQuery query){
		//根据clientType 确定卖家  买家，以及是供应商对账还是客户对账
		SpClient client = spClientMapper.selectByPrimaryKey(query.getClientId());
		ScWeChat wechat1 = scWeChatMapper.selectByOpenId(client.getOpenid());//
		
		ScWeChat wechat = scWeChatMapper.selectByOpenId(query.getOpenid());//根据当前操作者的openid 获取当前店铺storeId
		if(query.getClientType()==1){//客户对账
			
			if(wechat1.getStoreid()!=null){
				query.setBuyerstoreid(wechat1.getStoreid());
			}else{
				query.setBuyerstoreid(wechat1.getUserid());
			}
			query.setStoreId(wechat.getStoreid());
			query.setSellerstoreid(wechat.getStoreid());
		}else if(query.getClientType()==2){//供应商对账
			if(wechat1.getStoreid()!=null){
				query.setSellerstoreid(wechat1.getStoreid());
				query.setStoreId(wechat1.getStoreid());
			}
			query.setBuyerstoreid(wechat.getStoreid());
			
		}
		
		
		return query;
	}
	@Override
	public Integer saveAccountInfo(SsPayment pay) {
		pay.setUpdatetime(new Date());
		return ssPaymentMapper.insertSelective(pay);
	}
	@Override
	public List<ProductQuery> clientReplenishment(OrderQuery query) {
		OrderQuery orderQuery = new OrderQuery();
		orderQuery = setSellerAndBuyer(query);
		Map<String, Object> map = new HashMap<String, Object>();
		Page page = new Page();
		page.setPageNum(query.getPageNum());
		List<ProductQuery> querys = ssStoDetailMapper.selectProductsByClient(orderQuery,page);
		List<ProductQuery> results = new ArrayList<ProductQuery>();
		if(querys!=null&& querys.size()>0){
			for(ProductQuery p : querys){
				p.setStoreId(orderQuery.getStoreId());
				map.put(p.getId(), p);
			}
			
			for(String id : map.keySet()){
				results.add((ProductQuery) map.get(id));
			}
		}
		return results;
	}
	
}
