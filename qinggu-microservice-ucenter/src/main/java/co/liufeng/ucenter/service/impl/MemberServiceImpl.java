package co.liufeng.ucenter.service.impl;

import co.liufeng.domain.ucenter.Member;
import co.liufeng.ucenter.mapper.MemberMapper;
import co.liufeng.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.dubbo.config.annotation.Service;

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
        System.out.println("来了，哥哥");
        int i = Integer.parseInt(day);
        Integer integer = baseMapper.selectRegisterCount(i);
        System.out.println("打印"+integer);
        return integer;
    }
}
