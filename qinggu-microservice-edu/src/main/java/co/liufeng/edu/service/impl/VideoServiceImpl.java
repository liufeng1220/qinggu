package co.liufeng.edu.service.impl;

import co.liufeng.domain.edu.Video;
import co.liufeng.edu.mapper.VideoMapper;
import co.liufeng.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author Refeily
 * @since 2019-10-30
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
