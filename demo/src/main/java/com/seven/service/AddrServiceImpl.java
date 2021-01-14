package com.seven.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.dao.AddressDao;
import com.seven.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddrServiceImpl implements AddrService {
    @Autowired
    AddressDao addressDao;

    @Override
    public int AddAddr(String username, String addr) {
        Address address = new Address();
        address.setName(username);
        address.setIsdefault(0);
        address.setAddr(addr);
        int rowAffect = addressDao.insert(address);
        if (rowAffect > 0)
            return 0;
        return 1;
    }

    @Override
    public void deleteAddr(int addrId) {
        addressDao.deleteById(addrId);
    }

    @Override
    public int updateAddr(int addrId, String newAddr) {
        Address addr = addressDao.selectById(addrId);
        addr.setAddr(newAddr);
        int rowAffect = addressDao.updateById(addr);
        if (rowAffect > 0)
            return 0;
        return 1;
    }

    @Override
    public void setDefaultAddr(String username, int addrId) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        List<Address> list = addressDao.selectList(wrapper);
        for (Address l : list) {
            if (l.getId() == addrId) {
                if (l.getIsdefault() != 1) {
                    l.setIsdefault(1);
                    addressDao.updateById(l);
                }
            } else {
                if (l.getIsdefault() == 1){
                    l.setIsdefault(0);
                    addressDao.updateById(l);
                }
            }
        }
    }

    @Override
    public List<Address> listAllAddrByUser(String username) {
        QueryWrapper<Address> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        return addressDao.selectList(wrapper);
    }
}
