package costumetrade.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import costumetrade.user.domain.SpMenu;
import costumetrade.user.mapper.SpMenuMapper;
import costumetrade.user.service.SpMenuService;

@Transactional
@Service
public class SpMenuServiceImpl implements SpMenuService{
	@Autowired
	private SpMenuMapper spMenuMapper;

	@Override
	public List<SpMenu> getAllMenus() {
		// TODO Auto-generated method stub
		return spMenuMapper.getAllMenus();
	}
	
	

}
