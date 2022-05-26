package com.hackerrank.stocktrade.controller;

import java.util.List;
import java.util.Optional;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {
    @Autowired TradeRepository tradeRepository;
    @Autowired UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Trade> save(@RequestBody Trade trade) {
		if (tradeRepository.findById(trade.getId()).isPresent())
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		else
			try {
				userRepository.save(trade.getUser());
				return new ResponseEntity<>(tradeRepository.save(trade), HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}

    @GetMapping
    public List<Trade> findAll() {  
        return tradeRepository.findAll();  
    }

	@GetMapping("/{id}")
	public ResponseEntity<Trade> findById(@PathVariable("id") long id) {
		Optional<Trade> trade = tradeRepository.findById(id);

		if (trade.isPresent()) {
			return new ResponseEntity<>(trade.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/stocks/{stock}")
	public ResponseEntity<List<Trade>> findByStock(@PathVariable("stock") String stockId) {
		List<Trade> trades = tradeRepository.findBySymbol(stockId);

		if (!trades.isEmpty()) {
			return new ResponseEntity<>(trades, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Trade>> findByUserId(@PathVariable("userId") Long userId) {
		List<Trade> trade = tradeRepository.findByUserId(userId);

		if (!trade.isEmpty()) {
			return new ResponseEntity<>(trade, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/users/{userId}/stocks/{stock}")
	public ResponseEntity<List<Trade>> findBySymbolAndUserId(
		@PathVariable("stock") String stockId, @PathVariable("userId") Long userId) {
		List<Trade> trade = tradeRepository.findBySymbolAndUserId(stockId, userId);

		if (!trade.isEmpty()) {
			return new ResponseEntity<>(trade, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}