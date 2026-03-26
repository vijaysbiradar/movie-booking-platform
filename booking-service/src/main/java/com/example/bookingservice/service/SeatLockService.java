package com.example.bookingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class SeatLockService {

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean lockSeat(String showId, String seatId) {
        String key = "lock:" + showId + ":" + seatId;

        Boolean success = redisTemplate.opsForValue()
                .setIfAbsent(key, "LOCKED", Duration.ofMinutes(5));

        return Boolean.TRUE.equals(success);
    }

    public void releaseSeat(String showId, String seatId) {
        String key = "lock:" + showId + ":" + seatId;
        redisTemplate.delete(key);
    }
}