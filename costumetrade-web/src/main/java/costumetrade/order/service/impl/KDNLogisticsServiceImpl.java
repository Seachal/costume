package costumetrade.order.service.impl;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.service.KDNLogisticsService;

@Transactional
@Service
public class KDNLogisticsServiceImpl implements KDNLogisticsService{
	public static Logger logger = Logger.getLogger(KDNLogisticsServiceImpl.class);

	@Override
	public String orderOnlineByJson() throws Exception {
		return null;
	}
	
	

}
