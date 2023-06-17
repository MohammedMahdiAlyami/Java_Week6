package com.example.parking.Controller;


import com.example.parking.DTO.BookingDTO;
import com.example.parking.Model.MyUser;
import com.example.parking.Model.Parking;
import com.example.parking.Model.Time;
import com.example.parking.Service.ParkingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @GetMapping("/get")
    public ResponseEntity getParking(){
        List<Parking> parkingList = parkingService.getParking();
        return ResponseEntity.status(200).body(parkingList);
    }

    @PostMapping("/add/{branchId}")
    public ResponseEntity addParking(@AuthenticationPrincipal MyUser user,@PathVariable Integer branchId, @Valid @RequestBody Parking parking){
        parkingService.addParking(user,branchId,parking);
        return ResponseEntity.status(200).body("Parking Added");
    }

    @PutMapping("/update/{branchId}/{parkingId}")
    public ResponseEntity updateParking(@AuthenticationPrincipal MyUser user, @Valid @RequestBody Parking parking, @PathVariable Integer branchId, @PathVariable Integer parkingId){
        parkingService.updateParking(user, parking, branchId, parkingId);
        return ResponseEntity.status(200).body("Parking Updated");
    }

    @DeleteMapping("/delete/{branchId}/{parkingId}")
    public ResponseEntity deleteParking(@AuthenticationPrincipal MyUser user, @PathVariable Integer branchId,@PathVariable Integer parkingId){
        parkingService.deleteParking(user, branchId,parkingId);
        return ResponseEntity.status(200).body("Parking Deleted");
    }

    //all
    @GetMapping("/get-time/{branchId}")
    public ResponseEntity getParkingByTime(@Valid @RequestBody BookingDTO bookingDTO, @PathVariable Integer branchId){
        List<Parking> parking = parkingService.getParkingByTime(bookingDTO,branchId);
        return ResponseEntity.status(200).body(parking);
    }

    //all
    @GetMapping("/get-not-available/{branchId}")
    public ResponseEntity getNotAvailableParking(@Valid @RequestBody BookingDTO bookingDTO, @PathVariable Integer branchId){
     Integer num = parkingService.getNotAvailableParking(bookingDTO,branchId);
     return ResponseEntity.status(200).body(num);
    }

    //all
    @GetMapping("/get-available/{branchId}")
    public ResponseEntity getAvailableParking(@Valid @RequestBody BookingDTO bookingDTO, @PathVariable Integer branchId){
        Integer num = parkingService.getAvailableParking(bookingDTO,branchId);
        return ResponseEntity.status(200).body(num);
    }
}
