package co.liufeng.ucenter.mapper;

import co.liufeng.domain.ucenter.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Refeily
 * @since 2019-11-13
 */
public interface MemberMapper extends BaseMapper<Member> {
    Integer selectRegisterCount(Integer day);
}
