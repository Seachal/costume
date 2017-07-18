package costumetrade.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.order.domain.SpCart;

import costumetrade.order.service.SpCartService;

@Transactional
@Service
public class SpCartServiceImpl implements SpCartService{
//	@Autowired
//	private SpCartMapper spCartMapper;
//	
//	public List<SpCart> getSpCarts(int cropId) {
//
//		return spCartMapper.getSpCarts(cropId);
//	}
//	public int saveSpCart(SpCart spCart) {
//		int save = 0;
//		//查询对应ID的员工是否存在，存在的话进行update 不存在save
//		if(spCart.getId() != null){
//			SpCart getSize = spCartMapper.selectByPrimaryKey(spCart.getId());
//			if(getSize != null){
//				save = spCartMapper.updateByPrimaryKeySelective(spCart);
//			}else {
//				save = spCartMapper.insert(spCart) ;
//			}
//		}else {
//			save = spCartMapper.insert(spCart) ;
//		}
//		
//		return save;
//		 
//	}
//	public int deleteSpCart(int id) {
//	
//		return spCartMapper.deleteByPrimaryKey(id);
//	}
//	
//	

}
