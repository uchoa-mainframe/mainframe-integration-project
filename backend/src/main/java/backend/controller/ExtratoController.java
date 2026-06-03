package backend.controller;

import backend.dto.ExtratoDTO;
import backend.service.CobolService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ExtratoController {

    private final CobolService cobolService;

    public ExtratoController(CobolService cobolService) {
        this.cobolService = cobolService;
    }

    @GetMapping("/api/extrato")
    public ExtratoDTO extrato(
            @RequestParam String conta) {

        return cobolService.consultarExtrato(conta);

    }
}