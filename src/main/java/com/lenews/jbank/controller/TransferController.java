package com.lenews.jbank.controller;

import com.lenews.jbank.controller.dto.TransferRequestDto;
import com.lenews.jbank.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<Void> initiateTransfer(@RequestBody @Valid TransferRequestDto dto) {
        // This method will handle the transfer request.
        // It will call the service layer to process the transfer.
        // The TransferRequestDto should contain the necessary information for the transfer.
        transferService.transferMoney(dto);


        return ResponseEntity.ok().build();

    }

}
