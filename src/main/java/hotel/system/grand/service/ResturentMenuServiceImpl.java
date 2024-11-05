package hotel.system.grand.service;

import hotel.system.grand.dto.ResturentMenuDTO;
import hotel.system.grand.entity.ResturentMenuEntity;
import hotel.system.grand.repository.ResturentMenuRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor

public class ResturentMenuServiceImpl implements ResturentMenuService{
    final ResturentMenuRepository repository;
    final ModelMapper mapper;

    @Override
    public void addResturentMenu(ResturentMenuDTO resturentMenuDTO) {
        repository.save(mapper.map(resturentMenuDTO, ResturentMenuEntity.class));
    }

    @Override
    public Boolean deleteResturentMenu(Integer id) {
        repository.deleteById(id);
        return true;
    }

    @Override
    public List<ResturentMenuDTO> getAll() {
        List<ResturentMenuDTO> resturentMenuDTOList=new ArrayList<>();
        repository.findAll().forEach(resturentMenuEntity -> {
            resturentMenuDTOList.add(mapper.map(resturentMenuEntity,ResturentMenuDTO.class));
        });
        return resturentMenuDTOList;
    }

    @Override
    public List<ResturentMenuDTO> searchRoomDtoByFoodName(String foodName) {
        List<ResturentMenuDTO> resturentMenuDTOList=new ArrayList<>();
        repository.findByFoodName(foodName).forEach(resturentMenuEntity -> {
            resturentMenuDTOList.add(mapper.map(resturentMenuEntity,ResturentMenuDTO.class));
        });
        return resturentMenuDTOList;
    }
}
