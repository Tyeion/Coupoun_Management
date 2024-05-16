package com.coupon.Service.Impl;

import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;
import com.coupon.Repository.CouponRepository;
import com.coupon.Service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {


   private static final int MAX_USAGE  = 5;

    @Autowired
    private CouponRepository couponRepository;
    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon getCouponById(Long id) throws ResourceNotFoundException {
        Optional<Coupon> coupon =  couponRepository.findById(id);
        if(coupon.isEmpty()){
            throw new ResourceNotFoundException("Coupon id is not available");
        }
        return coupon.get();
    }

//    @Override
//    public List<Coupon> getCouponsForProduct(Long id) {
//        return  couponRepository.findByProductId(id);
////        List<Coupon> coupons = couponRepository.findByProductId(id);
////        if (coupons.isEmpty()) {
////            throw new ResourceNotFoundException("No coupons found for product with id: " + id);
////        }
////        return coupons.get();
//    }

    @Override
    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public void deleteCouponById(Long id) {
couponRepository.deleteById(id);
    }

    @Override
    public Coupon updateCouponById(Long id, Coupon coupon) {
        Coupon modelDB = couponRepository.findById(id).get();

        if (Objects.nonNull(coupon.getName()) && !"".equalsIgnoreCase(coupon.getName())) {
            modelDB.setName(coupon.getName());
        }

        return couponRepository.save(modelDB);
    }

    @Override
    public List<Coupon> getCouponsForProduct(Long id) throws ResourceNotFoundException {
        return  couponRepository.findByProductId(id);
    }

    @Override
    public int getCouponUsageCountForProduct(String couponCode, Long productId) throws ResourceNotFoundException {
        Coupon coupon = couponRepository.findByCouponCodeAndProductId(couponCode, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found for the provided code and product"));
        return coupon.getUsageCount();
    }

    @Override
    public void incrementCouponUsageCount(String couponCode, Long productId) throws ResourceNotFoundException {
        Coupon coupon = couponRepository.findByCouponCodeAndProductId(couponCode, productId)
                .orElseThrow(() -> new ResourceNotFoundException("Coupon not found for the provided code and product"));
        int usageCount = coupon.getUsageCount() + 1;
        coupon.setUsageCount(usageCount);
        couponRepository.save(coupon);
    }


//    @Override
//    public Coupon getCouponByCouponCodeAndProduct(Long id, Product product) {
//        return couponRepository.findByCouponCodeAndProduct(id,product);
//    }

//    @Override
//    public Coupon redeemCoupon(Long couponId, Long productId) {
//        return null;
//    }


//    for counting the usage count
//@Override
//public Coupon redeemCoupon(Long couponId, Long productId) {
//
//    Coupon coupon = couponRepository.findById(couponId).orElse(null);
//
//    if (coupon != null && coupon.getUsageCount() < MAX_USAGE && coupon.getProduct().getId().equals(productId)) {
//        // Increment the usage count
//        coupon.setUsageCount(coupon.getUsageCount() + 1);
//
//        // Update the coupon in the database
//        couponRepository.save(coupon);
//    }
//    return coupon;
//}
    // Retrieve the coupon from the database

//    @Override
//    public Coupon saveOrUpdateCoupon(Coupon coupon) {
//        // Check if a coupon with the same code and product ID exists
//        Coupon existingCoupon = couponRepository.findByCouponCodeAndProduct(coupon.getCouponCode(), coupon.getProduct());
//
//        if (existingCoupon != null) {
//            // Increment the usage count
//            existingCoupon.setUsageCount(existingCoupon.getUsageCount() + 1);
//            return couponRepository.save(existingCoupon); // Update existing coupon
//        } else {
//            return couponRepository.save(coupon); // Save new coupon
//        }
//    }

//    @Override
//    public Coupon findByCouponCodeAndProduct(String couponCode, Product product) {
//        return couponRepository.findByCouponCodeAndProduct(couponCode, product);
//
//    }

}

