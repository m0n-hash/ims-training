package com.sdm.ims.controller;


import com.sdm.ims.entity.*;
import com.sdm.ims.repository.SaleItemRepository;
import com.sdm.ims.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/sale/")
public class SaleController {
    @Autowired
    SaleRepository repository;

    @Autowired
    SaleItemRepository itemRepository;

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable("id")int id)throws Exception
    {
        Optional<SaleVoucher> result=repository.findById(id);
        if (result.isEmpty())
            throw new Exception("No data");
        return ResponseEntity.ok(result.get());
    }

    @GetMapping("all")
    public ResponseEntity<?> all()
    {
        try{
            return ResponseEntity.ok(repository.findAll());
        }catch (Exception ex)
        {throw  ex;}
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody SaleVoucher request) throws ParseException {
        request.setVoucher_dt(new Date());
        SaleVoucher data=repository.save(request);

        for (SaleItem i : request.getSaleItems()) {
            i.setSaleVoucherId(data.getId());
            itemRepository.save(i);
        }

        return ResponseEntity.ok(data);
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@RequestBody Map<String,Object> request, @PathVariable("id")int id)throws Exception
    {
        Optional<SaleVoucher> result=repository.findById(id);
        if (result.isEmpty())
            throw new Exception("No data");
        SaleVoucher data=result.get();
        if (request.containsKey("voucher_no"))
            data.setVoucher_no(request.get("voucher_no").toString());
        data=repository.save(data);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id")int id)throws Exception
    {
        if (repository.findById(id).isEmpty())
            throw new Exception("No data");
        repository.deleteById(id);
        return ResponseEntity.ok("Delete Success");
    }

}
