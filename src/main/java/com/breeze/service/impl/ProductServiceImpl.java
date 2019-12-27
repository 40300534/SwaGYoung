package com.breeze.service.impl;

import com.breeze.enums.BidTradingStatusEnum;
import com.breeze.mapper.*;
import com.breeze.pojo.ProductBid;
import com.breeze.pojo.ProductImage;
import com.breeze.pojo.Product;
import com.breeze.pojo.bo.*;
import com.breeze.service.IProductService;
import com.breeze.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wdnnccey
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    @Value("${uploadImage.productPathPrefix}")
    private String productUploadDir;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductBidMapper productBidMapper;
    @Autowired
    private ProductImageMapper productImageMapper;

    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Autowired
    private UserProductFavorateMapper userProductFavorateMapper;

    @Autowired
    private ProductAskMapper productAskMapper;


    @Override
    public void add(Product product, List<String> productImagePaths) {
        product.setStatus(0);
        product.setCreateTime(new Date());
        product.setImageFolder(productUploadDir);
        productMapper.add(product);
    }


    @Override
    public int deleteById(Long id) {
        return productMapper.deleteById(id);
    }

    @Override
    public int update(Product product) {
        return productMapper.update(product);
    }


    @Override
    public List<ProductBO> findAll() {
        List<ProductBO> productBOs = productMapper.findAll();
        addMasterPicture(productBOs);
        return productBOs;
    }

    @Override
    public ProductBO findProductBaseInfoByProductId(Long productId) {
        return productMapper.findProductBaseInfoByProductId(productId);
    }

    @Override
    public ProductBO findProductBuyBaseInfoByProductId(Long productId) {
        //如果商品已经都售空，需要返回商品基本信息。
        ProductBO productBO = productMapper.findProductBuyBaseInfoByProductId(productId);
        if(productBO == null){
            ProductBO product = findProductBaseInfoByProductId(productId);
            product.setMasterPicture(serverUrlPrefix + product.getMasterPicture());
            return product;
        }else{
            productBO.setMasterPicture(serverUrlPrefix + productBO.getMasterPicture());
            return productBO;
        }

    }

    @Override
    public ProductBO findProductSellBaseInfoByProductId(Long productId) {
        //查询当前商品是否有人出价
        List<ProductBid> productBid = productBidMapper.findByProductIdAndStatus(productId, BidTradingStatusEnum.PRODUCT_LISTED_PAID.getCode());
        if (productBid.size()>=1) {
            ProductBO product = productMapper.findProductSellBaseInfoByProductId(productId);
            product.setMasterPicture(serverUrlPrefix + product.getMasterPicture());
            return product;
        } else {
            ProductBO product = findProductBaseInfoByProductId(productId);
            product.setMasterPicture(serverUrlPrefix + product.getMasterPicture());
            return product;
        }

    }

    /*@Override
    public ProductBO findProductBaseInfoByProductId(Long productId) {
        ProductBO product = productMapper.findProductBuyBaseInfoByProductId(productId);
        product.setMasterPicture(serverUrlPrefix + product.getMasterPicture());
        return product;
    }*/

    @Override
    public Product findById(Long id) {
        return productMapper.findById(id);
    }


    @Override
    public List<ProductBO> findByCategoryId(Integer id) {
        List<ProductBO> productBOs = productMapper.findByProductCategoryId(id);
        addMasterPicture(productBOs);
        return productBOs;
    }

    @Override
    public List<ProductBO> findByNameAndCategoryId(String name, Integer categoryId) {
        if (!StringUtils.isEmpty(name)) {
            List<ProductBO> productBOs = productMapper.findByNameAndCategoryId(name, categoryId);
            addMasterPicture(productBOs);
            return productBOs;
        } else {
            return this.findAll();
        }
    }

    @Override
    public ProductDetailsBO findProductDetailsById(Long id) {
        Product product = findById(id);
        ProductDetailsBO productDetailsBO = productMapper.findProductDetailsById(id);

        if (StringUtils.isEmpty(id) || product == null) {
            return null;
        } else if (productDetailsBO == null) {
            return null;
        }

        ProductBid highBidByProductId = productBidMapper.findHighBidByProductId(id);

        if (highBidByProductId == null) {
            productDetailsBO.setMaxPrice(null);
        } else {
            productDetailsBO.setMaxPrice(highBidByProductId.getPrice());
        }

        //设置历史成交价格
        BigDecimal lastTransactionPrice = findLastTransactionPriceByProductId(id);
        productDetailsBO.setLastTransactionPrice(lastTransactionPrice);
        //设置产品所有图片
        List<ProductImage> productImages = productImageMapper.findProductAllImageByProductId(id);
        productImages.forEach(o -> {
            o.setUrl(serverUrlPrefix + o.getUrl());
        });
        //设置产品属性
        ProductAttributesBO productPublicAttributes = productMapper.findProductPublicAttributesByProductId(id);
        //设置商品特有属性
        List<ProductUniqueAttributeBO> uniqueAttributes = productAttrValueMapper.findUniqueAttributeByProductId(id);
        productPublicAttributes.setAttributes(uniqueAttributes);
        productDetailsBO.setProductAttributes(productPublicAttributes);
        List<String> productImageUrls = productImages.stream().map(ProductImage::getUrl).collect(Collectors.toList());
        productDetailsBO.setProductImages(productImageUrls);
        //设置相似商品
        List<ProductSalesVolumeBO> productSalesVolumeBOs = productMapper.findProductSalesVolumeTopByProductCategoryId(product.getProductCategoryId());
        if (productSalesVolumeBOs.size() > 0) {
            List<ProductBO> products = findProductInId(productSalesVolumeBOs
                    .stream()
                    .map(ProductSalesVolumeBO::getProductId)
                    .collect(Collectors.toList()));
            productDetailsBO.setRelatedProducts(products);

        }
        return productDetailsBO;
    }

    @Override
    public List<ProductBO> newestProduct() {
        List<ProductBO> productBOs = productMapper.newestProduct();
        addMasterPicture(productBOs);
        return productBOs;
    }

    @Override
    public List<ProductSalesVolumeBO> findProductSalesVolumeTop() {
        return productMapper.findProductSalesVolumeTop();
    }

    @Override
    public List<ProductBO> recentlySoldProducts() {
        List<ProductBO> productBOs = productMapper.recentlySoldProducts();
        addMasterPicture(productBOs);
        return productBOs;
    }

    @Override
    public List<ProductBO> findProductInId(List<Long> productIdList) {
        List<ProductBO> productBOs = productMapper.findProductInId(productIdList);
        addMasterPicture(productBOs);
        return productBOs;
    }

    @Override
    public List<Product> queryPrompt(String keyword) {
        return productMapper.queryPrompt(keyword);
    }

    @Override
    public BigDecimal findLastTransactionPriceByProductId(Long productId) {
        return productMapper.findLastTransactionPriceByProductId(productId);
    }

    @Override
    public List<ProductSalesVolumeBO> findProductSalesVolumeTopByProductCategoryId(Integer productCategoryId) {
        return productMapper.findProductSalesVolumeTopByProductCategoryId(productCategoryId);
    }

    @Override
    public ProductAttributesBO findProductPublicAttributesByProductId(Long productId) {
        return productMapper.findProductPublicAttributesByProductId(productId);
    }

    @Override
    public List<ProductHistoryPriceBO> findProductHistoryList(Long productId) {
        return productMapper.findProductHistoryList(productId);
    }



    /**
     * 商品后台管理查询所有商品
     * @return
     */
    @Override
    public List<ProductManageBO> findAllProduct() {
        List<ProductManageBO> productManageBOList = productMapper.findAllProduct();
        List<ProductManageBO> productManageBOS = userProductFavorateMapper.findByProductFavorateCounts();
        List<ProductBidBO> productBidBOList = productBidMapper.findAllUserBid();
        List<ProductAskBO> productAskBOList = productAskMapper.findAllMinProduct();
        List<ProductBidBO> productBidBOS = productBidMapper.findAllProductBidMaxPrice();

        for (ProductManageBO productManageBO : productManageBOList) {
            for (ProductManageBO manageBO : productManageBOS) {
                if (productManageBO.getProductId().equals(manageBO.getProductId())){
                    productManageBO.setUserFavorateCounts(manageBO.getUserFavorateCounts());
                }
            }
            for (ProductBidBO productBidBO : productBidBOS) {
                if (productManageBO.getProductId().equals(productBidBO.getProductId())){
                    productManageBO.setBidMaxPrice(productBidBO.getPrice());
                }
            }
            Map<String , BigDecimal> bidUserMap = new HashMap<>();
            for (ProductBidBO productBidBO : productBidBOList) {
                if (productManageBO.getProductId().equals(productBidBO.getProductId())){
                    bidUserMap.put(productBidBO.getUserName(),productBidBO.getPrice());
                    productManageBO.setBidUserMap(bidUserMap);
                }
            }
            for (ProductAskBO productAskBO : productAskBOList) {
                if (productManageBO.getProductId().equals(productAskBO.getProductId())){
                    productManageBO.setAskMinPrice(productAskBO.getPrice());
                    productManageBO.setAskFee(productAskBO.getFee());
                    productManageBO.setInventory(productAskBO.getInventory());
                    productManageBO.setAskUserName(productAskBO.getUserName());
                }
            }
        }

        addImageUrl(productManageBOList);
        return productManageBOList;
    }

    /**
     * 对图片进行主图展示
     *
     * @param productBOs
     * @return
     */
    public List<ProductBO> addMasterPicture(List<ProductBO> productBOs) {
        productBOs.forEach(o -> {
            o.setMasterPicture(serverUrlPrefix + o.getMasterPicture());
        });
        return productBOs;
    }

    public List<ProductManageBO> addImageUrl(List<ProductManageBO> productManageBOs) {
        productManageBOs.forEach(o -> {
            o.setImageUrl(serverUrlPrefix + o.getImageUrl());
        });
        return productManageBOs;
    }
}
