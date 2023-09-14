package com.example.onlineschool.controller;

import com.example.onlineschool.controller.vm.PartnerVm;
import com.example.onlineschool.dto.ResponseData;
import com.example.onlineschool.dto.partner.CreatePartner;
import com.example.onlineschool.entity.Partner;
import com.example.onlineschool.mapper.PartnerMapper;
import com.example.onlineschool.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/partners")
public class PartnerController {

    private final PartnerService service;
    private final PartnerMapper mapper;

    @PostMapping
    public ResponseData<PartnerVm> create(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "file") MultipartFile file
    ) throws IOException {
        Partner partner = service.create(new CreatePartner(name, file));
        return ResponseData.of(mapper.asPartnerVm(partner));
    }

    @GetMapping
    public ResponseData<List<PartnerVm>> getAll(Pageable pageable) {
        List<Partner> partnerList = service.getAll(pageable);
        return ResponseData.of(mapper.asPartnerList(partnerList));
    }

    @GetMapping("/{id}")
    public ResponseData<PartnerVm> getById(@PathVariable Long id) {
        Partner partner = service.getById(id);
        return ResponseData.of(mapper.asPartnerVm(partner));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
