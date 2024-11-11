package hotel.system.grand.service.impl;

import hotel.system.grand.dto.FoodDTO;
import hotel.system.grand.dto.OrdersDTO;
import hotel.system.grand.dto.ResturentMenuDTO;
import hotel.system.grand.entity.*;
import hotel.system.grand.repository.*;
import hotel.system.grand.service.ResturentMenuService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor

public class ResturentMenuServiceImpl implements ResturentMenuService {
    final ResturentMenuRepository resturentMenuRepository;
    final ResturantHasOrderRepository resturantHasOrderRepository;
    final OrderRepository orderRepository;
    final RoomBookingRepository roomBookingRepository;
    final CustomerRepository customerRepository;
    final ModelMapper mapper;

    @Override
    public HttpStatus addResturentMenu(ResturentMenuDTO resturentMenuDTO) {
        try{
            ResturentMenuEntity resturentMenuEntity = mapper.map(resturentMenuDTO, ResturentMenuEntity.class);
            resturentMenuEntity.setStatus(1);
            resturentMenuRepository.save(resturentMenuEntity);
            return HttpStatus.CREATED;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

    }

    @Override
    public Boolean deleteResturentMenu(Integer id) {
        Optional<ResturentMenuEntity> optionalResturentMenuEntity = resturentMenuRepository.findById(id);
        if (optionalResturentMenuEntity.isPresent()){
            ResturentMenuEntity resturentMenuEntity = optionalResturentMenuEntity.get();
            resturentMenuEntity.setStatus(0);
            resturentMenuRepository.save(resturentMenuEntity);
            return true;
        }
        return false;

    }

    @Override
    public List<ResturentMenuDTO> getAll() {
        List<ResturentMenuDTO> resturentMenuDTOList=new ArrayList<>();
        resturentMenuRepository.findAll().forEach(resturentMenuEntity -> {
           if(resturentMenuEntity.getStatus()==1){
               resturentMenuDTOList.add(mapper.map(resturentMenuEntity,ResturentMenuDTO.class));
           }
        });
        return resturentMenuDTOList;
    }

    @Override
    public List<ResturentMenuDTO> searchRoomDtoByFoodName(String foodName) {
        List<ResturentMenuDTO> resturentMenuDTOList=new ArrayList<>();
        resturentMenuRepository.findByFoodName(foodName).forEach(resturentMenuEntity -> {
            resturentMenuDTOList.add(mapper.map(resturentMenuEntity,ResturentMenuDTO.class));
        });
        return resturentMenuDTOList;
    }



}
