package com.example.onlineschool.mapper;

import com.example.onlineschool.controller.vm.PartnerVm;
import com.example.onlineschool.dto.partner.CreatePartner;
import com.example.onlineschool.entity.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PartnerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "imgUrl", source = "imgUrl")
    Partner asNewPartner(CreatePartner dto, String imgUrl);

    PartnerVm asPartnerVm(Partner partner);

    List<PartnerVm> asPartnerList(List<Partner> partners);
}
