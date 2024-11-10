package hotel.system.grand.service.impl;

import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.entity.HallBookingEntity;
import hotel.system.grand.entity.HallEntity;
import hotel.system.grand.repository.HallBookingRepository;
import hotel.system.grand.repository.HallRepository;
import hotel.system.grand.service.HallBookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HallBookingServiceImpl implements HallBookingService {
    final HallBookingRepository hallBookingRepository;
    final HallRepository hallRepository;
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
}
