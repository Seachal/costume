package costumetrade.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import costumetrade.user.domain.ScWeChat;
@Mapper
public interface ScWeChatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScWeChat record);

    int insertSelective(ScWeChat record);

    ScWeChat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScWeChat record);

    int updateByPrimaryKey(ScWeChat record);
    
    ScWeChat selectByOpenId(String openid);
    
    ScWeChat selectByUnionid(String unionid);
}