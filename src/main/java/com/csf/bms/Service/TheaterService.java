package com.csf.bms.Service;

import com.csf.bms.Dto.TheaterDto;
import com.csf.bms.Model.Theater;
import com.csf.bms.Repo.TheaterRepo;
import com.csf.bms.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {


    @Autowired
    private TheaterRepo theaterRepo;


    public List<TheaterDto> createTheaters(List<TheaterDto> theaterDtos) {
        return theaterDtos.stream()
                .map(dto -> {
                    Theater theater = maptoEntity(dto);
                    Theater saved = theaterRepo.save(theater);
                    return maptoDto(saved);
                })
                .collect(Collectors.toList());
    }


    public TheaterDto getTheaterById(Long id){

        Theater theater=theaterRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Theater not found with id: "+id));
        return maptoDto(theater);
    }

    public List<TheaterDto> getAllTheater(){

        List<Theater>theaters=theaterRepo.findAll();
        return theaters.stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());
    }

    public List<TheaterDto> getAllTheaterByCity(String city){

        List<Theater>theaters=theaterRepo.findByCity(city);
        return theaters.stream()
                .map(this::maptoDto)
                .collect(Collectors.toList());
    }

    public TheaterDto updateTheaterByCity(String city, TheaterDto theaterDto) {

        Theater theater = theaterRepo.findFirstByCity(city)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Theater not found in city: " + city));

        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setTotalScreen(theaterDto.getTotalScreen());
        theater.setCity(theaterDto.getCity());

        Theater updatedTheater = theaterRepo.save(theater);
        return maptoDto(updatedTheater);
    }


    public void deleteAllTheaterByCity(String city) {

        List<Theater> theaters = theaterRepo.findByCity(city);

        if (theaters.isEmpty()) {
            throw new ResourceNotFoundException(
                    "Theater not found in city: " + city
            );
        }

        theaterRepo.deleteAll(theaters);
    }




    private TheaterDto   maptoDto(Theater theater) {
        TheaterDto theaterDto = new TheaterDto();
        theaterDto.setId(theater.getId());
        theaterDto.setName(theater.getName());
        theaterDto.setAddress(theater.getAddress());
        theaterDto.setCity(theater.getCity());
        theaterDto.setTotalScreen(theater.getTotalScreen());
        return theaterDto ;
    }


    private Theater maptoEntity(TheaterDto theaterDto){
      Theater theater = new Theater() ;
        theater.setName(theaterDto.getName());
        theater.setAddress(theaterDto.getAddress());
        theater.setCity(theaterDto.getCity());
        theater.setTotalScreen(theaterDto.getTotalScreen());
        return theater;

    }
}
