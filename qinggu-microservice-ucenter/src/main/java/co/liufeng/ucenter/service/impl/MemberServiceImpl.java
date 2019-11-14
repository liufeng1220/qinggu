package co.liufeng.ucenter.service.impl;

import co.liufeng.ucenter.entity.Member;
import co.liufeng.ucenter.mapper.MemberMapper;
import co.liufeng.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Refeily
 * @since 2019-11-13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {
    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }
}
