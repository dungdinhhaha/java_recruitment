package unigap.api.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import unigap.api.model.Seeker;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeekerOut {
    private String name;
    private Date birthday;
    private String address;
    private Integer provinceId;
    public static SeekerOut from (Seeker u){
        return SeekerOut.builder()
                .name(u.getName())
                .address(u.getAddress())
                .provinceId(u.getProvince())
                .birthday(u.getBirthday())
                .build();
    }


}
