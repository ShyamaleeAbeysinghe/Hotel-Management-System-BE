package hotel.system.grand.service.impl;

import hotel.system.grand.dto.*;
import hotel.system.grand.entity.*;
import hotel.system.grand.repository.CustomerRepository;
import hotel.system.grand.repository.HallBookingRepository;
import hotel.system.grand.repository.HallRepository;
import hotel.system.grand.service.HallBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HallBookingServiceImpl implements HallBookingService {
    final HallBookingRepository hallBookingRepository;
    final HallRepository hallRepository;
    final CustomerRepository customerRepository;
    final ModelMapper mapper;

    @Override
    public List<HallDTO> findAvailableHall(String date) {
        List<HallBookingEntity> allByBookingHall=hallBookingRepository.findAllByBookedDate(LocalDate.parse(date));
        List<HallEntity> allHalls=hallRepository.findAll();

        allHalls.forEach(hallEntity -> {
            allByBookingHall.forEach(hallBookingEntity -> {
                if(hallEntity.getId()==hallBookingEntity.getHallEntity().getId()){
                    hallEntity.setStatus(0);
                }
            });
        });

        List<HallDTO> hallDTOList=new ArrayList<>();
        allHalls.forEach(hallEntity -> {
           if (hallEntity.getStatus()==1){
               hallDTOList.add(mapper.map(hallEntity,HallDTO.class));
           }
        });
        return hallDTOList;
    }

    @Override
    public HttpStatus saveHallBooking(HallBookingDTO hallBookingDTO) {
        try {
            Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(hallBookingDTO.getCustomerId());
            Optional<HallEntity> optionalHallEntity = hallRepository.findById(hallBookingDTO.getHallId());
            if (optionalHallEntity.isPresent() && optionalCustomerEntity.isPresent()){
                HallBookingEntity hallBookingEntity = mapper.map(hallBookingDTO, HallBookingEntity.class);
                hallBookingEntity.setHallEntity(optionalHallEntity.get());
                hallBookingEntity.setCustomerEntity01(optionalCustomerEntity.get());
                hallBookingEntity.setDate(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                hallBookingEntity.setStatus(1);
                hallBookingRepository.save(hallBookingEntity);
                return HttpStatus.CREATED;
            }else{
                return HttpStatus.NOT_FOUND;
            }
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public List<HallBookingResponseDTO> getAllHallBookingsByCustomer(Integer userId) {
        List<HallBookingResponseDTO> hallBookingResponseDTOList=new ArrayList<>();
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(userId);
        if (optionalCustomerEntity.isPresent()){
            CustomerEntity customerEntity = optionalCustomerEntity.get();
            hallBookingRepository.findAllByCustomerEntity01(customerEntity).forEach(hallBookingEntity -> {
                if(hallBookingEntity.getStatus()==1){
                    HallBookingResponseDTO hallBookingResponseDTO = mapper.map(hallBookingEntity, HallBookingResponseDTO.class);
                    hallBookingResponseDTO.setPrice(hallBookingEntity.getHallEntity().getPrice());
                    hallBookingResponseDTO.setImg(hallBookingEntity.getHallEntity().getImg());
                    hallBookingResponseDTOList.add(hallBookingResponseDTO);
                }
            });
        }

        return hallBookingResponseDTOList;
    }

    @Override
    public List<ManageHallBookingDTO> getAllHallBookings() {
        List<ManageHallBookingDTO> bookingDTOList = new ArrayList<>();
        hallBookingRepository.findAll().forEach(hallBookingEntity -> {
            hallBookingEntity.getCustomerEntity01().setPassword("");
            hallBookingEntity.getCustomerEntity01().setUserName("");
            ManageHallBookingDTO manageBookingDTO = mapper.map(hallBookingEntity, ManageHallBookingDTO.class);
            if (hallBookingEntity.getStatus() == 0) {
                manageBookingDTO.setStatus("Cancelled");
            } else if (hallBookingEntity.getStatus() == 1) {
                manageBookingDTO.setStatus("Pending");
            }
            manageBookingDTO.setTotal(hallBookingEntity.getHallEntity().getPrice());
            manageBookingDTO.setBookDate(hallBookingEntity.getBookedDate());
            bookingDTOList.add(manageBookingDTO);
        });
        return bookingDTOList;
    }

    @Override
    public HttpStatus cancelBooking(Integer bookingId) {
        Optional<HallBookingEntity> optionalHallBookingEntity = hallBookingRepository.findById(bookingId);
        if (optionalHallBookingEntity.isPresent()){
            HallBookingEntity hallBookingEntity = optionalHallBookingEntity.get();
            LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long difference = ChronoUnit.DAYS.between(currentDate, hallBookingEntity.getBookedDate());
            if (difference<7){
                return HttpStatus.NOT_ACCEPTABLE;
            }else{
                hallBookingEntity.setStatus(0);
                hallBookingRepository.save(hallBookingEntity);
                return HttpStatus.ACCEPTED;
            }
        }else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
