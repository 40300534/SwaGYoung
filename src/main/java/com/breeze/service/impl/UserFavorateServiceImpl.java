package com.breeze.service.impl;

import com.breeze.mapper.UserProductFavorateMapper;
import com.breeze.pojo.UserProductFavorate;
import com.breeze.pojo.bo.ProductBO;
import com.breeze.service.IUserFavorateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class UserFavorateServiceImpl implements IUserFavorateService {

    @Resource
    private UserProductFavorateMapper userProductFavorateMapper;

    @Override
    public int deleteById(Long id) {
        return userProductFavorateMapper.deleteById(id);
    }

    @Override
    public int add(UserProductFavorate userProductFavorate) {
        return userProductFavorateMapper.add(userProductFavorate);
    }

    @Override
    public UserProductFavorate findById(Long productId, Long userId) {
        return userProductFavorateMapper.findByUserIdAndProductId(productId, userId);
    }

    @Override
    public int update(UserProductFavorate record) {
        return userProductFavorateMapper.update(record);
    }

    @Override
    public List<ProductBO> findAllByUserId(Long uid) {

        List<ProductBO> productBOList = new ArrayList<>();
        List<UserProductFavorate> userFavorateList = userProductFavorateMapper.findAllByUserId(uid);
        for (UserProductFavorate userFavorate : userFavorateList) {
            Long productId = userFavorate.getProductId();

            ProductBO productBO = userProductFavorateMapper.findByProductId(productId);
            productBOList.add(productBO);
        }
        return productBOList;
    }

    @Override
    public UserProductFavorate toggle(UserProductFavorate userProductFavorate) {
        return userProductFavorateMapper.findByUserIdAndProductId(userProductFavorate.getProductId(), userProductFavorate.getUserId());

    }

    /**
     * 根据用户id和商品ID判断是否是收藏
     *
     * @param userId
     * @param productId
     * @return
     */
    @Override
    public boolean isFavorate(Long userId, Long productId) {

        if (userId == null && productId == null || userId == 0 && productId == 0) {
            return false;
        } else {
            UserProductFavorate byId = userProductFavorateMapper.findByUserIdAndProductId(productId, userId);
            if (byId != null) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 收藏商品数
     * @param userId
     * @return
     */
    @Override
    public Long userFavorateNumber(Long userId) {
        return userProductFavorateMapper.userFavorateNumber(userId);
    }
}
