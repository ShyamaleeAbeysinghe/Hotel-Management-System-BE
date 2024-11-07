package hotel.system.grand.service.impl;

import hotel.system.grand.dto.HallDTO;
import hotel.system.grand.dto.RoomDTO;
import hotel.system.grand.entity.HallEntity;
import hotel.system.grand.entity.RoomEntity;
import hotel.system.grand.repository.HallRepository;
import hotel.system.grand.service.HallService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HallServiceImpl implements HallService {
    final HallRepository hallRepository;
    final ModelMapper mapper;

    @Override
    public HttpStatus addHall(HallDTO hallDTO) {
        try{
            HallEntity hallEntity = mapper.map(hallDTO, HallEntity.class);
            hallEntity.setStatus(1);
            hallRepository.save(hallEntity);
            return HttpStatus.CREATED;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public Boolean deleteHall(Integer id) {

        Optional<HallEntity> optionalHallEntity = hallRepository.findById(id);
        if (optionalHallEntity.isPresent()){
            HallEntity hallEntity = optionalHallEntity.get();
            hallEntity.setStatus(0);
            hallRepository.save(hallEntity);
            return true;
        }
        return false;

    }

    @Override
    public List<HallDTO> getAll() {
        List<HallDTO> hallDTOList=new ArrayList<>();
        hallRepository.findAll().forEach(hallEntity -> {
            if (hallEntity.getStatus()==1) {
                hallDTOList.add(mapper.map(hallEntity,HallDTO.class));
            }
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
