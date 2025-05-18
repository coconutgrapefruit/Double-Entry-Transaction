package com.sgvgroup.sgvbank;

import com.sgvgroup.sgvbank.Transaction.TransactionService;
import com.sgvgroup.sgvbank.Transaction.dto.TransactionDto;
import com.sgvgroup.sgvbank.Transaction.dto.TransactionRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sgvbank")
public class Controller {
    private final TransactionService transactionService;

    public Controller (
            TransactionService service
    ) {
        this.transactionService = service;
    }

    @PostMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> transactionPost (TransactionRequestDto request) {
        TransactionDto response = transactionService.makeTransaction(request);

        UriComponents uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(response.id());

        return ResponseEntity.created(uri.toUri()).body(response);
    }

    @GetMapping(value = "/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionDto> transactionGetById (UUID id) {
        TransactionDto response = transactionService.getTransaction(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<Page<TransactionDto>> history(
            @PathVariable UUID accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TransactionDto> pageDto = transactionService.getTransactionByAccount(accountId, pageable);
        return ResponseEntity.ok().body(pageDto);
    }

}
