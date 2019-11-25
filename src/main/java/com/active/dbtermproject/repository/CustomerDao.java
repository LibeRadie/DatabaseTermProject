package com.active.dbtermproject.repository;

import com.active.dbtermproject.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDao { // db접근 함수들

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<String> getAllUserNames() {
        List<String> userNameList = new ArrayList<>();
        userNameList.addAll(this.jdbcTemplate.queryForList("select name from user", String.class));

        return userNameList;
    }

    // 회원 가입
    public int insert(Customer customer) {
        return this.jdbcTemplate.update(
                "insert customer values(?, ?, ?, ?, ?, ?)",
                customer.getId(), customer.getPassword(),
                customer.getEmail(), customer.getName(),
                customer.getPhoneNumber(), customer.getType()
        );
    }

    // 회원 탈퇴
    public int delete(String customerId) {
        return this.jdbcTemplate.update(
                "delete from customer where id=?",
                customerId
        );
    }

    // 회원 정보 수정
    public int update(String customerId, Customer newInfo) {
        return this.jdbcTemplate.update(
                "update customer SET " +
                        "password=?, " +
                        "email=?, " +
                        "name=?, " +
                        "phone_number=?, " +
                        "type=? " +
                        "WHERE id=?",
                newInfo.getPassword(),
                newInfo.getEmail(),
                newInfo.getName(),
                newInfo.getPhoneNumber(),
                newInfo.getType(),
                customerId
        );
    }

    // 관리자인지 확인하는 함수?
    public boolean isAdmin(Customer customer) {
        return false;
    }

    public Customer getCustomerById(String customerId) {
        return this.jdbcTemplate.queryForObject(
                "select * from customer c where c.id=?",
                (rs, rowNum) -> Customer.builder()
                        .id(rs.getString("id"))
                        .password(rs.getString("password"))
                        .email(rs.getString("email"))
                        .name(rs.getString("name"))
                        .phoneNumber(rs.getString("phone_number"))
                        .type(rs.getString("type")).build(), customerId);
    }

    public String getTypeById(String customerId) {
        return jdbcTemplate.queryForObject(
                "select type from customer c where c.id=?",
                new Object[]{customerId}, String.class);
    }
}
