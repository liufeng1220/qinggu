package co.liufeng.ucenter.service;

import co.liufeng.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Refeily
 * @since 2019-11-13
 */
public interface MemberService extends IService<Member> {
    Integer countRegisterByDay(String day);
}
