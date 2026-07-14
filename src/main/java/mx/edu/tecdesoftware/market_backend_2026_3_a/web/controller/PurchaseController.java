package mx.edu.tecdesoftware.market_backend_2026_3_a.web.controller;


import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    /*
    listado completo, filtrar por cliente, registrar una nueva compra 
    codigos de estado, 
     */

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/client/{clientId}")
    public ResponseEntity<Purchase> getByCliente(@PathVariable("clientId") String clientId){
        return purchaseService.getByClientId(clientId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase) {
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.CREATED);
    }


}
