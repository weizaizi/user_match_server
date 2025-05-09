package com.ncu.usermatchserver.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ncu.usermatchserver.common.ErrorCode;
import com.ncu.usermatchserver.exception.BusinessException;
import com.ncu.usermatchserver.mapper.UserMapper;
import com.ncu.usermatchserver.model.VO.UserVO;
import com.ncu.usermatchserver.model.domain.User;
import com.ncu.usermatchserver.service.UserService;
import com.ncu.usermatchserver.utils.CheckStringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ncu.usermatchserver.contant.UserConstant.USER_LOGIN_STATUS;

/**
 * @author winter
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2025-05-05 15:33:21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public Long register(String userAccount, String password, String checkPassword) {
        if (StringUtils.isAnyBlank(userAccount, password, checkPassword)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户小于4位");
        }

        if (password.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码小于6位");
        }

        if (!StringUtils.equals(password, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }

        LambdaQueryWrapper<User> userLambdaQueryWrapper = checkUserAccountAndPassword(userAccount, password);


        long count = this.count(userLambdaQueryWrapper);

        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已经存在");
        }

        MD5 md5 = MD5.create();
        password = md5.digestHex(password);

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(password);

        boolean save = this.save(user);

        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据保存异常");
        }


        return user.getId();
    }

    private LambdaQueryWrapper<User> checkUserAccountAndPassword(String userAccount, String password) {
        if (CheckStringUtil.isSpecialChar(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号有特殊字符");
        }

        if (CheckStringUtil.isSpecialChar(password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码有特殊字符");
        }

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUserAccount, userAccount);
        return userLambdaQueryWrapper;
    }

    @Override
    public UserVO login(String userAccount, String password, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userAccount, password)) {
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }

        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户小于4位");
        }

        if (password.length() < 6) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码小于6位");
        }

        LambdaQueryWrapper<User> userLambdaQueryWrapper = checkUserAccountAndPassword(userAccount, password);
        User user = this.getOne(userLambdaQueryWrapper);

        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该用户不存在");
        }

        MD5 md5 = MD5.create();

        if (!StringUtils.equals(md5.digestHex(password), user.getUserPassword())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账户或密码错误");
        }
        UserVO result = getSafeUser(user);

        request.getSession().setAttribute(USER_LOGIN_STATUS, result);
        return result;
    }

    @Override
    public UserVO getSafeUser(User user) {
        return BeanUtil.toBean(user, UserVO.class);
    }

    @Override
    public List<UserVO> searchTeamUserByTags(List<String> tags) {
        Gson gson = new Gson();
        //拿到所有用户
        List<User> list = this.list();
        return list.stream().filter(user -> {
            if (user == null) return false;
            //用户的标签名称的集合
            Set<String> tempTagsName = gson.fromJson(user.getTags(), new TypeToken<Set<String>>() {}.getType());
            //如果用户不包含集合中的标签则不收集
            for (String tag : tags) if (!tempTagsName.contains(tag)) return false;
            return true;
        }).map(this::getSafeUser).collect(Collectors.toList());
    }


}




