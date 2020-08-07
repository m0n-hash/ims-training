package com.sdm.ims.controller;


import com.sdm.ims.entity.PurchaseItem;
import com.sdm.ims.entity.PurchaseVoucher;
import com.sdm.ims.repository.PurchaseItemRepository;
import com.sdm.ims.repository.PurchaseVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/purchase/")
public class PurchaseController {

    @Autowired
    PurchaseVoucherRepository purchaseVoucherRepository;

    @Autowired
    PurchaseItemRepository itemRepository;

    @GetMapping("all")
    public ResponseEntity<?> all()
    {
        try{
            return ResponseEntity.ok(purchaseVoucherRepository.findAll());
        }catch (Exception ex)
        {
            throw ex;
        }
    }

    @GetMapping("{id}")
    public ResponseEntity selectById(@PathVariable("id")int id)throws Exception
    {
        Optional<PurchaseVoucher> result=purchaseVoucherRepository.findById(id);
        if (result.isEmpty())
            throw new Exception("No data");
        return ResponseEntity.ok(result.get());
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody PurchaseVoucher request)
    {
        request.setVoucher_dt(new Date());
        PurchaseVoucher data=purchaseVoucherRepository.save(request);

        for (PurchaseItem i:request.getPurchaseItemSet()) {
            i.setPurchaseVoucherId(data.getId());
            itemRepository.save(i);
        }
        return ResponseEntity.ok(data);
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@RequestBody Map<String,Object> request, @PathVariable("id")int id)throws Exception
    {
        Optional<PurchaseVoucher> data=purchaseVoucherRepository.findById(id);
        if (data.isEmpty())
            throw new Exception("No data");
        PurchaseVoucher data1=data.get();
        if (request.containsKey("voucher_no"))
            data1.setVoucher_no(request.get("voucher_no").toString());
        data1=purchaseVoucherRepository.save(data1);
        return ResponseEntity.ok(data1);
    }
}
