package com.hackerrank.stocktrade.repository;

import java.util.List;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {
  List<Trade> findBySymbol(String stock);
  List<Trade> findByUser(User user);
  List<Trade> findByUserId(Long userId);
  List<Trade> findBySymbolAndUserId(String stock, Long userId);
}