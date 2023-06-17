package com.example.parking.Repository;

import com.example.parking.Model.Branch;
import com.example.parking.Model.Parking;
import com.example.parking.Model.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {

    Parking findParkingById(Integer id);

    List<Parking> findAllByBranch(Branch branch);

    Parking findParkingByParkingNumber(String number);

   List<Parking> findAllByBranchAndTimeSetContains(Branch branch, Time time);
}
