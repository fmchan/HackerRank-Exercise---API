package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {
    @Autowired TradeRepository tradeRepository;
    @Autowired UserRepository userRepository;

    @DeleteMapping
    public void deleteAll() {
		tradeRepository.deleteAll();
        userRepository.deleteAll();
	}
}
