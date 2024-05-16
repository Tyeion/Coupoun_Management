package com.coupon.Service;

import com.coupon.Entity.Coupon;
import com.coupon.Entity.Product;
import com.coupon.Exception.ResourceNotFoundException;

import java.util.List;

public interface CouponService {
    List<Coupon> getAllCoupons();
    Coupon getCouponById(Long id) throws ResourceNotFoundException;
    Coupon saveCoupon(Coupon coupon);
    void deleteCouponById(Long id);

    Coupon updateCouponById(Long id, Coupon coupon);


//    new method by product id ---> custom method

    List<Coupon> getCouponsForProduct(Long id) throws ResourceNotFoundException;



int getCouponUsageCountForProduct(String couponCode, Long productId) throws ResourceNotFoundException;

void incrementCouponUsageCount(String couponCode, Long productId) throws ResourceNotFoundException;



}
