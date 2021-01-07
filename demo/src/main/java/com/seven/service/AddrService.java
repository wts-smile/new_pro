package com.seven.service;

import com.seven.pojo.Address;

import java.util.List;

public interface AddrService {
    int AddAddr(String username, String addr);
    void deleteAddr(int addrId);
    int updateAddr(int addrId, String newAddr);
    void setDefaultAddr(String username, int addrId);
    List<Address> listAllAddrByUser(String username);
}
