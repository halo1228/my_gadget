package com.lg.sys.model.converter;

import com.lg.sys.model.entity.User;
import com.lg.sys.model.param.user.UserAddParam;
import com.lg.sys.model.param.user.UserEditParam;
import com.lg.sys.model.param.user.UserPageParam;
import com.lg.sys.model.vo.user.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author liuga
 * @since 2023-02-15 14:35
 * Description:
 * 用户模块 bean 相关转换器
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
    /*
     * 这个方法就是用于实现对象属性复制的方法
     *@Mapping(source = "numberOfSeats", target = "seatCount")
     * @Mapping 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性
     */

    /**
     * 添加参数转换 PO（Persistant Object）持久对象
     */
    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "userCode", target = "userCode"),
            @Mapping(source = "sex", target = "sex"),
            @Mapping(source = "tel", target = "tel"),
            @Mapping(source = "birthday", target = "birthday"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "avatar", target = "avatar"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "idx", target = "idx")
    })
    User userAddParam2User(UserAddParam userAddParam);

    /**
     * 编辑参数 转换 po
     */
    User userEditParam2User(UserEditParam userEditParam);

    User userPageParam2User(UserPageParam userPageParam);


    UserVo user2UserVo(User user);
}
