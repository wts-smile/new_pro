package com.seven.controller;

import com.seven.models.resobjs.ErrnoRes;
import com.seven.pojo.Address;
import com.seven.service.AddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddrController {
    @Autowired
    AddrService addrService;

    @PostMapping("/addr/add")
    public ErrnoRes addrAdd(@RequestParam("username") String username, @RequestParam("addr") String addr) {
        return new ErrnoRes(addrService.AddAddr(username, addr));
    }

    @PostMapping("/addr/setdefault")
    public ErrnoRes setdefault(@RequestParam("username") String username, @RequestParam("aid") int aid) {
        addrService.setDefaultAddr(username, aid);
        return new ErrnoRes(0);
    }

    @GetMapping("/addr/list")
    public List<Address> addrList(@RequestParam("username") String username) {
        return addrService.listAllAddrByUser(username);
    }

    @PostMapping("/addr/upd")
    public ErrnoRes addrList(@RequestParam("aid") int aid, @RequestParam("addr") String addr){
        return new ErrnoRes(addrService.updateAddr(aid, addr));
    }

    @PostMapping("/addr/del")
    public ErrnoRes addrDel(@RequestParam("aid") int aid) {
        addrService.deleteAddr(aid);
        return new ErrnoRes(0);
    }
}
