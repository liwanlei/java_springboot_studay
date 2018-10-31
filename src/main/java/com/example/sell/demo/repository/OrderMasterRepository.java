package com.example.sell.demo.repository;/*
 *
 * @author lileilei
 * Date: 2018/10/23
 * Time: 15:01
 */

import com.example.sell.demo.daoobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  OrderMasterRepository extends JpaRepository<OrderMaster ,String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

}
