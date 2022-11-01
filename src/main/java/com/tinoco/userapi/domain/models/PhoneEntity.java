package com.tinoco.userapi.domain.models;

import com.tinoco.userapi.domain.dto.PhoneDto;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "PHONE")
@Data
@Accessors(chain = true)
public class PhoneEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    @Column(name = "id")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    @Column(name = "number")
    private String number;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "country_code")
    private String countryCode;

    @Override
    public String toString() {
        return "PhoneEntity [cityCode=" + cityCode + ", countryCode=" + countryCode + ", id=" + id + ", number="
                + number + "]";
    }

    public PhoneDto phoneDto() {
        return new PhoneDto()
                .setNumber(number)
                .setCityCode(cityCode)
                .setCountryCode(countryCode);
    }
}
