package backend.service;

import backend.dto.ExtratoDTO;
import backend.dto.MovimentacaoDTO;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CobolService {

    public ExtratoDTO consultarExtrato(String conta) {

        try {

            ProcessBuilder pb = new ProcessBuilder(
                    "wsl",
                    "bash",
                    "-c",
                    "cd ~/mainframe-bank-statement/cobol && ./extrato " + conta);

            Process process = pb.start();

            List<MovimentacaoDTO> movimentacoes = new ArrayList<>();

            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()))) {

                String linha;

                while ((linha = reader.readLine()) != null) {

                    if (!linha.contains(";")) {
                        continue;
                    }

                    String[] campos = linha.split(";");

                    if (campos.length < 4) {
                        continue;
                    }

                    movimentacoes.add(
                            new MovimentacaoDTO(
                                    campos[1],
                                    campos[2],
                                    Double.parseDouble(campos[3])));
                }
            }

            process.waitFor();

            return new ExtratoDTO(
                    conta,
                    movimentacoes);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao executar COBOL",
                    e);

        }
    }
}