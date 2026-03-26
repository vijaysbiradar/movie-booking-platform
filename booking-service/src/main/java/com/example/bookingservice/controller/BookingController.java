package com.example.bookingservice.controller;

import com.example.bookingservice.service.SeatLockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final SeatLockService seatLockService;

    @PostMapping("/lock")
    public String lockSeat(@RequestParam String showId,
                           @RequestParam String seatId) {

        boolean locked = seatLockService.lockSeat(showId, seatId);

        return locked ? "Seat locked" : "Already locked";
    }

    @PostMapping("/release")
    public String releaseSeat(@RequestParam String showId,
                              @RequestParam String seatId) {

        seatLockService.releaseSeat(showId, seatId);
        return "Seat released";
    }
}