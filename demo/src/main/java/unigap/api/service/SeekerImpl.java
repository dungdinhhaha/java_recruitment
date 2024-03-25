package unigap.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import unigap.api.dto.in.PageDtoIn;
import unigap.api.dto.in.SeekerIn;
import unigap.api.dto.out.PageDtoOut;
import unigap.api.dto.out.SeekerOut;
import unigap.api.model.Seeker;
import unigap.api.repository.SeekerRepository;
import unigap.common.ErrorCode;
import unigap.common.response.ApiException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SeekerImpl implements ISeeker{

@Autowired
    private SeekerRepository seekerRepository;

    @Override
    public PageDtoOut<SeekerOut> getAllSeeker(PageDtoIn pageDtoIn) {
        Page<Seeker> seekers = seekerRepository.findAll(PageRequest.of(pageDtoIn.getPage()-1, pageDtoIn.getPageSize()));
        return PageDtoOut.from(pageDtoIn.getPage(),pageDtoIn.getPageSize(),3L, seekers.stream().map(SeekerOut::from).toList());
    }

    @Override
    public SeekerOut getSeekerByID(long id) {
        Seeker seeker = seekerRepository.findById((int) id).orElseThrow(
                ()->new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST,"Cant Find By ID")
        );
       return SeekerOut.from(seeker);

    }

    @Override
    public SeekerOut createSeeker(SeekerIn seekerIn) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Seeker seeker = Seeker.builder()
                .name(seekerIn.getName())
                .address(seekerIn.getAddress())
                .birthday(seekerIn.getBirthday())
                .createdAt( Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .updatedAt(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .province(Math.toIntExact(seekerIn.getProvinceId()))
                .build();
        seekerRepository.save(seeker);
        return SeekerOut.from(seeker);
    }

    @Override
    public SeekerOut updateSeeker(long id, SeekerIn seekerIn) {
        Seeker seeker = seekerRepository.findById((int) id).orElseThrow(
                () -> new ApiException(ErrorCode.NOT_FOUND, HttpStatus.BAD_REQUEST,"Cant Find By ID")
        );
        seeker.setName(seekerIn.getName());
        seeker.setBirthday(seekerIn.getBirthday());
        seeker.setAddress(seekerIn.getAddress());
        seeker.setProvince(seeker.getProvince());
        seekerRepository.save(seeker);
        return SeekerOut.from(seeker);
    }

    @Override
    public String deleteSeeker(long id) {
        seekerRepository.deleteById((int) id);
        return "Da Xoa";
    }
}
