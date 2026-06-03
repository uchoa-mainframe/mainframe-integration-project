package backend.dto;

import java.util.List;

public class ExtratoDTO {

    private String conta;
    private List<MovimentacaoDTO> movimentacoes;

    public ExtratoDTO() {
    }

    public ExtratoDTO(String conta,
            List<MovimentacaoDTO> movimentacoes) {

        this.conta = conta;
        this.movimentacoes = movimentacoes;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public List<MovimentacaoDTO> getMovimentacoes() {
        return movimentacoes;
    }

    public void setMovimentacoes(List<MovimentacaoDTO> movimentacoes) {
        this.movimentacoes = movimentacoes;
    }
}