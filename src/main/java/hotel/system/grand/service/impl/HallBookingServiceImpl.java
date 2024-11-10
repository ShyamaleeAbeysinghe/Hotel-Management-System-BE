package hotel.system.grand.service.impl;

import hotel.system.grand.dto.HallBookingDTO;
import hotel.system.grand.dto.HallDTO;
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
        List<HallBookingEntity> allByBookingHall=hallBookingRepository.findAllByDate(LocalDate.parse(date));
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
}
