function entrar() {

    document.getElementById("login").style.display = "none";

    document.getElementById("menu").style.display = "block";

}

async function consultarExtrato() {

    const conta =
        document.getElementById("conta").value;

    const response =
        await fetch(
            `http://localhost:8080/api/extrato?conta=${conta}`
        );

    const dados =
        await response.json();

    let saldo = 0;

    dados.movimentacoes.forEach(mov => {
        saldo += mov.valor;
    });

    let html = `

        <div class="extrato-card">

            <div class="saldo-card">

                <span>Saldo Disponível</span>

                <h1>
                    ${saldo.toLocaleString(
        "pt-BR",
        {
            style: "currency",
            currency: "BRL"
        }
    )}
                </h1>

            </div>

            <h2>Extrato Conta ${dados.conta}</h2>

            <table class="extrato-table">

                <thead>

                    <tr>
                        <th>Data</th>
                        <th>Descrição</th>
                        <th>Valor</th>
                    </tr>

                </thead>

                <tbody>
    `;

    dados.movimentacoes.forEach(mov => {

        const dataFormatada =
            mov.data.substring(6, 8) + "/" +
            mov.data.substring(4, 6) + "/" +
            mov.data.substring(0, 4);

        const valorFormatado =
            mov.valor.toLocaleString(
                "pt-BR",
                {
                    style: "currency",
                    currency: "BRL"
                }
            );

        html += `

            <tr>

                <td>
                    ${dataFormatada}
                </td>

                <td class="descricao">
                    ${mov.descricao}
                </td>

                <td class="${mov.valor >= 0 ? 'credito' : 'debito'}">
                    ${valorFormatado}
                </td>

            </tr>

        `;
    });

    html += `

                </tbody>

            </table>

        </div>

    `;

    document.getElementById("resultado").innerHTML =
        html;
}