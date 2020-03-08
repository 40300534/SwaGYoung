package com.breeze.service.impl;
import com.breeze.dao.SpotDao;
import com.breeze.mapper.SpotMapper;
import com.breeze.pojo.Spot;
import com.breeze.service.SpotService;
import com.breeze.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpotServiceImpl implements SpotService {

    @Autowired
    private SpotDao spotDao;

    @Autowired
    private SpotMapper spotMapper;


    @Override
    public void saveSpot(Spot spot) {
        spot.setId(UUIDUtils.getUUID());
        spotDao.save(spot);

    }

    @Override
    public void updateSpot(Spot spot) {
        spotDao.save(spot);
    }

    @Override
    public Page<Spot> findSpot(Spot spot) {
        Specification<Spot> spotSpecification = (Specification<Spot>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if(!StringUtils.isEmpty(spot.getSpot_name())){

                Predicate p1 = criteriaBuilder.like(root.get("spot_name"), "%"+spot.getSpot_name()+"%");

                predicateList.add(p1);
            }
            if(!StringUtils.isEmpty(spot.getSpot_adder())){
                Predicate p2 = criteriaBuilder.equal(root.get("spot_adder"), spot.getSpot_adder());

                predicateList.add(p2);
            }
            if(!StringUtils.isEmpty(spot.getSpot_details())){
                Predicate p3 = criteriaBuilder.like(root.get("spot_details"), spot.getSpot_details());

                predicateList.add(p3);
            }
            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        Pageable pageable = PageRequest.of(spot.getPageNum(), spot.getPageSize());
        return spotDao.findAll(spotSpecification,pageable);
    }

    @Override
    public void delSpot(String ids) {

        if(ids.contains(",")){
            spotMapper.del(ids);
        }else {
            spotDao.deleteById(ids);
        }

    }

    @Override
    public Spot findSpotById(String id) {
        return spotDao.findById(id).get();
    }
}
