package hotel.system.grand.service.impl;

import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.entity.HallEntity;
import hotel.system.grand.repository.HallRepository;
import hotel.system.grand.service.HallService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    final HallRepository hallRepository;
    final ModelMapper mapper;

    @Override
    public void addHall(HallDTO hallDTO) {
        hallRepository.save(mapper.map(hallDTO, HallEntity.class));
    }

    @Override
    public Boolean deleteHall(Integer id) {
        hallRepository.deleteById(id);
        return true;
    }

    @Override
    public List<HallDTO> getAll() {
        List<HallDTO> hallDTOList=new ArrayList<>();
        hallRepository.findAll().forEach(hallEntity -> {
            hallDTOList.add(mapper.map(hallEntity,HallDTO.class));
        });
        return hallDTOList;
    }

    @Override
    public List<HallDTO> searchHallDtoByHallName(String hallName) {
        List<HallDTO> hallDTOList=new ArrayList<>();
        hallRepository.findByHallName(hallName).forEach(hallEntity -> {
            hallDTOList.add(mapper.map(hallEntity,HallDTO.class));
        });
        return hallDTOList;
    }
}
